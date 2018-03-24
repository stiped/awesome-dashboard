package awesome.dashboard.jenkins.data;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Bygg {

    private Long jobbId;
    private boolean byggerNaa;
    private String displayname;
    private String status;
    private LocalDateTime jobbStartet;
    private List<Endring> endringer;

    public Bygg() {
        this.endringer = new ArrayList<>();
    }

    public Bygg medJobbId(Long jobbId) {
        this.jobbId = jobbId;
        return this;
    }

    public Bygg byggerNaa(boolean byggerNaa) {
        this.byggerNaa = byggerNaa;
        return this;
    }

    public Bygg medDisplayname(String displayname) {
        this.displayname = displayname;
        return this;
    }

    public Bygg medStatus(String status) {
        this.status = status;
        return this;
    }

    public Bygg medJobbStartet(LocalDateTime jobbStartet) {
        this.jobbStartet = jobbStartet;
        return this;
    }

    public void medEndringer(List<Endring> endring) {
        this.endringer = endring;
    }

    @JsonGetter
    public Long getJobbId() {
        return jobbId;
    }

    @JsonGetter
    public boolean isByggerNaa() {
        return byggerNaa;
    }

    @JsonGetter
    public String getDisplayname() {
        return displayname;
    }

    @JsonGetter
    public String getStatus() {
        return status;
    }

    @JsonGetter
    public String getJobbStartet() {
        return jobbStartet.toString();
    }

    @JsonGetter
    public List<Endring> getEndringer() {
        return endringer;
    }
}
