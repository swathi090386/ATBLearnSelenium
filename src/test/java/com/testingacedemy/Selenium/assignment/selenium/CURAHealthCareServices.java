package com.testingacedemy.Selenium.assignment.selenium;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class CURAHealthCareServices {

    WebDriver driver;

    @BeforeTest
    public void openBrowser(){

        driver=new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test(priority = 0)
    public void openAppAndVerifyTitle(){
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        String title=driver.getTitle();
        Assert.assertEquals(title,"CURA Healthcare Service");
    }

    @Test(priority = 1)
    public void verifyMakeAppointmentButtonAvailable(){

        WebElement makeApp_Button=driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
        if (makeApp_Button.isDisplayed()){
            makeApp_Button.click();
            System.out.println("MakeAppointment is displayed");
        }
        else {
            System.out.println("MakeAppointment button not displayed");
        }

    }
    @Test(priority = 2)
    public void enterLoginDetails(){
        WebElement login_TextBox=driver.findElement(By.cssSelector("input#txt-username"));
        login_TextBox.sendKeys("John Doe");

        WebElement password_TextBox=driver.findElement(By.cssSelector("input#txt-password"));
        password_TextBox.sendKeys("ThisIsNotAPassword");

        WebElement login_button=driver.findElement(By.cssSelector("button#btn-login"));
        login_button.click();

        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"https://katalon-demo-cura.herokuapp.com/#appointment");
    }

    @Test(priority = 3)
    public void make_Appointment(){
        WebElement facility_dropdown=driver.findElement(By.cssSelector("select#combo_facility"));
        Select select=new Select(facility_dropdown);

        select.selectByIndex(2);

        WebElement none_Options=driver.findElement(By.cssSelector("input#radio_program_none"));
        none_Options.click();

        WebElement vist_DateElement=driver.findElement(By.cssSelector("input#txt_visit_date"));
        vist_DateElement.sendKeys("29/03/2024");

        WebElement comment_Element=driver.findElement(By.cssSelector("textarea#txt_comment"));
        comment_Element.sendKeys("Make new appointment with Dr");

        WebElement book_ButtonElement=driver.findElement(By.cssSelector("button#btn-book-appointment"));
        book_ButtonElement.click();

        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        System.out.println(driver.findElement(By.cssSelector("div.col-xs-12>h2")).getText());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.col-xs-12>h2")).getText(),"Appointment Confirmation");

    }

    @AfterTest
    public void close_Browser(){
        driver.quit();
    }
}