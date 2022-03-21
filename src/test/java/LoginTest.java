import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest {
    CourierClient courierClient;
    Courier courier;
    int courierId;
    CourierCredentials credentials;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = courier.getRandom();
        credentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    @Test
    public void courierCanLoginWithValidCredentials() {
        courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(credentials);
        int statusCode = loginResponse.extract().statusCode();
        courierId = loginResponse.extract().path("id");

        assertThat("Courier cannot login", statusCode, equalTo(200));
        assertThat("Courier ID is incorrect", courierId, is(not(0)));
            }

    @Test
    public void courierCanNotLoginWithNotExitingCredentials() {
        ValidatableResponse loginResponse = courierClient.login(credentials);
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Status code is not 404", statusCode, equalTo(404));
        assertThat("Message is incorrect", message, equalTo("Учетная запись не найдена"));

    }

    @Test
    public void courierCanNotLoginWithoutLogin() {
        CourierCredentials credentialsWithNoLogin = new CourierCredentials("", courier.getPassword());
        ValidatableResponse loginResponse = courierClient.login(credentialsWithNoLogin);
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Status code is not 404", statusCode, equalTo(400));
        assertThat("Message is incorrect", message, equalTo("Недостаточно данных для входа"));

    }

    @Test
    public void courierCanNotLoginWithoutPassword() {
        CourierCredentials credentialsWithNoPassword = new CourierCredentials(courier.getLogin(), "");
        ValidatableResponse loginResponse = courierClient.login(credentialsWithNoPassword);
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");

        assertThat("Status code is not 404", statusCode, equalTo(400));
        assertThat("Message is incorrect", message, equalTo("Недостаточно данных для входа"));

    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}
