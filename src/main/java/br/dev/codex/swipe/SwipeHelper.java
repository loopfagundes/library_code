package br.dev.codex.swipe;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class SwipeHelper {

    // acao touch
    // scrolldown
    private final AppiumDriver driver;

    public SwipeHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    public void swipeUp() {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void swipeToElement(WebElement element) {
        int tries = 0;
        while (tries < 5) {
            try {
                if (element.isDisplayed()) {
                    break;
                }
            } catch (Exception ignored) {
            }
            swipeUp();
            tries++;
        }
    }
}