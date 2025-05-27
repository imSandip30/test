package com.ist.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class WaitFor {
    private final FluentWait<WebDriver> wait;

    public WaitFor(WebDriver driver) {
       
        Duration timeout = Duration.ofSeconds(Integer.getInteger("wait.timeout", 10));
        this.wait = new WebDriverWait(driver,timeout)
        	//.withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(200))
            .ignoring(NoSuchElementException.class);
    }

    public void visibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public void visibilityOfElements(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean invisibilityOfElement(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void elementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void textToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}