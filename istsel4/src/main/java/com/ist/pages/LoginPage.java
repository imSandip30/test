package com.ist.pages;


import com.ist.utils.WaitFor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;
    private final WaitFor wait;

    @FindBy(name = "username")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement passWord;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    private WebElement forgotPassword;

    public LoginPage(WebDriver driver, WaitFor wait) {
        this.driver = driver;
        if (driver == null) {
            throw new IllegalStateException("WebDriver is null in LoginPageFactory");
        }
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public LoginPage enterUsername(String uname) {
        wait.visibilityOfElement(userName);
        userName.sendKeys(uname);
        return this;
    }

    public LoginPage enterPassword(String pwd) {
        wait.visibilityOfElement(passWord);
        passWord.sendKeys(pwd);
        return this;
    }

    public LoginPage clickLogin() {
        wait.elementToBeClickable(loginButton);
        loginButton.click();
        return this;
    }

    public boolean isForgotPasswordLinkPresent() {
        wait.visibilityOfElement(forgotPassword);
        return forgotPassword.isDisplayed();
    }

    public WebElement getUserNameField() {
        return userName;
    }
}