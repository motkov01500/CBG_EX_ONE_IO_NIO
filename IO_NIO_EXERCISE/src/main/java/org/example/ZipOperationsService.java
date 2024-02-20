package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Key;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipOperationsService {

    public static void encryptFile(Cipher algorithm,Key key, File zipToDecrypt, File zipToSave) {
        try (ZipFile zipFile = new ZipFile(zipToDecrypt);
             ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipToSave))) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            zipOutputStream.putNextEntry(new ZipEntry("AES_key.txt"));
            zipOutputStream.write(key.getEncoded());
            ZipEntry keyEntry = null;
            algorithm.init(Cipher.ENCRYPT_MODE, key);
            while (entries.hasMoreElements()) {
                ZipEntry normalEntry = entries.nextElement();
                byte[] beforeEncryption = zipFile.getInputStream(normalEntry).readAllBytes();
                byte[] encrypted = algorithm.doFinal(beforeEncryption);
                zipOutputStream.putNextEntry(normalEntry);
                zipOutputStream.write(encrypted);
                zipOutputStream.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptZipFile(Cipher algorithm, File zipToDecrypt, File zipToSave) {
        try (ZipFile inputZip = new ZipFile(zipToDecrypt);
             ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipToSave))) {
            Enumeration<? extends ZipEntry> entries = inputZip.entries();
            ZipEntry keyEntry = entries.nextElement();
            Key key = new SecretKeySpec(inputZip.getInputStream(keyEntry).readAllBytes(), "AES");
            algorithm.init(Cipher.DECRYPT_MODE, key);
            while (entries.hasMoreElements()) {
                ZipEntry encryptedEntry = entries.nextElement();
                byte[] encrypted = inputZip.getInputStream(encryptedEntry).readAllBytes();
                byte[] decrypted = algorithm.doFinal(encrypted);
                ZipEntry decryptedEntry = new ZipEntry(encryptedEntry.getName());
                zipOutputStream.putNextEntry(decryptedEntry);
                zipOutputStream.write(decrypted);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
