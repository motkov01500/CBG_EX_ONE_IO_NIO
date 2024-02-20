package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static org.example.ZipOperationsService.decryptZipFile;
import static org.example.ZipOperationsService.encryptZipFile;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Path toEncrypt;
        Path newZip;
        Cipher algorithm = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
        Key key = keyGenerator.generateKey();
        System.out.print("Enter path for file to Encrypt:");
        toEncrypt = Paths.get(scanner.next());
        System.out.print("Enter path for file to Decrypt:");
        newZip = Paths.get(scanner.next());
//        encryptZipFile(algorithm,key,toEncrypt,newZip);
        decryptZipFile(algorithm,toEncrypt,newZip);
    }
}