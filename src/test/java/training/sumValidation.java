package training;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class sumValidation {

    @Test
    public void sumValidate(){

        JsonPath js = new JsonPath(Payload.courses());
        int count = js.getInt("courses.size()");
        System.out.println(count);
        int sum =0;
        for(int i=0;i<count;i++){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price*copies;
            sum = sum +amount;
        }
        System.out.println(sum);
        int total = js.getInt("dashboard.purchaseAmount");
        Assertions.assertEquals(sum , total);

    }


}
