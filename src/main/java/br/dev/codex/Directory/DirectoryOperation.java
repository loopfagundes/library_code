package br.dev.codex.Directory;

import br.dev.codex.logger.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryOperation {

    public static void createFolder(String nameFolder) {
        Path directoryPath = Paths.get("src/test/resources/" + nameFolder);
        try {
            Files.createDirectory(directoryPath);
            System.out.println("Pasta '" + directoryPath + "' criada com sucesso!");
        } catch (IOException e) {
            System.out.println("Falha ao criar a pasta '" + directoryPath + "' ou a pasta já existe.");
            throw new RuntimeException(e);
        }
    }

    public static void deleteDirectoryPath(String nameFolder) {
        String directoryPath = "src/test/resources/" + nameFolder;
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            deleteDirectory(directory);
        } else {
            LoggerFactory.log_INFO("A pasta '" + directoryPath + "' não existe ou não é um diretório.");
        }
    }

    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    public static void OperationFolder() {
        deleteDirectoryPath("dataUser");
        createFolder("dataUser");
    }
}