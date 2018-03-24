package awesome.dashboard.api;

import awesome.dashboard.jenkins.JenkinsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/jenkins")
@Produces(MediaType.APPLICATION_JSON)
public class JenkinsController {

    private final JenkinsService jenkinsService;
    private final ObjectMapper objectMapper;

    public JenkinsController() {
        this.jenkinsService = new JenkinsService();
        this.objectMapper = new ObjectMapper();
    }

    @GET
    @Path("{jobbnavn}")
    public Response hentInformasjonOmJobb(@PathParam("jobbnavn") String jobbnavn) throws JsonProcessingException {
        return Response.ok(objectMapper.writeValueAsString(jenkinsService.hentOversikt(jobbnavn))).build();
    }
}
