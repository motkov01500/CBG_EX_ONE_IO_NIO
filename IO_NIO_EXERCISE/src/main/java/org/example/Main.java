package org.example;

//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Scanner;
//
//import static org.example.ZipOperationsService.decryptZipFile;
//import static org.example.ZipOperationsService.encryptZipFile;
//
//public class Main {
//
//    public static void main(String[] args) throws Exception {
////        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Lyubo\\Desktop\\AES_key.txt");
////        FileInputStream fileInputStream1 = new FileInputStream("C:\\Users\\Lyubo\\Desktop\\kur.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream("davidimdaliStava.txt");
//        Scanner scanner = new Scanner(System.in);
//        byte[] ivBytes = new byte[16];
//        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
//        Path toEncrypt;
//        Path newZip;
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
//        Cipher algorithm = Cipher.getInstance("AES");
//        Key key = keyGenerator.generateKey();
////        String plainText = Base64.getEncoder().encodeToString(key.getEncoded());
//        algorithm.init(Cipher.ENCRYPT_MODE, key);
//        String a = "kur";
//        byte[] encr = algorithm.doFinal(a.getBytes());
//        SecretKey key1 = new SecretKeySpec(key.getEncoded(),"AES");
//        algorithm.init(Cipher.DECRYPT_MODE, key1);
//        byte[] decrypt = algorithm.doFinal(encr);
//        fileOutputStream.write(decrypt  );
//        fileOutputStream.close();
//    }
//}


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


//        System.out.print("Enter path for file to Encrypt:");
//        toEncrypt = Paths.get(scanner.next());
//        System.out.print("Enter path for file to Decrypt:");
//        newZip = Paths.get(scanner.next());
////        encryptZipFile(algorithm,key,toEncrypt,newZip);
//        decryptZipFile(algorithm,toEncrypt,newZip);