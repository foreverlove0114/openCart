package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewsletterSubscriptionPage extends BasePage{
    public NewsletterSubscriptionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='newsletter'][value='1']")
    WebElement newsletterYesRadioCss;

    public boolean check_radio_isChecked(){
        return newsletterYesRadioCss.isSelected();
    }
}
