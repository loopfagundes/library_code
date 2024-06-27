package br.dev.codex.tablesLists;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableList {

    public static void tableList(WebElement element) {
        WebElement mainTable = element;

        List<WebElement> table = mainTable.findElements(By.tagName("tr"));

        List<WebElement> firstList = null;
        List<WebElement> secondList = null;

        for (WebElement row : table) {
            firstList = row.findElements(By.tagName("th"));
            secondList = row.findElements(By.tagName("td"));

            //É só imprimir depois de execução.
            for (WebElement column : firstList) {
                System.out.println("primeira linha: " + column.getText());
            }

            for (WebElement column : secondList) {
                System.out.println("As linhas: " + column.getText() + ", ");
            }
        }
    }
}