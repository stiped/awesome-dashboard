package awesome.dashboard.jira.data;

import com.fasterxml.jackson.annotation.JsonGetter;

@SuppressWarnings("unused")
public class Status {

    private String status;
    private Long statusKategori;

    public Status medStatus(String status) {
        this.status = status;
        return this;
    }

    public Status medStatusKategori(Long statusKategori) {
        this.statusKategori = statusKategori;
        return this;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonGetter
    public Long getKategori() {
        return statusKategori;
    }
}
