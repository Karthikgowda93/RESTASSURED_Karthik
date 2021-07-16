package Models;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import training.Payload;

public class JsonPathpase {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.courses());
        // To Print the courses size
        int count = js.getInt("courses.size()");
        System.out.println(count);
        // print purchase amount
        int total = js.getInt("dashboard.purchaseAmount");
        System.out.println(total);

        // to print the course title of the first data
        String st_course_title = js.get("courses[2].title");
        System.out.println(st_course_title);

        // To print the course title of all the elements in JSon

        for (int i = 0; i < count; i++) {
            String title = js.get("courses[" + i + "].title");
            System.out.println(title);

            int prices = js.getInt("courses[" + i + "].price");
            System.out.println(prices);
        }

        // print no of copies sold by RPA

        for (int i = 0; i < count; i++) {
            String title = js.get("courses[" + i + "].title");
          if(title.equalsIgnoreCase("RPA")){
              int copies = js.getInt("courses["+i+"].copies");
              System.out.println("No of Copies of RPA sold -->" + copies);
              break;
          }
        }

        // verify the sum of all courses matches the total purchase amount
        int sum =0;
        for (int i = 0; i < count; i++) {
            int totalOfIndividuals = js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
             // System.out.println(totalOfIndividuals);
              sum = sum + totalOfIndividuals;
        }
        System.out.println(sum);
       /* if (sum == total ) {
            System.out.println("The total matches with the purchase amount");
        }else {
            System.out.println("The total not matches with the purchase amount");
        }*/
        Assertions.assertEquals(sum,total);

    }


}
