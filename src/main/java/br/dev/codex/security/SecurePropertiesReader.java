package br.dev.codex.security;

import java.io.FileInputStream;
import java.util.Properties;

public class SecurePropertiesReader {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("config.properties")) {
            properties.load(inputStream);

            // Obter dados criptografados
            String encryptedEmail = properties.getProperty("email");
            String encryptedPassword = properties.getProperty("password");

            // Descriptografar os dados
            String email = CryptoUtils.decrypt(encryptedEmail);
            String password = CryptoUtils.decrypt(encryptedPassword);

            System.out.println("E-mail: " + email);
            System.out.println("Senha: " + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}