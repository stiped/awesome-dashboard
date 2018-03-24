package awesome.dashboard.jenkins;

import awesome.dashboard.jenkins.data.Bygg;
import awesome.dashboard.jenkins.data.Endring;
import awesome.dashboard.jenkins.data.Helserapport;
import awesome.dashboard.jenkins.data.Jenkins;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class JenkinsResponseMapper {

    public static Jenkins mapTilJenkins(JsonNode rootNode) {
        Jenkins jenkins = new Jenkins();

        jenkins.medNavn(rootNode.get("name").asText());
        jenkins.medByggfarge(rootNode.get("color").asText());

        Helserapport helserapport = new Helserapport();
        rootNode.get("healthReport").forEach(jsonNode -> {
            String description = jsonNode.get("description").asText();
            if (description.startsWith("Build stability:")) {
                helserapport.medByggstabilitet(description.substring(description.indexOf(": ")+2));
            }
            if (description.startsWith("Test Result:")) {
                helserapport.medTestresultat(description.substring(description.indexOf(": ")+2));
            }
        });
        jenkins.medHelse(helserapport);

        rootNode.get("builds").forEach(jsonNode -> {
            Bygg bygg = new Bygg();
            boolean byggerNaa = jsonNode.get("building").asBoolean();
            bygg.byggerNaa(byggerNaa)
            .medDisplayname(jsonNode.get("displayName").asText())
            .medJobbId(jsonNode.get("number").longValue())
            .medJobbStartet(LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonNode.get("timestamp").asLong()), ZoneId.systemDefault()))
            .medStatus(byggerNaa ? "BUILDING" : jsonNode.get("result").asText())
            .medEndringer(getEndringer(jsonNode));

            jenkins.medBygg(bygg);
        });
        return jenkins;
    }

    private static List<Endring> getEndringer(JsonNode jsonNode) {
        List<Endring> endringer = new ArrayList<>();
        jsonNode.get("changeSet").get("items").forEach(jsonEndring -> {
            Endring endring = new Endring();
            endring.medKommentar(jsonEndring.get("comment").asText().replaceAll("\\n", "").trim())
                    .medAuthor(jsonEndring.get("author").get("fullName").asText());
            endringer.add(endring);
        });
        return endringer;
    }

}
