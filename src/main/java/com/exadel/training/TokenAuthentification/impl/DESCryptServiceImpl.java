package com.exadel.training.tokenAuthentification.impl;

import com.exadel.training.tokenAuthentification.CryptService;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by HP on 18.07.2015.
 */
public class DESCryptServiceImpl implements CryptService{

   private Cipher ecipher;
   private Cipher dcipher;
   private static SecretKey key;

   static {
       try {
           key = KeyGenerator.getInstance("DES").generateKey();
       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       }
   }

    public DESCryptServiceImpl() throws Exception {
        ecipher = Cipher.getInstance("DES");
        dcipher = Cipher.getInstance("DES");
        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);
    }

    public String encrypt(String str) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        byte[] utf8 = str.getBytes("UTF8");
        byte[] enc = ecipher.doFinal(utf8);

        return new sun.misc.BASE64Encoder().encode(enc);
    }

    public String decrypt(String str) throws IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        byte[] utf8 = dcipher.doFinal(dec);

        return new String(utf8, "UTF8");
    }
}
