package com.ist.stepdef;


import com.ist.config.ConfigReader;
import com.ist.pages.LAD5FaultTablePage;
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

public class LAD5FaultTableStepDef {
	private ConfigReader config;
    private WebDriver driver;
    private WaitFor wait;
    private LAD5FaultTablePage lAD5FaultTablePage;

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
        lAD5FaultTablePage = new LAD5FaultTablePage(driver,wait);   
    }

    @After
    public void tearDown() {
        if (driver != null) {
           // driver.quit();
        }
    }

 

  
    @Given("I am on the page with the LAD5 fault table")
    public void i_am_on_the_page_with_the_lad5_fault_table() {
    	   String baseUrl = System.getProperty("base.url", "https://www.w3schools.com/html/html_tables.asp");
           driver.get(baseUrl);
           wait.visibilityOfElement(lAD5FaultTablePage.gettableField());
       }


    @Then("the LAD5 fault table should be displayed")
    public void the_lad5_fault_table_should_be_displayed() {
        Assert.assertTrue(lAD5FaultTablePage.isLAD5FaultTableDisplayed());
    }

    @Then("the LAD5 fault table should have at least {int} rows and {int} columns")
    public void the_lad5_fault_table_should_have_at_least_rows_and_columns(int minRows, int minColumns) {
        int actualRows = lAD5FaultTablePage.getRowCount();
        int actualColumns = lAD5FaultTablePage.getColumnCount();
        System.out.println("actualRows"+actualRows +"actualColumns"+ actualColumns);
        Assert.assertTrue(actualRows >= minRows);
        Assert.assertTrue(actualColumns >= minColumns);
    }

    @Then("the LAD5 fault table should contain the message {string}")
    public void the_lad5_fault_table_should_contain_the_message(String expectedMessage) {
    	System.out.println("expectedMessage"+expectedMessage );
        Assert.assertTrue(lAD5FaultTablePage.containsFaultMessage(expectedMessage));
        lAD5FaultTablePage.getTableData();
      /*  lAD5FaultTablePage.printEntireTable();
        lAD5FaultTablePage.printRowContainingMessage("Austria");
        lAD5FaultTablePage.printRowByIndex(2); 
        lAD5FaultTablePage.printColumnByIndex(2);
        lAD5FaultTablePage.printTableHeaders(); 
        lAD5FaultTablePage.printTableSummary();
        lAD5FaultTablePage.printTableAsCSV();
        lAD5FaultTablePage.printRowsMatchingRegex("Au");*/

    }
}