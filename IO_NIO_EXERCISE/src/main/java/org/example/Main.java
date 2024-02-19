package org.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

//public class Main {
//
//    public static void main(String[] args) throws Exception {
//        Path path = Paths.get("C:\\Users\\lyuboslav.motkov\\Desktop\\neshto.zip");
//        Cipher cipher = Cipher.getInstance("AES");
//        KeyGenerator keyGenerator =  KeyGenerator.getInstance("AES");
//        SecretKey key = keyGenerator.generateKey();
//        cipher.init(Cipher.ENCRYPT_MODE,key);
//        try (FileInputStream fi = new FileInputStream(path.toString());
//                ZipInputStream zip = new ZipInputStream(fi);
//             ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("newFile.zip"))) {
//            ZipEntry entry;
//            while ((entry = zip.getNextEntry()) != null) {
//                int bitesRead;
//                if(entry.isDirectory()) {
//                    zipOutputStream.putNextEntry(entry);
//                    continue;
//                }
//                byte buffer[] = new byte[1024];
//                while((bitesRead = zip.read(buffer)) != -1) {
//                    byte[] encryptedText = zip.readAllBytes();
//                    if(encryptedText != null) {
//                        ZipEntry newFile = new ZipEntry(entry.getName());
//                        zipOutputStream.putNextEntry(newFile);
//                        zipOutputStream.write(encryptedText);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

class Test{
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\lyuboslav.motkov\\Desktop\\neshto.zip");
        try (FileInputStream fi = new FileInputStream(path.toString());
             ZipInputStream zip = new ZipInputStream(fi);
             FileOutputStream out = new FileOutputStream("testt.txt")) {
            ZipEntry entry;
            while ((entry =  zip.getNextEntry()) != null) {
                byte[] a = zip.readAllBytes();
                out.write(a);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }
}