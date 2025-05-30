package br.dev.codex.waits;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement_3 {

    //APPIUM
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public WaitHelper(AppiumDriver driver, long timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    // Esperar até o elemento ficar visível
    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Esperar até o elemento ser clicável
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Esperar até um texto estar presente no elemento
    public boolean waitForText(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    // Esperar até um elemento estar presente (localizado pelo By)
    public WebElement waitForPresence(org.openqa.selenium.By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Esperar até um elemento desaparecer (ficar invisível)
    public boolean waitForInvisibility(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}