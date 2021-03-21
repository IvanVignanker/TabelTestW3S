import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;

public class BaseTest {

    WebDriver driver;


    @BeforeMethod
    public void openBrowser() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1800x900");
        driver = new ChromeDriver(options);

        driver.get("https://www.w3schools.com/html/html_tables.asp");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }



    public String getTableCellTextByXpath(WebElement table, int searchColumn,
                                          String searchText, int returnColumnText) throws Exception{

        //*[@id="customers"]/tbody/tr[2]/td[1]
        //*[@id="customers"]/tbody/tr[2]/td[2]
        //*[@id="customers"]/tbody/tr[2]/td[3]
        int tr = findNumberOfRow(table,searchColumn,searchText,returnColumnText);

        String returnedText = table.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr["+ Integer.toString(tr)
                + "]/td[" + Integer.toString(returnColumnText) + "]")).getText();
        System.out.println(returnedText);
        return returnedText;
    }

    public int findNumberOfRow (WebElement table, int searchColumn,
                                String searchText, int returnColumnText){
        int numberOfRow = 0;
        String expectedText;

        List<WebElement> allRows = table.findElements(By.tagName("tr"));
                System.out.println("Total No of rows are : " + allRows.size());

        for (int i = 1; i < allRows.size();i++) {
            expectedText = table.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[" +
                            Integer.toString(i + 1) + "]/td[1]")).getText();

            if (expectedText.equals(searchText)) {
                numberOfRow = i+1;

            }
            System.out.println(expectedText);
            break;
        }
        return numberOfRow;
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn,
                                       String searchText, int returnColumnText, String expectedText) throws Exception {
        if (getTableCellTextByXpath(table, searchColumn, searchText, returnColumnText).equals(expectedText)){
            System.out.println("The values are the same.");
            return true;
        }
        System.out.println("The values do not match");
        return false;
    }


   @AfterMethod
    public void tearDown() {
    driver.quit();
   }



}
