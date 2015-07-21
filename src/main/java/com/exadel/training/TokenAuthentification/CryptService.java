package com.exadel.training.TokenAuthentification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by HP on 18.07.2015.
 */
public interface CryptService {
    String decrypt(String str) throws IOException, BadPaddingException, IllegalBlockSizeException;
    String encrypt(String plainText) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException;
}
