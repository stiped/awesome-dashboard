package awesome.dashboard.jenkins.data;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unused")
public class Jenkins {

    private String navn;
    private Helserapport helserapport;
    private String byggfarge;
    private List<Bygg> byggliste;

    public Jenkins() {
        this.byggliste = new ArrayList<>();
    }

    public Jenkins medByggfarge(String byggfarge) {
        this.byggfarge = byggfarge;
        return this;
    }

    public Jenkins medNavn(String navn) {
        this.navn = navn;
        return this;
    }

    public Jenkins medHelse(Helserapport helserapport) {
        this.helserapport = helserapport;
        return this;
    }

    public void medBygg(Bygg bygginfo) {
        this.byggliste.add(bygginfo);
    }

    @JsonGetter
    public List<Bygg> bygg() {
        byggliste.sort(Comparator.comparing(Bygg::getJobbId).reversed());
        return byggliste;
    }

    @JsonGetter
    public String getJobbnavn() {
        return navn;
    }

    @JsonGetter
    public Helserapport getHelserapport() {
        return helserapport;
    }

    @JsonGetter
    public String getByggindikator() {
        return byggfarge;
    }
}
