package pages.app;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.PageBase;

import static com.codeborne.selenide.Selenide.$;

public class Login_Page extends PageBase {
    public static final String URL = "https://app.staging-bemakers.com/login";
    public SelenideElement email_Input = $(By.xpath("//input[@name='email']"));
    public SelenideElement password_Input = $(By.xpath("//input[@name='password']"));
    public SelenideElement continue_Btn = $(By.xpath("//button[@type='submit']"));

}



