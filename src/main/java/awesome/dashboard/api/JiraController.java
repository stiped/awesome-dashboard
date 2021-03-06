package awesome.dashboard.api;

import awesome.dashboard.jira.JiraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("jira")
@Produces(MediaType.APPLICATION_JSON)
public class JiraController {

    private final JiraService jiraService;
    private final ObjectMapper objectMapper;


    public JiraController() {
        this.jiraService = new JiraService();
        this.objectMapper = new ObjectMapper();
    }

    @GET
    @Path("sprint/oversikt")
    public Response SprintInformasjon() throws JsonProcessingException {
        return Response.ok(objectMapper.writeValueAsString(jiraService.hentOversikt())).build();
    }
}
