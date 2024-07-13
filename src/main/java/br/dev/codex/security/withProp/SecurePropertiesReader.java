package br.dev.codex.security.withProp;


import br.dev.codex.files.FilesOperation;

public class SecurePropertiesReader {
    public static void main(String[] args) {
        try {
            // Descriptografar os dados
            String email = Crypto.decrypt(FilesOperation.getProperties("crypto").getProperty("email"));
            String password = Crypto.decrypt(FilesOperation.getProperties("crypto").getProperty("password"));

            System.out.println("E-mail: " + email);
            System.out.println("Senha: " + password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}