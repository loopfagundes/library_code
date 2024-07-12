package br.dev.codex.security;

import java.io.FileOutputStream;
import java.util.Properties;

public class SecurePropertiesWriter {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            String email = "user@example.com";
            String password = "securePassword";

            // Criptografar o e-mail e a senha
            String encryptedEmail = CryptoUtils.encrypt(email);
            String encryptedPassword = CryptoUtils.encrypt(password);

            // Armazenar dados criptografados
            properties.setProperty("email", encryptedEmail);
            properties.setProperty("password", encryptedPassword);

            // Salvar no arquivo de propriedades
            try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/config.properties")) {
                properties.store(outputStream, null);
            }

            System.out.println("Dados criptografados e armazenados com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}