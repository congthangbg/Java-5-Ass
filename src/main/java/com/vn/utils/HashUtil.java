package com.vn.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {
	public static String hash(String plain) {
		String salt=BCrypt.gensalt();
		return BCrypt.hashpw(plain, salt);
	}
	public static boolean verify(String plain,String hash) {
		return BCrypt.checkpw(plain, hash);
	}
}
