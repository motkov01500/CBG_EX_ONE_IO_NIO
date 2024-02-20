package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.security.Key;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipOperationsService {

    public static void encryptZipFile(Cipher algorithm, Key key, Path zipToEncrypt, Path zipToSafe) throws Exception {
        Key key1 = new SecretKeySpec(key.getEncoded(),"AES");
        algorithm.init(Cipher.ENCRYPT_MODE, key1);
        try (FileInputStream fileInputStream = new FileInputStream(zipToEncrypt.toString());
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(zipToSafe.toString());
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)){
            ZipEntry entry;
            zipOutputStream.putNextEntry(new ZipEntry("AES_key.txt"));
            zipOutputStream.write(key.getEncoded());
            byte[] buffer = new byte[1024];
            while ((entry =  zipInputStream.getNextEntry()) != null) {
                zipOutputStream.putNextEntry(entry);
                int readBytes;
                while((readBytes = zipInputStream.read(buffer)) != -1) {
                    byte[] a = zipInputStream.readNBytes(readBytes);
                    byte[] encryptedData = algorithm.doFinal(a);
                    zipOutputStream.write(encryptedData);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptZipFile(Cipher algorithm, Path zipToDecrypt, Path zipToSafe) throws Exception {
        try (FileInputStream fileInputStream = new FileInputStream(zipToDecrypt.toString());
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(zipToSafe.toString());
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)){
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            Key key = new SecretKeySpec(zipInputStream.readAllBytes(),"AES");
            algorithm.init(Cipher.DECRYPT_MODE,key);
            while ((zipEntry =  zipInputStream.getNextEntry()) != null) {
                zipOutputStream.putNextEntry(zipEntry);
                byte[] a = zipInputStream.readAllBytes();
                byte[] decryptedData = algorithm.doFinal(a);
                System.out.println(decryptedData.length);
                zipOutputStream.write(decryptedData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
