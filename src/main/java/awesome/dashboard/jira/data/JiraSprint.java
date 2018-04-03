package awesome.dashboard.jira.data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@JsonPropertyOrder(alphabetic=true)
public class JiraSprint {

    private Long sprintId;
    private String status;
    private String navn;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;
    private List<JiraIssue> issues;

    public JiraSprint() {
        this.issues = new ArrayList<>();
    }

    public JiraSprint medSprintId(Long sprintId) {
        this.sprintId = sprintId;
        return this;
    }

    public JiraSprint medStatus(String status) {
        this.status = status;
        return this;
    }

    public JiraSprint medNavn(String navn) {
        this.navn = navn;
        return this;
    }

    public JiraSprint medStartetTidspunkt(LocalDateTime startet) {
        this.startDato = startet;
        return this;
    }

    public JiraSprint medSluttTidspukt(LocalDateTime slutt) {
        this.sluttDato = slutt;
        return this;
    }

    public void leggTilIssue(JiraIssue issue) {
        this.issues.add(issue);
    }

    @JsonGetter
    public Long getSprintId() {
        return sprintId;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonGetter
    public String getNavn() {
        return navn;
    }

    @JsonGetter
    public String getStartDato() {
        return startDato.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @JsonGetter
    public String getSluttDato() {
        return sluttDato.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @JsonGetter Long dagerIgjen() {
        return ChronoUnit.DAYS.between(startDato, sluttDato);
    }

    @JsonGetter
    public List<JiraIssue> issues_i_sprint() {
        return issues;
    }

    @JsonGetter Long storypoints_totalt() {
        return issues.stream().filter(issue -> issue.getStorypoint() != null).mapToLong(JiraIssue::getStorypoint).sum();
    }

    @JsonGetter Long storypoints_inProgress() {
        return issues.stream().filter(issue -> issue.getStorypoint() != null).filter(issue -> issue.getStatus().getKategori() == 4).mapToLong(JiraIssue::getStorypoint).sum();
    }

    @JsonGetter Long storypoints_todo() {
        return issues.stream().filter(issue -> issue.getStorypoint() != null).filter(issue -> issue.getStatus().getKategori() == 2).mapToLong(JiraIssue::getStorypoint).sum();
    }

    @JsonGetter Long storypoints_done() {
        return issues.stream().filter(issue -> issue.getStorypoint() != null).filter(issue -> issue.getStatus().getKategori() == 3).mapToLong(JiraIssue::getStorypoint).sum();
    }
}
