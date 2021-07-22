package courses;

import java.util.List;

public class CoursesJson {

    // list<webAutomation> "list" is because webAutomation, api,mobile will give the array of response in json response
    private List<webAutomation> webAutomation;
    private List<api> api;
    private List<mobile> mobile;

    public List<courses.webAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<courses.webAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<courses.api> getApi() {
        return api;
    }

    public void setApi(List<courses.api> api) {
        this.api = api;
    }

    public List<courses.mobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<courses.mobile> mobile) {
        this.mobile = mobile;
    }
}
