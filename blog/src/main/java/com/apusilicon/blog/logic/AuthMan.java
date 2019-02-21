package com.apusilicon.blog.logic;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Component
public class AuthMan {

    /**
     * Encodes password using BCrypt Algorithm
     *
     * @param password user password to be hashed
     * @return String of crytographically hashed password
     */
    public String encode(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    /**
     * Check is two cryptographically signed hashes are the same
     *
     * @param input users email and password raw text
     * @param storage database stored cryto sign hash of user
     * @return Boolean true if match else false
     */
    public Boolean match(String input, String storage) {
        BCrypt.Result result = BCrypt.verifyer().verify(input.toCharArray(), storage.toCharArray());
        return result.verified;
    }

    /**
     * Deserializes Map from String
     *
     * @param kvPairsList key value pair map representation
     * @return Map<String, String> built from serialized map string
     */
    public Map<String, String> stringToMap(String kvPairsList) {
        // Init Return Map
        Map<String, String> map = new HashMap<>();
        // Removes the outer braces
        kvPairsList = kvPairsList.substring(1, kvPairsList.length() - 1);
        // Splits the kvPairsList based on the comma and iterates over single pairs
        for (String kvPairs : kvPairsList.split(",")) {
            // Splits the kvPairsList based on the comma and iterates over single pairs
            String[] kv = kvPairs.split("=");
            map.put(kv[0], kv[1]);
        }
        return map;
    }

    /**
     * Decrypt token
     *
     * @param token encrypted string that contains user info
     * @param key database stored cryto signed hash of user
     * @return Map<String,String> deserialized from decrypted token
     * @throws java.security.InvalidKeyException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidAlgorithmParameterException
     * @throws javax.crypto.BadPaddingException
     * @throws javax.crypto.IllegalBlockSizeException
     */
    public Map<String, String> parseToken(String token, String key) throws IllegalArgumentException,
            InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        //first 16 chars
        key = key.substring(0, 16);
        // Init hashmap
        HashMap<String, String> map = new HashMap<>();
        // setting the initialization vector
        String initVector = "16 chars minimum";
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(token));

        return stringToMap(new String(original));
    }

    /**
     * Creates Encrypted token
     *
     * @param map stores user directives
     * @param key database stored cryto sign hash of user
     * @return String encrypted password
     */
    public String createToken(HashMap<String, String> map, String key) {
        //first 16 chars
        key = key.substring(0, 16);
        //Converting the hash map to string repr
        String hashSerial = map.toString();
        // setting the initialization vector
        String initVector = "16 chars minimum";
        // Try to encrypt the data
        // Catch exceptions thrown
        try {

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(hashSerial.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Can write
     *
     * @param map stores the current user permissions
     * @return boolean returns write permissions
     */
    public Boolean canWrite(Map<String, String> map) {
        System.out.println(map.toString());
        return map.get("blog").contains("w");
    }
}
