import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TableTest extends BaseTest {

        BaseTest tb = new BaseTest();


        @Test
        @Parameters({"table", "searchColumn", "searchText", "returnColumnText", "expectedText"})
        public void tableTest() throws Exception {

                /*  driver.findElement(By.xpath("//table[@id='customers'])"));*/
                tb.verifyTableCellText(driver.findElement(By.xpath("//*[@id=\"customers\"]")),
                        1,
                        "Alfreds Futterkiste",
                        3,
                        "Germany");
        }
}
