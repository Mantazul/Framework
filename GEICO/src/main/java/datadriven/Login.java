package datadriven;

import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends CommonAPI {
    @FindBy(id = "userName")
    public static WebElement loginTextBox;
    @FindBy(id = "userPassword")
    public static WebElement passwordTextBox;
    @FindBy(id = "loginButton")
    public static WebElement loginButton;
    @FindBy(id="userPassword-msgs")
    public static WebElement errorMessage;

    public static void navigateToLoginPage(){
        driver.navigate().to("https://ecams.geico.com/ecams/login.xhtml?r=true");
    }
    public void clickSubmit(){

        loginButton.click();
    }
    public void clearInputBox(WebElement webElement){

        webElement.clear();
    }

    public String SendLoginInfo(String email, String password) throws InterruptedException {
        loginTextBox.sendKeys(email);
        passwordTextBox.sendKeys(password);
        Thread.sleep(2000);
        clickSubmit();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userPassword-msgs")));
        String errMessage = errorMessage.getText();
        clearInputBox(loginTextBox);
        clearInputBox(passwordTextBox);
        return errMessage;
    }
}