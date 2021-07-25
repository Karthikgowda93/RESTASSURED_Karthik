package training;

import courses.GoogleApiproduct;
import courses.location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GoogleAPI {

    @BeforeTest
    public static void setup() {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
    }

    @Test
    public void googleApiSerilisation() {

        GoogleApiproduct gap = new GoogleApiproduct();
        /*        50,
                "Frontline house",
                "(+91) 983 893 3937",
                "29, side layout, cohen 09",
                "http://google.com",
                "French-IN",
                "s"
        );*/
        gap.setAccuracy(50);
        gap.setName("Frontline house");
        gap.setPhone_number("(+91) 983 893 3937");
        gap.setAddress("29, side layout, cohen 09");
        gap.getWebsite("http://google.com");
        gap.setLanguage("French-IN");
        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        gap.setLocation(l);
        List<String> typeList = new ArrayList<String>();
        typeList.add("shoe park");
        typeList.add("shop");
        gap.setTypes(typeList);


       /*Response response= given().queryParam("key","qaclick123")
                .body(gap)
                .when()
                .post("/maps/api/place/add/json")
                .then().extract().response();
                *//*.assertThat().statusCode(200);*//*

        Assertions.assertEquals(200,response.statusCode());
        System.out.println(response.statusCode());
        System.out.println(response.body().asString());*/


        // using re and response spec builders
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

      ResponseSpecification res =  new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification primary = given().spec(req).body(gap);
        Response response1= primary
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .extract().response();
        System.out.println(response1.asString());



    }


}
