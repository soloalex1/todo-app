package model;

import java.io.File;
import java.security.MessageDigest;
import java.math.BigInteger;


public class MD5 {
    
    // utilizando o hash MD5 para encriptação das senhas armazenadas no banco de dados da aplicação
    public String getMD5(String senha) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, m.digest()).toString(16);
    }    
    public static void main(String[] args) {
        System.out.println(File.separator + File.pathSeparator);
    }
}