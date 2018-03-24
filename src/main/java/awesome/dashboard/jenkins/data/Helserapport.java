package awesome.dashboard.jenkins.data;

import com.fasterxml.jackson.annotation.JsonGetter;

@SuppressWarnings("unused")
public class Helserapport {

    private String byggstabilitet;
    private String testresultat;

    public Helserapport medByggstabilitet(String byggstabilitet) {
        this.byggstabilitet = byggstabilitet;
        return this;
    }

    public Helserapport medTestresultat(String testresultat) {
        this.testresultat = testresultat;
        return this;
    }

    @JsonGetter
    public String getByggstabilitet() {
        return byggstabilitet;
    }

    @JsonGetter
    public String getTestresultat() {
        return testresultat;
    }
}
