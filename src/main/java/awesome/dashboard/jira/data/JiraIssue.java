package awesome.dashboard.jira.data;

import com.fasterxml.jackson.annotation.JsonGetter;

@SuppressWarnings("unused")
public class JiraIssue {

    private String key;
    private Long storypoint;
    private String issueType;
    private Status status;

    public JiraIssue medIssueType(String issueType) {
        this.issueType = issueType;
        return this;
    }

    public JiraIssue medStorypoint(Long storypoint) {
        this.storypoint = storypoint;
        return this;
    }

    public JiraIssue medKey(String key) {
        this.key = key;
        return this;
    }

    public JiraIssue medStatus(Status status) {
        this.status = status;
        return this;
    }

    @JsonGetter
    public String getIssueType() {
        return issueType;
    }

    @JsonGetter
    public String getKey() {
        return key;
    }

    @JsonGetter
    public Long getStorypoint() {
        return storypoint;
    }

    @JsonGetter
    public Status getStatus() {
        return status;
    }

}
