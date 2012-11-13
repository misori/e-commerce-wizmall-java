package com.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 암호화/복호화 Util 클래스
 * AES 알고리즘 / CBC 운영모드
 * @author 구본웅
 *
 */
public class CryptoUtil {
 
       private static final String ENC_TYPE = "utf-8";
       private static final String ENC_FORMAT = "AES/CBC/PKCS5Padding";
       private static BASE64Decoder DECORDER = new BASE64Decoder();
       private static BASE64Encoder ENCORDER = new BASE64Encoder();
       private static SecretKeySpec SecureKey = null;
       private static IvParameterSpec InitialVector = null;
       private static Cipher EncryptCipher = null;
       private static Cipher DecryptCipher = null;
      
       /**
        * 키를 생성하는 메서드, 불완전한 싱글턴
        * @return
        * @throws Exception
        */
       private static void init() throws Exception{
            
    	   byte key[] = {
    	            11, 22, 33, 44, 55, 66, 77, 88, 99, 00, 
    	            11, 22, 33, 44, 55, 66
    	        };
    	        byte pbIV[] = {
    	            11, -22, 40, -44, 55, -66, 77, -55, 55, -1, 
    	            -11, -2, 33, 44, 55, 6
    	        };  
    	        
             // Key값 설정
             if(SecureKey == null){
                   // SecureKey = new SecretKeySpec(DECORDER.decodeBuffer("[Key(32byte) BASE64로 인코딩된 값]"), "AES");
            	 SecureKey = new SecretKeySpec(key, "AES");
             }
            
             // IV값 설정
             if(InitialVector == null){
                   // InitialVector = new IvParameterSpec(DECORDER.decodeBuffer("[IV(16byte) BASE64로 인코딩된 값]"));
            	 InitialVector = new IvParameterSpec(pbIV);
             }
       }
      
       /**
        * 암호화 Cipher 초기화
        * @return
        * @throws Exception
        */
       private static Cipher geEncryptCipher() throws Exception{
             init();
            
             // 불완전한 싱글턴
             if(EncryptCipher == null){
                    EncryptCipher = Cipher.getInstance(ENC_FORMAT);
                    EncryptCipher.init(Cipher.ENCRYPT_MODE, SecureKey, InitialVector);
             }
            
             return EncryptCipher;
       }
      
       /**
        * 복호화 Cipher 초기화
        * @return
        * @throws Exception
        */
       private static Cipher getDecryptCipher() throws Exception {
             init();
            
             // 불완전한 싱글턴
             if(DecryptCipher == null){
                    DecryptCipher = Cipher.getInstance(ENC_FORMAT);
                    DecryptCipher.init(Cipher.DECRYPT_MODE, SecureKey, InitialVector);
             }
            
             return DecryptCipher;
       }
      
       /**
        * 암호화
        * @param clearStr
        * @return
        * @throws Exception
        */
       public static synchronized String encrypt(String clearStr) throws Exception {
             return ENCORDER.encode(geEncryptCipher().doFinal(clearStr.getBytes(ENC_TYPE)));
       }
      
       /**
        * 복호화
        * @param encStr
        * @return
        * @throws Exception
        */
       public static synchronized String decrypt(String encStr) throws Exception {
             return new String(getDecryptCipher().doFinal(DECORDER.decodeBuffer(encStr)), ENC_TYPE);
       }
      
       public static void main(String args[]) throws Exception {
    	   
    	   
    	   	String source = "{\"bbb_ver\":\"1.0.0\", \"aaa_type\":\"10\"}";
             
             String encryptedData = encrypt(source);
             System.out.println(encryptedData);
             
             System.out.println(decrypt(encryptedData));
             //String testData = "PejojGjUwoEY/EExU4LVK+3Bdmt4Hs/+gd2/BKKhVj7ixavy4DNsYS488tCc//adpTeABaXmwdd14712xqPItSQlaG59QelbSi8fJRo3iSkic77F7yP6GsMM0WvceKgtwNiTdflI2nUajtuZBjwXZutqXGJoDd4KYexTieyJ2/5uEz2iOUzztFQJBdFxFcFakPYpdanr05H1J493YPhokqWa6ewxS7soYPcUutGucI1p2Uo1n0z2Wf2Vwuu1PkSPevX5vU0ieXQjTSXoJQ9pgU0L+FvQJWeW9lp8shOkcEo=";
             //System.out.println(decrypt(testData));
       }
}