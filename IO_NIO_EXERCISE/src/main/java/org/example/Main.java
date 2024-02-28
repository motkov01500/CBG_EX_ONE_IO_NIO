package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Scanner;

import static org.example.ZipOperationsService.decryptZipFile;
import static org.example.ZipOperationsService.encryptFile;

public class Main {
    public static void main(String[] args) throws Exception {
        try(Scanner scanner = new Scanner(System.in)) {
            Path toEncrypt;
            Path newZip;
            Cipher algorithm = Cipher.getInstance("AES");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            System.out.print("Enter the first path:");
            toEncrypt = Paths.get(scanner.next());
            System.out.print("Enter the second path:");
            newZip = Paths.get(scanner.next());
            System.out.println("Enter the operation(encrypt or decrypt):");
            String option = scanner.next();
            switch (option.toLowerCase()) {
                case "encrypt":
                    encryptFile(algorithm, key, toEncrypt.toFile(), newZip.toFile());break;
                case "decrypt":
                    decryptZipFile(algorithm, toEncrypt.toFile(), newZip.toFile());break;
            }
        }
    }
}