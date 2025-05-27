package com.ist.pages;


import com.ist.utils.WaitFor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LAD5FaultTablePage {
    private WebDriver driver;
    private final WaitFor wait;

    // Locator for the LAD5 fault table
    @FindBy(xpath = "//table[@id='customers']") // Adjust ID based on actual HTML
    private WebElement lad5FaultTable;

    
    // Locators for rows and columns
    @FindBy(xpath = "//table[@id='customers']//tr") // All rows
    private List<WebElement> tableRows;

    @FindBy(xpath = "//table[@id='customers']//tr[1]//th") // Header columns
    private List<WebElement> tableHeaders;

    // Locator for all table cells
    @FindBy(xpath = "//table[@id='customers']//td") // All cells
    private List<WebElement> tableCells;

    public LAD5FaultTablePage(WebDriver driver, WaitFor wait) {
        this.driver = driver;
        if (driver == null) {
            throw new IllegalStateException("WebDriver is null in LoginPageFactory");
        }
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }

    public void navigateToPage(String url) {
        driver.get(url);
    }

    public boolean isLAD5FaultTableDisplayed() {
        try {
            wait.visibilityOfElement(lad5FaultTable);
            return lad5FaultTable.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getRowCount() {
        wait.visibilityOfElements(tableRows);
        return tableRows.size();
    }

    public int getColumnCount() {
        wait.visibilityOfElements(tableHeaders);
        return tableHeaders.size();
    }

    public boolean containsFaultMessage(String expectedMessage) {
        wait.visibilityOfElements(tableCells);
        for (WebElement cell : tableCells) {
            if (cell.getText().toLowerCase().contains(expectedMessage.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public WebElement gettableField() {
        return lad5FaultTable;
    }
    
    // 1. Print the entire table (headers and all rows)
    public void printEntireTable() {
        wait.visibilityOfElements(tableRows);
        System.out.println("=== LAD5 Fault Table ===");
        // Print headers
        List<String> headers = tableHeaders.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("Headers: " + String.join(" | ", headers));

        // Print each row
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> cells = tableRows.get(i).findElements(org.openqa.selenium.By.tagName("td"));
            if (!cells.isEmpty()) { // Skip header row if it has no <td>
                List<String> cellValues = cells.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                System.out.println("Row " + (i + 1) + ": " + String.join(" | ", cellValues));
            }
        }
        System.out.println("=====================");
    }
    

    // Return table data as List<List<String>> (headers and rows)
    public  void getTableData() {
    	// List<WebElement> rows = driver.findElements(By.xpath("(//table)[1]/tbody/tr"));

         // Create a List of Lists to store table data
         List<List<String>> tableData = new ArrayList<>();

         // Loop through each row
         for (WebElement row : tableRows) {
             List<WebElement> columns = row.findElements(By.tagName("td"));
             System.out.println("columns.toString()"+columns.toString());
             List<String> rowData = new ArrayList<>();
             
             System.out.println(rowData.toString());
             // Loop through each cell and store text
             for (WebElement cell : columns) {
            	 System.out.println(cell);
                 rowData.add(cell.getText());
                 System.out.println(cell.getText());
             }

             // Add row data to tableData list
             tableData.add(rowData);
         }

         // Print extracted table data
         System.out.println("tableData :"+tableData);


        }

    // 2. Print rows containing a specific message
    public void printRowContainingMessage(String expectedMessage) {
        wait.visibilityOfElements(tableRows);
        System.out.println("=== Rows Containing '" + expectedMessage + "' ===");
        boolean found = false;
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> cells = tableRows.get(i).findElements(org.openqa.selenium.By.tagName("td"));
            if (!cells.isEmpty()) {
                boolean containsMessage = cells.stream()
                        .anyMatch(cell -> cell.getText().toLowerCase().contains(expectedMessage.toLowerCase()));
                if (containsMessage) {
                    List<String> cellValues = cells.stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList());
                    System.out.println("Row " + (i + 1) + ": " + String.join(" | ", cellValues));
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No rows found containing '" + expectedMessage + "'.");
        }
        System.out.println("=====================");
    }

    // 3. Print a specific row by index (1-based)
    public void printRowByIndex(int rowIndex) {
        wait.visibilityOfElements(tableRows);
        if (rowIndex < 1 || rowIndex > tableRows.size()) {
            System.out.println("Invalid row index: " + rowIndex + ". Table has " + tableRows.size() + " rows.");
            return;
        }
        System.out.println("=== Row " + rowIndex + " ===");
        List<WebElement> cells = tableRows.get(rowIndex - 1).findElements(org.openqa.selenium.By.tagName("td"));
        if (cells.isEmpty()) {
            System.out.println("Row " + rowIndex + " is empty or a header row.");
        } else {
            List<String> cellValues = cells.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            System.out.println("Row " + rowIndex + ": " + String.join(" | ", cellValues));
        }
        System.out.println("=====================");
    }

    // 4. Print a specific column by index (1-based)
    public void printColumnByIndex(int columnIndex) {
        wait.visibilityOfElements(tableRows);
        int columnCount = getColumnCount();
        if (columnIndex < 1 || columnIndex > columnCount) {
            System.out.println("Invalid column index: " + columnIndex + ". Table has " + columnCount + " columns.");
            return;
        }
        System.out.println("=== Column " + columnIndex + " ===");
        // Print header for the column
        String header = tableHeaders.get(columnIndex - 1).getText();
        System.out.println("Header: " + header);
        // Print cells in the column
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> cells = tableRows.get(i).findElements(org.openqa.selenium.By.tagName("td"));
            if (!cells.isEmpty() && cells.size() >= columnIndex) {
                System.out.println("Row " + (i + 1) + ": " + cells.get(columnIndex - 1).getText());
            }
        }
        System.out.println("=====================");
    }

    // 5. Print table headers only
    public void printTableHeaders() {
        wait.visibilityOfElements(tableHeaders);
        System.out.println("=== Table Headers ===");
        List<String> headers = tableHeaders.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        System.out.println("Headers: " + String.join(" | ", headers));
        System.out.println("=====================");
    }

    // 6. Print table summary (row count, column count, total cells)
    public void printTableSummary() {
        wait.visibilityOfElements(tableRows);
        int rowCount = getRowCount();
        int columnCount = getColumnCount();
        int cellCount = tableCells.size();
        System.out.println("=== Table Summary ===");
        System.out.println("Rows: " + rowCount);
        System.out.println("Columns: " + columnCount);
        System.out.println("Total Cells: " + cellCount);
        System.out.println("=====================");
    }

    // 7. Print table in CSV-like format
    public void printTableAsCSV() {
        wait.visibilityOfElements(tableRows);
        System.out.println("=== Table in CSV Format ===");
        // Print headers
        List<String> headers = tableHeaders.stream()
                .map(WebElement::getText)
                .map(text -> "\"" + text.replace("\"", "\"\"") + "\"") // Escape quotes
                .collect(Collectors.toList());
        System.out.println(String.join(",", headers));

        // Print rows
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> cells = tableRows.get(i).findElements(org.openqa.selenium.By.tagName("td"));
            if (!cells.isEmpty()) {
                List<String> cellValues = cells.stream()
                        .map(WebElement::getText)
                        .map(text -> "\"" + text.replace("\"", "\"\"") + "\"") // Escape quotes
                        .collect(Collectors.toList());
                System.out.println(String.join(",", cellValues));
            }
        }
        System.out.println("=====================");
    }

    // 8. Print rows matching a regular expression
    public void printRowsMatchingRegex(String regexPattern) {
        wait.visibilityOfElements(tableRows);
        System.out.println("=== Rows Matching Regex '" + regexPattern + "' ===");
        boolean found = false;
        for (int i = 0; i < tableRows.size(); i++) {
            List<WebElement> cells = tableRows.get(i).findElements(org.openqa.selenium.By.tagName("td"));
            if (!cells.isEmpty()) {
                boolean matchesRegex = cells.stream()
                        .anyMatch(cell -> cell.getText().matches(regexPattern));
                if (matchesRegex) {
                    List<String> cellValues = cells.stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList());
                    System.out.println("Row " + (i + 1) + ": " + String.join(" | ", cellValues));
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No rows found matching regex '" + regexPattern + "'.");
        }
        System.out.println("=====================");
    }
    
    
    
    
    
    
}