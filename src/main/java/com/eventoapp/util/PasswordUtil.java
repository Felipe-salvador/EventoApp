package com.eventoapp.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
	
	public static String md5(String senha) throws NoSuchAlgorithmException{
		MessageDigest messagedig = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1, messagedig.digest(senha.getBytes()));
		return hash.toString(16);
	}

	public static String encoder(String senha) {
		BCryptPasswordEncoder encoderSenha = new BCryptPasswordEncoder();
		return encoderSenha.encode(senha);
	}

}
