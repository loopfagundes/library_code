package br.dev.codex.security;

import java.io.FileInputStream;
import java.util.Properties;

public class SecurePropertiesReader {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(inputStream);

            // Obter dados criptografados
            String encryptedEmail = properties.getProperty("email");
            String encryptedPassword = properties.getProperty("password");

            // Descriptografar os dados
            String email = Crypto.decrypt(encryptedEmail);
            String password = Crypto.decrypt(encryptedPassword);

            System.out.println("E-mail: " + email);
            System.out.println("Senha: " + password);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}