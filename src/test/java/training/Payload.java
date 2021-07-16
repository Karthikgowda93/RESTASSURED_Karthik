package training;

public class Payload {

    public static String courses() {
        return """
                {
                "dashboard": {
                "purchaseAmount": 1010,
                "website": "rahulshettyacademy.com"
                },
                "courses": [
                {
                "title": "Selenium Python",
                "price": 50,
                "copies": 6
                },
                
                {
                "title": "Cypress",
                "price": 40,
                "copies": 4
                },
                                
                {
                "title": "RPA",
                "price": 45,
                "copies": 10
                },
                                
                {
                "title": "RestAssured",              
                "price": 50,
                "copies": 2
                }
                                
                ]
                                
                }
                                
                """;
    }

    public static String libraryPayload(){

        return """
              
                {
                "name":"LearnRest Assured wi2th Java",
                "isbn":"ee",
                "aisle":"33",
                "author":"Karthik K"
                }
                  """;

       // return payload;
    }


}
