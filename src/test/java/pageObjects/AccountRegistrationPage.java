package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtLasttname;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement buttonContinue;

    @FindBy(linkText = "Subscribe / unsubscribe to newsletter")
    WebElement linkSubscribe;

    @FindBy(xpath = "//label[normalize-space()='Yes']")
    WebElement checkboxSubscribe;

    public void setFirstName(String fname){
        txtFirstname.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLasttname.sendKeys(lname);

    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);

    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);

    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);

    }

    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);

    }

    public void setPrivacyPolicy() {
        chkdPolicy.click();

    }

    public void clickContinue() {
        //sol1
        btnContinue.click();
    }

    public String getConfirmationMsg(){
        try{
            return  (msgConfirmation.getText());
        }catch(Exception e){
            return (e.getMessage());
        }
    }

    public void clickContinueButton(){
        buttonContinue.click();
    }

    public void click_subscribe_newsletter(){
        linkSubscribe.click();
    }

    public void click_checkbox_subscribe(){
        checkboxSubscribe.click();
    }

    public void register(){
        setFirstName(randomString().toUpperCase());
        setLastName(randomString().toUpperCase());
        setEmail(randomString() + "@gmail.com");
        setTelephone(randomNumber());

        String password = randomAlphaNumeric();
        setPassword(password);
        setConfirmPassword(password);
        click_checkbox_subscribe();
        setPrivacyPolicy();

        clickContinue();
    }

}
