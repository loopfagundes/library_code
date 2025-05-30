package dev.codex.helpers;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenshotHelper {

    private final AppiumDriver driver;

    public ScreenshotHelper(AppiumDriver driver) {
        this.driver = driver;
    }

    private void esperarTelaCarregar() {
        String pageSourceAntes;
        String pageSourceDepois = "";
        int tentativas = 0;
        int maxTentativas = 5;

        do {
            pageSourceAntes = pageSourceDepois;
            pageSourceDepois = driver.getPageSource();
            tentativas++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (!pageSourceAntes.equals(pageSourceDepois) && tentativas < maxTentativas);
    }

    public void takeScreenshot(String fileName) {
        esperarTelaCarregar();

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File diretorio = new File("screenshots");
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            FileUtils.copyFile(src, new File(diretorio, fileName + ".png"));
            System.out.println("Screenshot salvo: " + diretorio.getAbsolutePath() + "/" + fileName + ".png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}