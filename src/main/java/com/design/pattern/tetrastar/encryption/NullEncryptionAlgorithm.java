package com.design.pattern.tetrastar.encryption;
/**
 * 
 * @author Akshata, Rachna and Shweta.
 *  Null design pattern
 */

public class NullEncryptionAlgorithm implements EncryptionAlgorithm {

    /**
     *   Null encryption does nothing and returns same string that is passed
     * @param dataToEncrypt
     * @return
     */
    @Override
    public String encrypt(String dataToEncrypt) {
        System.out.println("Encrypting data");
        return dataToEncrypt;
    }

    @Override
    public String decrypt(String dataToDecrypt) {
        System.out.println("Decrypting data");
        return dataToDecrypt;
    }

}
