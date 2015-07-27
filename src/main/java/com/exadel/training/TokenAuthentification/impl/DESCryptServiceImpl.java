package com.exadel.training.tokenAuthentification.impl;

import com.exadel.training.tokenAuthentification.CryptService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.crypto.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by HP on 18.07.2015.
 */
@Scope(value = "prototype")
@Repository
public class DESCryptServiceImpl implements CryptService{

   private Cipher ecipher;
   private Cipher dcipher;
   private  SecretKey key;


    public DESCryptServiceImpl() throws Exception {
        key = KeyGenerator.getInstance("DES").generateKey();
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
