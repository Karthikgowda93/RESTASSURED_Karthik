package training;

import Models.Product;
import Models.productsPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class LibraryApi {

    @BeforeTest
    public static void setUp() {
        RestAssured.baseURI = "http://216.10.245.166/Library/Addbook.php";
    }

    @Test(dataProvider = "InputJson")
    public void postNewBook(String name, String isbn, String aisle, String author) {

        //  RestAssured.baseURI = "http://216.10.245.166";
        productsPayload payload = new productsPayload(
                name,
                isbn,
                aisle,
                author
        );

        String response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(payload)
                .when()
                .post("/Library/Addbook.php")
                .then()
                .extract().response().asString();
        //   Assertions.assertEquals(200,response.statusCode());
        //     Assertions.assertEquals("kar334", response.jsonPath().getString("ID"));
        System.out.println(response);

        JsonPath js = reusableArtifacts.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }


    // used when for API types where it accepts always same input(ex Json input in here)
    @Test
    public void postNewBookByJsonFileInput() throws IOException {


        String response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(returnJsonIput("C:\\Users\\karthikk\\Desktop\\REST ASSURED\\input.json"))
                .when()
                .post("/Library/Addbook.php")
                .then()
                .extract().response().asString();
        //   Assertions.assertEquals(200,response.statusCode());
        //     Assertions.assertEquals("kar334", response.jsonPath().getString("ID"));
        System.out.println(response);

        JsonPath js = reusableArtifacts.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }
    public static String returnJsonIput(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }


    @Test(dataProvider = "deleteData")
    public void DeleteBook(String id) {

        productsPayload payload2 = new productsPayload(
                id
        );
        String response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(payload2)
                .when()
                .post("/Library/DeleteBook.php")
                .then()
                .extract().response().asString();
        System.out.println(response);

    }

    @DataProvider(name="deleteData")
    public Object[][] deleteBookPayload() {
        return new Object[][]{{"kou33"},{"kargh"},{"kri526"}};
    }

    @DataProvider(name = "InputJson")
    public Object[][] inputData() {
        return new Object[][]{{"learn something", "kou", "33", "Koushik"}, {"learn some", "kar", "gh", "Karthik"}, {"Get Rest", "kri", "526", "Appa"}};
    }

}
