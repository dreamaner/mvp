package com.android.kit.utils.data.cipher;


import com.android.kit.utils.data.safe.Base64;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class Base64Cipher extends com.android.kit.utils.data.cipher.Cipher {
    private Cipher cipher;

    public Base64Cipher() {
    }

    public Base64Cipher(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public byte[] decrypt(byte[] res) {
        if (cipher != null) res = cipher.decrypt(res);
        return Base64.decode(res, Base64.DEFAULT);
    }

    @Override
    public byte[] encrypt(byte[] res) {
        if (cipher != null) res = cipher.encrypt(res);
        return Base64.encode(res, Base64.DEFAULT);
    }
}
