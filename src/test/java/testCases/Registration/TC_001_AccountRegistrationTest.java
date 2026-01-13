package testCases.Registration;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.NewsletterSubscriptionPage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verify_account_registration(){
        logger.info("***** Starting TC001_AccountRegistrationTest *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("***** Clicked on MyAccount Link *****");

            hp.clickRegister();
            logger.info("***** Clicked on Register Link *****");

            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("***** Providing customer details *****");
            regpage.register();

            logger.info("***** Validating expected message *****");
            String confmgs = regpage.getConfirmationMsg();
            if(confmgs.equals("Your Account Has Been Created!")){
                Assert.assertTrue(true);
            }else{
                logger.error("Test failed...");
                logger.debug("Debug logs...");
                Assert.assertTrue(false);
            }

            //Assert.assertEquals(confmgs, "Your Account Has Been Created!!!");
        } catch (Exception e) {
            Assert.fail();
        }

        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }

    @Test(enabled = false)
    public void validate_register_empty_notification(){
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegister();

        AccountRegistrationPage arp = new AccountRegistrationPage(driver);
        arp.clickContinue();

        if(!"First Name must be between 1 and 32 characters!".isEmpty() &&
            !"Last Name must be between 1 and 32 characters!".isEmpty() &&
            !"E-Mail Address does not appear to be valid!".isEmpty() &&
            !"Telephone must be between 3 and 32 characters!".isEmpty() &&
            !"Password must be between 4 and 20 characters!".isEmpty()
        ){
            Assert.assertTrue(true);
        }else{
            Assert.fail();
        }
    }

    @Test(enabled = false)
    public void check_subscription_while_register(){
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("***** Clicked on MyAccount Link *****");

        hp.clickRegister();
        logger.info("***** Clicked on Register Link *****");

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

        logger.info("***** Providing customer details *****");
        regpage.register();
        regpage.clickContinueButton();
        regpage.click_subscribe_newsletter();

        NewsletterSubscriptionPage nsp = new NewsletterSubscriptionPage(driver);

        Assert.assertTrue(nsp.check_radio_isChecked());
        if(nsp.check_radio_isChecked()){
            System.out.println("RadioButton isSelected as Yes");
        }

    }
}
