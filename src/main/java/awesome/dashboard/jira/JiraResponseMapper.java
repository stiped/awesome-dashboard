package awesome.dashboard.jira;

import awesome.dashboard.jira.data.JiraIssue;
import awesome.dashboard.jira.data.JiraSprint;
import awesome.dashboard.jira.data.Status;
import awesome.dashboard.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

import static awesome.dashboard.jira.JiraService.STORYPOINT_FIELD;

public class JiraResponseMapper {

    public static JiraSprint mapTilJiraSprint(JsonNode rootNode) {
        JiraSprint jiraSprint = new JiraSprint();

        rootNode.get("issues").forEach(issue -> byggIssue(issue, jiraSprint));
        return jiraSprint;
    }

    private static void byggIssue(JsonNode issue, JiraSprint jiraSprint) {

        JsonNode fields = issue.get("fields");
        byggSprintInfo(jiraSprint, fields.get("sprint"));

        JiraIssue jiraIssue = new JiraIssue().medKey(issue.get("key").asText())
                .medIssueType(fields.get("issuetype").get("name").asText())
                .medStatus(byggStatus(fields.get("status")));
        if (fields.get(STORYPOINT_FIELD) != null) {
                jiraIssue.medStorypoint(fields.get(STORYPOINT_FIELD).asLong());
        }

        jiraSprint.leggTilIssue(jiraIssue);
    }

    private static Status byggStatus(JsonNode statusJson) {
        Status status = new Status().medStatus(statusJson.get("name").asText())
                .medStatusKategori(statusJson.get("statusCategory").get("id").asLong());
        return status;
    }

    private static void byggSprintInfo(JiraSprint jiraSprint, JsonNode sprintNode) {
        if (jiraSprint.getSprintId() == null) {
            jiraSprint.medSprintId(sprintNode.get("id").asLong());
            jiraSprint.medNavn(sprintNode.get("name").asText());
            jiraSprint.medStatus(sprintNode.get("state").asText());
            jiraSprint.medStartetTidspunkt(JsonUtils.fromZuluToLocalDateTime(sprintNode.get("startDate").asText()));
            jiraSprint.medSluttTidspukt(JsonUtils.fromZuluToLocalDateTime(sprintNode.get("endDate").asText()));
        }
    }

}
