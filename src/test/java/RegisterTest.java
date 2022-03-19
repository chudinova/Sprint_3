import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class RegisterTest {
    CourierClient courierClient;
    CourierGenerator courierGenerator;
    Courier courier;
    int courierId;
    CourierCredentials credentials;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courierGenerator = new CourierGenerator();
        courier = courierGenerator.getRandom();
        credentials = new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    @Test
    public void registerWithValidCredentials() {
        ValidatableResponse loginResponse1 = courierClient.create(courier);
        int statusCode = loginResponse1.extract().statusCode();
        boolean ok = loginResponse1.extract().path("ok");

        ValidatableResponse loginResponse2 = courierClient.login(credentials);
        courierId = loginResponse2.extract().path("id");


        assertThat("Courier cannot register", statusCode, equalTo(201));
        assertThat("JSON is incorrect", ok, is(true));
    }

    @Test
    public void registerWithExitingLogin() {
        courierClient.create(courier);
        ValidatableResponse loginResponse1 = courierClient.login(credentials);
        courierId = loginResponse1.extract().path("id");
        ValidatableResponse loginResponse2 = courierClient.create(courier);
        int statusCode = loginResponse2.extract().statusCode();
        String message = loginResponse2.extract().path("message");

        assertThat("Status code is incorrect", statusCode, equalTo(409));
        assertThat("Message is incorrect", message, equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void registerWithNoPassword() {
        Courier courierWithNoPassword = new Courier(courier.getLogin(),"",courier.getFirstName());
        ValidatableResponse loginResponse = courierClient.create(courierWithNoPassword);
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");


        assertThat("Status code is incorrect", statusCode, equalTo(400));
        assertThat("Message is incorrect", message, equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void registerWithNoLogin() {
        Courier courierWithNoLogin = new Courier("", courier.getPassword(), courier.getFirstName());
        ValidatableResponse loginResponse = courierClient.create(courierWithNoLogin);
        int statusCode = loginResponse.extract().statusCode();
        String message = loginResponse.extract().path("message");


        assertThat("Status code is incorrect", statusCode, equalTo(400));
        assertThat("Message is incorrect", message, equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}

