package training;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraAPIEx {

    @BeforeTest
    public static void setUp() {

        RestAssured.baseURI = "http://localhost:8080";
    }


    @Test
    public void LoginAndAddCommentApi() {
        // in order to avoid any certificate issue when https is used in URI need to send "relaxedHTTPSValidation()"
        // Session filter class used to pass info into the api, in the below case 'session' used to pass login session id into adding
        //comments API to to be successful (like cookie in this case as session ID)
        SessionFilter session = new SessionFilter();

        // login API
        given().header("Content-Type", "application/json")
                .and()
                .body("""
                        {
                            "username": "karthik.k0493",
                            "password": "Karthik@93"
                        }
                        """)
                .filter(session)
                .when()
                .post("/rest/auth/1/session")
                .then()
                .log().all().extract().response().asString();

// to add comments in the Ticket


        String response =given().pathParam("IssueId", "10101")
                .header("Content-Type", "application/json")
                .and()
                .body("""
                        {
                            "body": "Adding the comments to test",
                            "visibility": {
                                "type": "role",
                                "value": "Administrators"
                            }
                        }
                        """)
                .filter(session)
                .when()
                .post("/rest/api/2/issue/{IssueId}/comment")
                .then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String commentId = js.get("id");
        String expectedComment= js.getString("body");


        // to add the attachmenmts there is one more command "multipart" and header have to pass as "multipart/form-data" whenever adding
        // attachment in the api

        given().header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data") // whenever multipart method used have to send this header
                .multiPart("file",new File("attachment.txt"))   // this is the method used to send the attachment in Rest Api
                .filter(session)
                .pathParam("IssueID","10101")
                .when()
                .post("/rest/api/2/issue/{IssueID}/attachments")
                .then().log().all().assertThat().statusCode(200);

        // get the issue using the path and query param

        String getIssueResponse = given().filter(session).pathParam("IssueID", "10101")
                .queryParam("fields", "comment")
                .when()
                .get("/rest/api/2/issue/{IssueID}")
                .then().log().body().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(getIssueResponse);
        int commentsCount = js1.get("fields.comment.comments.size()");

        for (int i =0;i<commentsCount;i++){

        String commentsId =js1.get("fields.comment.comments["+i+"].id").toString();
        if(commentsId.equalsIgnoreCase(commentId)){
            String commentsBody = js1.get("fields.comment.comments["+i+"].body");
            System.out.println(commentsBody);
            Assert.assertEquals(expectedComment,commentsBody);
        }


        }



    }

}
