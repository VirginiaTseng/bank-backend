package com.virginiabank.bankdemo.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncryptionUtils  
 * Encrypting password in order to be saved into Database
 */
public class EncryptionUtils {
	
	
	/**
	 * MD5 FOR PASSWORD
	 * @param password  original password string 
	 * @return String that encryption by MD5
	 */
	public static String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5"); // GET MD5 ENCRYPTION
	        byte[] hashBytes = md.digest(password.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashBytes) {
	            sb.append(String.format("%02x", b)); // CONVERT INTO LEX 16 STRING 
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Error hashing password", e);
	    }
	}
}
