package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String randomString(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber(){
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphaNumeric(){
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return (str + "@" + num);
    }
}
