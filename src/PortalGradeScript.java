import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;


public class PortalGradeScript {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        Scanner input = new Scanner(System.in);

        String baseUrl = "https://portal.aait.edu.et";
        String gradeReportUrl = "https://portal.aait.edu.et/Grade/GradeReport";

        System.out.print("Enter your username: ");
        String myUsername = input.next();

        System.out.print("Enter your password: ");
        String myPassword = input.next();

        driver.get(baseUrl);

        //signing into my account
        driver.findElement(By.id("UserName")).sendKeys(myUsername);
        driver.findElement(By.id("Password")).sendKeys(myPassword);
        driver.findElement(By.className("btn-success")).click();

        //go to grade report page
        driver.navigate().to(gradeReportUrl);

        //finding all tr elements in the page
        List<WebElement> rows = driver.findElements(By.tagName("tr"));

        //declaring a string builder to store the grade report
        StringBuilder gradeReport;
        gradeReport = new StringBuilder();

        //traversing each row and appending values
        for (WebElement row :
                rows) {
            for (WebElement td :
                    row.findElements(By.tagName("td"))) {
                gradeReport.append(td.getText()).append("  ");
            }
            gradeReport.append("\n");
        }

        //writing to a file
        String fileNameToStoreGradeReport = ".\\src\\GradeReport.txt";

        try {
            Writer writer = new FileWriter(fileNameToStoreGradeReport);
            writer.write(gradeReport.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        driver.close();


    }
}
