package awesome.dashboard.jenkins.data;

import com.fasterxml.jackson.annotation.JsonGetter;

@SuppressWarnings("unused")
public class Endring {

    private String kommentar;
    private String author;

    public Endring medKommentar(String kommentar) {
        this.kommentar = kommentar;
        return this;
    }

    public Endring medAuthor(String author) {
        this.author = author;
        return this;
    }


    @JsonGetter
    public String getKommentar() {
        return kommentar;
    }

    @JsonGetter
    public String getAuthor() {
        return author;
    }
}
