package com.design.pattern.tetrastar.encryption;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 */

public class SimpleEncryptionAlgorithm implements EncryptionAlgorithm {

    /**
     *  Simple Encryption is just reversing passed string
     * @param dataToEncrypt
     * @return
     */
    @Override
    public String encrypt(String dataToEncrypt) {
        System.out.println("Encrypting data");
        if(dataToEncrypt == null || dataToEncrypt.length() <= 1) {
            return dataToEncrypt;
        }
        StringBuffer sb = new StringBuffer(dataToEncrypt);
        return sb.reverse().toString();
    }

    /**
     *  Decryption is same as encrypt because it will just reverse already reversed string to return original one
     * @param dataToDecrypt
     * @return
     */
    @Override
    public String decrypt(String dataToDecrypt) {
        System.out.println("Decrypting data");
        return encrypt(dataToDecrypt);
    }
}
