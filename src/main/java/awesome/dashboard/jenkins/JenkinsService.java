package awesome.dashboard.jenkins;

import awesome.dashboard.PropertiesService;
import awesome.dashboard.jenkins.data.Jenkins;
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

import java.io.*;
import java.net.URI;

public class JenkinsService {

    public Jenkins hentOversikt(String jobbnavn) {
        URI uri = URI.create(String.format("https://jenkins.intern.landbruksdirektoratet.no/job/%s/api/json?depth=1", jobbnavn));
        HttpHost httpHost = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(PropertiesService.get("jenkins.username"), PropertiesService.get("jenkins.password")));

        BasicAuthCache authCache = new BasicAuthCache();

        BasicScheme basicAuth = new BasicScheme();
        authCache.put(httpHost, basicAuth);


        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        try {
            CloseableHttpResponse response = httpClient.execute(httpHost, httpGet, localContext);
            return JenkinsResponseMapper.mapTilJenkins(new ObjectMapper().readTree(EntityUtils.toString(response.getEntity())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
