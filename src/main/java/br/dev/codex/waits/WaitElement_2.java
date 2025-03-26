package br.dev.codex.waits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitElement_2 {
    private final WebDriverWait wait;

    // Construtor permitindo tempo de espera configurável
    public WaitElement_2(WebDriver driver, long timeoutInSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Construtor padrão com tempo fixo de 15 segundos
    public WaitElement_2(WebDriver driver) {
        this(driver, 15);
    }

    // Aguarda até que o elemento esteja visível na tela
    public WebElement visibilityOf(By by) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Elemento não encontrado ou não visível: " + by, e);
        }
    }

    // Aguarda até que o elemento esteja presente no DOM (independente de estar visível ou não)
    public WebElement presenceOfElement(By by) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Elemento não encontrado no DOM: " + by, e);
        }
    }

    // Aguarda até que o elemento esteja clicável
    public WebElement toBeClickable(By by) {
        WebElement element = visibilityOf(by);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            throw new NoSuchElementException("Elemento não está clicável: " + by, e);
        }
    }

    // Aguarda até que um elemento desapareça da tela
    public boolean invisibilityOf(By by) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Aguarda até que um elemento contenha um determinado texto
    public boolean textToBePresentInElement(By by, String text) {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
        } catch (TimeoutException e) {
            return false;
        }
    }
}