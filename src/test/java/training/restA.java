package training;

import Models.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class restA {

    @BeforeAll
    public static void setUp(){
       RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void PostReq(){

        Product product = new Product(

                "Mr",
                "Kushal",
                115

        );
        /*Response response= given()
                           .header("Content-type", "application/json")
                           .and()
                           .body(product)
                           .when()
                           .post("/posts")
                           .then().extract().response();*/

        /*  Assertions.assertEquals(201,response.statusCode());
        Assertions.assertEquals("Kushal", response.jsonPath().getString("body"));
        Assertions.assertEquals("115",response.jsonPath().getString("userId"));
        Assertions.assertEquals("Mr",response.jsonPath().getString("title"));*/
        //  Assertions.assertAll("records.size()", response.jsonPath().);
        //    Assertions.assertEquals(101,response.jsonPath().getString("id"));

// to get the body in ressonse
        String response= given()
                .header("Content-type", "application/json")
                .and()
                .body(product)
                .when()
                .post("/posts")
                .then().extract().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String Id = js.getString("id");

        System.out.println(Id);

    }

    @Test
    public void getRequest() {
          Response response = given()
                            .contentType(ContentType.JSON)
                            .queryParam("id","101")
                            .when()
                            .get("/posts").then()
                            .extract().response();

        // to get the body in the response
      given().contentType(ContentType.JSON).queryParam("id","21").when().get("/posts").then().log().body();

       // deserialise
      //  given().contentType(ContentType.JSON).queryParam("id", "31").when().get("/posts").as(Product.class);


        /*Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("qui est esse",response.jsonPath().getString("title[1]"));*/
           }

    @Test
    public void  UpdateMethod(){

        Product product = new Product(

                "Mr",
                "Kushal",
                115,
                101

        );
        Response response = given()
                .header("Content-type","application/json")
                .and()
                .body(product)
                .when()
                .put("/posts/1")
                .then().extract().response();

       /* Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Awesome",response.jsonPath().getString("body"));*/

    }

    @Test
    public void Delete(){
        Response response= given()
                .header("Content-type","application/json")
                .when()
                .delete("/posts/1")
                .then().extract().response();

        Assertions.assertEquals(200, response.statusCode());

    }

}
