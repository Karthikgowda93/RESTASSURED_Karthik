package training;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthExample {

    @Test
    public void OAuth() {

        // to get the code which has tgo go to browser we need to use selenium concept
        // but as google not allowing automation bot to login into gmail account
        // we need to manually hit in the browser and get the code and pass it in

        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhrU00c-1gG1dON2XG9iA__tyAYVc0XtSl-9YUOWvz7z0tgWTmelUPNgym1O3oc7A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=none#";

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

        // Finally after getting the access_token
        String Finalresponse = given().queryParam("access_token", Access_token)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .asString();
        System.out.println(Finalresponse);


    }


}
