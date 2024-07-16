package br.dev.codex.Directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryFolder {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir")
            + File.separator
            + "src"
            + File.separator
            + "test"
            + File.separator
            + "resources"
            + File.separator
            + "dataUSer"
            + File.separator;

    public static void main(String[] args) throws IOException {

        deleteDirectoryRecursively(new File(DIR_PATH_PROPERTIES));
        createFolder("dataUser");
        FilesOperation.createProperties("dataUser", "1_user");
        FilesOperation.createProperties("dataUser", "2_user");
        FilesOperation.createProperties("dataUser", "1_user_crypto");
        FilesOperation.createProperties("dataUser", "2_user_crypto");
    }

    public static void createFolder(String path){
        File directory = new File("src/test/resources/" + path);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("A pasta foi criada!");
        } else {
            System.out.println("A pasta ja existe!");
        }
    }

    public static void deleteDirectoryRecursively(File file) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursively(entry);
                }
            }
        }
        Files.delete(file.toPath());
    }
}