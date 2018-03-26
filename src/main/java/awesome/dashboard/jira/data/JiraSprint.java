package awesome.dashboard.jira.data;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class JiraSprint {

    private Long sprintId;
    private String status;
    private String navn;
    private LocalDateTime startDato;
    private LocalDateTime sluttDato;

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
}
