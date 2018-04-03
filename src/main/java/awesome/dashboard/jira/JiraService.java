package awesome.dashboard.jira;

import awesome.dashboard.PropertiesService;
import awesome.dashboard.jira.data.JiraSprint;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

public class JiraService {

    public static String STORYPOINT_FIELD;

    public JiraService() {
        STORYPOINT_FIELD = PropertiesService.get("jira.issue.field.storypoint");
    }

    public JiraSprint hentOversikt() {
        URI uri = URI.create(
                String.format("%s/rest/agile/1.0/sprint/%s/issue?maxResults=100&fields=%s",
                        PropertiesService.get("jira.baseUrl"),
                        PropertiesService.get("jira.sprint.id"),
                        byggQueryFields()
                )
        );
        HttpHost httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(PropertiesService.get("jira.username"), PropertiesService.get("jira.password")));

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);

        BasicAuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(httpHost, basicAuth);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        try {
            CloseableHttpResponse response = httpClient.execute(httpHost, httpGet, localContext);
            return JiraResponseMapper.mapTilJiraSprint(new ObjectMapper().readTree(EntityUtils.toString(response.getEntity())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String byggQueryFields() {
        String fields = "sprint,status,issuetype," + STORYPOINT_FIELD;
        return fields;
    }
}
