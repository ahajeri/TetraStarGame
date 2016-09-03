package com.design.pattern.tetrastar.encryption;
/**
 * 
 * @author Akshata, Rachna and Shweta. 
 */
public interface EncryptionAlgorithm {

    String encrypt(String dataToEncrypt);

    String decrypt(String dataToDecrypt);

}
