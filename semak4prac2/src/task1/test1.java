package task1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.file.Files;
import java.nio.file.Paths;

public class test1 {
    private static final int IV_LENGTH = 16;
    private static final int AES_KEY_LENGTH_BYTES = 16;


    public static void main(String[] args) {
        try {
            byte[] keyFileContent = Files.readAllBytes(Paths.get("D:\\semak4prac2\\src\\task1\\aes.key"));
            byte[] encryptedData = Files.readAllBytes(Paths.get("D:\\semak4prac2\\src\\task1\\secret_text.enc"));
            byte[] iv = new byte[IV_LENGTH];
            System.arraycopy(keyFileContent, 0, iv, 0, 16);
            byte[] aesKeyBytes = new byte[AES_KEY_LENGTH_BYTES];
            System.arraycopy(keyFileContent, IV_LENGTH, aesKeyBytes, 0, 16);
            SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey, gcmParameterSpec);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println(new String(decryptedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}