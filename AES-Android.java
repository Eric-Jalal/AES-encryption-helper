
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * this is an AES encryptor/decryptor
 * This class is running base64 with android.util.Base64
 */
public class AES {

    private static final String TAG = "AES Encryptor/Decryptor";

    public static final String INIT_VECTOR = "YOUR_KEY";

    /**
     * encrypt method of AES by taking single string value
     *
     * @param key
     * @param initVector
     * @param value
     * @return
     */
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeToString(encrypted, Base64.DEFAULT);

        } catch (Exception ex) {
            Log.e(TAG, "encryption encountered a problem" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * encrypt method of AES by taking byte array value
     * @param key
     * @param initVector
     * @param value
     * @return
     */
    public static byte[] encrypt(String key, String initVector, byte[] value) {
        Log.i(TAG, "encrypt byte array value ran");
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            return cipher.doFinal(value);
        } catch (Exception ex) {
            Log.e(TAG, "encrypt sing string value ran");
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * decrypt method of AES by taking encrypted String
     * @param key
     * @param initVector
     * @param encrypted
     * @return
     */
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted,16));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * decrypt method of AES by taking decrypted byte array
     * @param key
     * @param initVector
     * @param encrypted
     * @return
     */
    public static byte[] decrypt(String key, String initVector, byte[] encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            return cipher.doFinal(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String realContent(String content){
        return content.substring(content.indexOf(" ") + 1, content.length());
    }

    public static String getRandomKey() {
        return getRandomStr(16);
    }

    private static String getRandomStr(int i) {

        return null;
    }
}

