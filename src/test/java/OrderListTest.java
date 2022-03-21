import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderListTest {
    OrderClient orderClient;


    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void orderListGet () {
        ValidatableResponse ordersListResponse = orderClient.ordersList();
        int statusCode = ordersListResponse.extract().statusCode();
        String response = ordersListResponse.extract().toString();


        assertThat("Can't get list of orders", statusCode, equalTo(200));
        assertThat("Response body is null", response != null);
    }
}
