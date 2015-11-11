package com.paul.archetype.utils;

import java.security.MessageDigest;

public class MD5Utils {
	/**
	 * MD5加码。32位小写
	 * */ 
	public static String MD5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("utf-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return  buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	
	/**
	 * 
	 * 可逆的加密算法
	 * */ 
	public static String KL(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	
	/**
	 * 
	 * 加密后解密
	 * */ 
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}

	
	
	
	
	
	
	
	
	
	
	
}
