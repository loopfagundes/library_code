package br.dev.codex.cucumber;

import br.dev.codex.files.FilesOperation;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

public class Element {

    /*
     * limpa o campo de texto
     */
    public void clear_and_text(String text) throws Exception {
        try {
            WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(this.by));
            element.clear();
            element.sendKeys(text);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new Exception(e);
        }
    }

    /*
     * Get Text = obter o texto dentro sendKeys
     */
    public void text(String text) throws Exception {
        try {
            WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(this.by));
            element.sendKeys(text);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new Exception(e);
        }
    }

    /*
     * Ver o texto antes de assert ou qualquer o jeito.
     */
    public void viewText() throws Exception {
        try {
            WebElement element = this.wait.until(ExpectedConditions.visibilityOf(locator));
            System.out.println("Message: " + element.getText());
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new Exception(e);
        }
    }

    /*
     * set Dados que Armazenar os dados que tem ASCII e depois com assert.
     * Pode ser exportar outra classe, exemplo CustomSD.
     */
    public void setData(String nameProp, String value) throws IOException {
        WebElement element = this.wait.until(ExpectedConditions.visibilityOf(locator));
        String replacement = element.getText().replace("á", "a");
        FilesOperation.setProperty(nameProp, value, replacement);
        Assert.assertEquals(replacement, FilesOperation.getProperties(nameProp).getProperty(value));
    }

    /*
     * Ulitizar o metodo de AssertEquals com getText
     */

    public void assertEquals(String expected) throws Exception {
        try {
            WebElement element = this.wait.until(ExpectedConditions.elementToBeClickable(this.by));
            Assert.assertEquals(element.getText(), expected);
        } catch (InvalidElementStateException | NoSuchElementException | StaleElementReferenceException |
                 TimeoutException e) {
            throw new Exception(e);
        }
    }
}