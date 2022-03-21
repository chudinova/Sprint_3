import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class Courier {
    private String login;
    private String password;
    private String firstName;


    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Step("Создание рандомного курьера")
    public static Courier getRandom(){

        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);

        Allure.addAttachment("Логин: ", courierLogin);
        Allure.addAttachment("Пароль: ", courierPassword);
        Allure.addAttachment("Имя: ", courierFirstName);

        return new Courier(courierLogin, courierPassword, courierFirstName);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
