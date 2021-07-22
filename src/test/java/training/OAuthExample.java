package training;

import courses.api;
import courses.complexJsonExampleforOAuthExample;
import courses.webAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthExample {

    @Test
    public void OAuth() {
        // json response titles which can be stored in an array so that compare the final actual response
        String[] expected ={"Selenium Webdriver Java","Cypress","Protractor"};
        // to get the code which has tgo go to browser we need to use selenium concept
        // but as google not allowing automation bot to login into gmail account
        // we need to manually hit in the browser and get the code and pass it in

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjV-ocpUQQg6ND1i7EcjJzHB4ZxyFAcXDkqgC3tF3-mf4E9y30aaYu6dMjswWrQWQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
       String  partUrl = url.split("code=")[1];
       String code = partUrl.split("&scope")[0];
       // To get the Access_token
        // add urlEncodingEnabled(false) in order not to convert the code which is in alphanumeric  and special charcters format to Aphabetic format
        String GettingAccessToken = given().urlEncodingEnabled(false)
                .queryParam("code", code)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
                .when()
                .post("https://www.googleapis.com/oauth2/v4/token")
                .asString();

        System.out.println(GettingAccessToken);
        JsonPath js = new JsonPath(GettingAccessToken);
        String Access_token = js.getString("access_token");

        // Finally after getting the access_token when deserialisation is not applied

        /*String Finalresponse = given().queryParam("access_token", Access_token)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();

        System.out.println(Finalresponse);*/

        // when deserialisation is defined

        complexJsonExampleforOAuthExample cj = given().queryParam("access_token", Access_token).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .as(complexJsonExampleforOAuthExample.class);

        System.out.println("Expertise :"+cj.getExpertise());
        System.out.println("Instructor :"+cj.getInstructor());
        System.out.println("LinkedIn :"+cj.getLinkedIn());

        // hard coated
        cj.getCourses().getWebAutomation().get(2).getCourseTitle();

        List<api> getapiCourses =cj.getCourses().getApi();
        for (int i=0;i<getapiCourses.size();i++){
           String CTitle = getapiCourses.get(i).getCourseTitle();
            if(CTitle.equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println("The price of course "+CTitle+" : "+getapiCourses.get(i).getPrice());
            }
        }

        // print all the values of webAutomation courses
        ArrayList<String> al =new ArrayList<String>();
        List<webAutomation> getwebCourses = cj.getCourses().getWebAutomation();

        for (int k =0;k<getwebCourses.size();k++){
            al.add(getwebCourses.get(k).getCourseTitle());
            //System.out.println("The "+k+" course Title is : "+Title);
        }

       // convert array to arraylist *****interview question**************************************************************
      List<String> expectedList = Arrays.asList(expected);
        Assert.assertTrue(al.equals(expectedList));

    }


}
