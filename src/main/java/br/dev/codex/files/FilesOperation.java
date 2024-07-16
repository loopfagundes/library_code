package app.netlify.bugbank.utils;

import br.dev.codex.logger.LoggerFactory;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FilesOperation {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir")
            + File.separator
            + "src"
            + File.separator
            + "test"
            + File.separator
            + "resources"
            + File.separator;

    //criar um prop
    //é opcional
    public static void createProperties(String folderName, String createName) {
        try (OutputStream output = Files.newOutputStream(Path.of(DIR_PATH_PROPERTIES + folderName + "/" + createName + ".properties"))) {
            Properties properties = new Properties();
            properties.store(output, "");
            System.out.println("Propriedade salva com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties(String folderName, String name) throws IOException {
        InputStream inputStream = null;
        Properties properties = new Properties();
        try {
            File file = new File(DIR_PATH_PROPERTIES + folderName + "/" + name + ".properties");
            inputStream = Files.newInputStream(file.toPath());
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            LoggerFactory.log_INFO("Não carregou o arquivo" + e.getMessage());
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return properties;
    }

    public static void setProperties(String folderName, String nameProp, String key, String value) throws IOException {
        Properties properties = getProperties(folderName, nameProp);
        properties.setProperty(key, value);
        saveProperties(folderName, nameProp, properties);
    }

    private static void saveProperties(String folderName, String name, Properties properties) throws IOException {
        OutputStream outputStream = null;
        try {
            File file = new File(DIR_PATH_PROPERTIES + folderName + "/" + name + ".properties");
            outputStream = Files.newOutputStream(file.toPath());
            properties.store(outputStream, null);
        } catch (Exception e) {
            LoggerFactory.log_INFO("Não foi possível salvar as propriedades: " + e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    //delete uma prop
    //é opcional
    public static void deleteProperties(String folderName, String name) {
        File file = new File(DIR_PATH_PROPERTIES + folderName + "/" + name + ".properties");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Arquivo '" + name + "' apagado com sucesso!");
            } else {
                System.out.println("Falha ao apagar o arquivo '" + name + "'.");
            }
        } else {
            System.out.println("O arquivo '" + name + "' não existe.");
        }
    }

    // Apagar todas properties
    //é opcional
    private static void deleteAllProperties(String nameFolder) {
        // caminho da pasta onde os arquivos .properties estão dentro pasta
        Path directoryPath = Paths.get("src/test/resources/" + nameFolder);
        try {
            // verificar se a pasta existe e dentro pasta
            if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
                // Apagar todos os arquivos que tem dentro .properties
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, "*.properties")) {
                    for (Path entry : stream) {
                        Files.delete(entry);
                        System.out.println("Arquivo '" + entry.getFileName() + "' apagado com sucesso!");
                    }
                }
            } else {
                System.out.println("A pasta '" + directoryPath + "' não existe ou não é um diretório.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}