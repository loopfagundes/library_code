package br.dev.codex.security.createProp;

import java.io.FileOutputStream;
import java.util.Properties;

public class SecurePropertiesWriter {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            String email = "user@example.com";
            String password = "securePassword";

            // Criptografar o e-mail e a senha
            String encryptedEmail = Crypto.encrypt(email);
            String encryptedPassword = Crypto.encrypt(password);

            // Armazenar dados criptografados
            properties.setProperty("email", encryptedEmail);
            properties.setProperty("password", encryptedPassword);

            // Salvar no arquivo de propriedades
            try (FileOutputStream outputStream = new FileOutputStream("src/main/resources/config.properties")) {
                properties.store(outputStream, null);
            }

            System.out.println("Dados criptografados e armazenados com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}