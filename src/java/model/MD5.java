package model;

import java.security.*;
import java.math.*;

// classe de encriptação da senha
public class MD5 {
    public String getMD5(String senha) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }    
}