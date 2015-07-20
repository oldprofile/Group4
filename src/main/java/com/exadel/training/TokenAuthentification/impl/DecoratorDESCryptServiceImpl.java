package com.exadel.training.tokenAuthentification.impl;

import com.exadel.training.tokenAuthentification.CryptService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by HP on 18.07.2015.
 */
public class DecoratorDESCryptServiceImpl implements CryptService {

    private CryptService cryptService;

    public DecoratorDESCryptServiceImpl(CryptService cryptService) {
        this.cryptService = cryptService;
    }

    @Override
    public String decrypt(String str) throws IOException, BadPaddingException, IllegalBlockSizeException {
      return cryptService.decrypt(str);
    }

    @Override
    public String encrypt(String plainText) throws UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        return cryptService.encrypt(plainText);
    }
}
