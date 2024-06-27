package br.dev.codex.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Properties;

public class FilesOperation {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir")
            + File.separator
            + "src"
            + File.separator
            + "test"
            + File.separator
            + "resources"
            + File.separator
            + "properties"
            + File.separator;

    public static Properties getProperties(String name) throws IOException {
        InputStream inputStream = null;
        Properties prop = new Properties();
        try {
            File file = new File(DIR_PATH_PROPERTIES + name + ".properties");
            inputStream = Files.newInputStream(file.toPath());
            prop.load(inputStream);
            return prop;
        } catch (Exception e) {
            throw new RuntimeException("Não carregou o arquivo " + e.getMessage());
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
    }

    public static void setProperty(String nameProp, String key, String value) throws IOException {
        Properties properties = getProperties(nameProp);
        properties.setProperty(key, value);
        saveProperties(nameProp, properties);
    }

    private static void saveProperties(String name, Properties properties) throws IOException {
        OutputStream outputStream = null;
        try {
            File file = new File(DIR_PATH_PROPERTIES + name + ".properties");
            outputStream = Files.newOutputStream(file.toPath());
            properties.store(outputStream, null);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar as propriedades: " + e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}