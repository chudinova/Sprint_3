import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

        assertThat("Can't get list of orders", statusCode, equalTo(200));
    }
}
