package com.ist.stepdef;

import com.ist.config.ConfigReader;
import com.ist.pages.LoginPage;
import com.ist.utils.WaitFor;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class LoginStepDef {
	private ConfigReader config;
    private WebDriver driver;
    private WaitFor wait;
    private LoginPage loginPage;

    @Before
    public void setUp() {
    	config = new ConfigReader();
        String browser = config.readProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        wait = new WaitFor(driver);
        loginPage = new LoginPage(driver,wait);   
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on login page")
    public void userIsOnLoginPage() {
        String baseUrl = System.getProperty("base.url", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get(baseUrl);
        wait.visibilityOfElement(loginPage.getUserNameField());
    }

    @When("User enters username {string}")
    public void userEntersUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("User enters password {string}")
    public void userEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("User clicks on Login button")
    public void userClicksOnLoginButton() {
        loginPage.clickLogin();
    }

    @Then("User should navigate to Orange HRM home page")
    public void userShouldNavigateToOrangeHrmHomePage() {
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Navigation to dashboard failed");
    }

    @Then("User verifies Forgot password link display")
    public void userVerifiesForgotPasswordLinkDisplay() {
        Assert.assertTrue(loginPage.isForgotPasswordLinkPresent(), "Forgot password link is not displayed");
    }
}