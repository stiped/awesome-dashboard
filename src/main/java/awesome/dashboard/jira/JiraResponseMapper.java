package awesome.dashboard.jira;

import awesome.dashboard.jira.data.JiraSprint;
import awesome.dashboard.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class JiraResponseMapper {

    public static JiraSprint mapTilJiraSprint(JsonNode rootNode) {
        JiraSprint jiraSprint = new JiraSprint();

        jiraSprint.medSprintId(rootNode.get("id").asLong());
        jiraSprint.medNavn(rootNode.get("name").asText());
        jiraSprint.medStatus(rootNode.get("state").asText());
        jiraSprint.medStartetTidspunkt(JsonUtils.fromZuluToLocalDateTime(rootNode.get("startDate").asText()));
        jiraSprint.medSluttTidspukt(JsonUtils.fromZuluToLocalDateTime(rootNode.get("endDate").asText()));

        return jiraSprint;
    }

}
