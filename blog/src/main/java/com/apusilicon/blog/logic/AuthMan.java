package com.apusilicon.blog.logic;

import org.springframework.stereotype.Component;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.apusilicon.blog.classes.imaginery.Token;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
     * Check if two cryptographically signed hashes are the same
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
     * Deserializes json into object
     *
     * @param json
     * @return token object
     */
    public Token getTokenObject(String json) {
        Gson gson = new Gson();
        Token token = gson.fromJson(json, Token.class);
        return token;
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
    public Token parseToken(String token, String key) throws IllegalArgumentException,
            InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        //first 16 chars
        key = key.substring(0, 16);
        // setting the initialization vector
        String initVector = "16 chars minimum";
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(token));

        return getTokenObject(new String(original));
    }

    /**
     * Creates Encrypted token
     *
     * @param json serialized token object to be encrypted
     * @param key database stored cryto sign hash of user
     * @return String encrypted password
     */
    public String createToken(String json, String key) {
        //first 16 chars
        key = key.substring(0, 16);
        //Converting the hash map to string repr
        String hashSerial = json;
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
     * @param token
     * @return boolean returns write permissions
     */
    public Boolean canWriteBlog(Token token) {
        System.out.println(token.toString());
        return token.getBlog().contains("w");
    }

    /**
     * Can write to database
     *
     * @param token
     * @return boolean returns write permissions
     */
    public Boolean canCreateDb(Token token) {
        System.out.println(token.toString());
        return token.getDatabase().contains("c");
    }
}
