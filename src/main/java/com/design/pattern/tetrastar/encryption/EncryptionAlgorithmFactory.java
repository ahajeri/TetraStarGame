/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.encryption;

import com.design.pattern.tetrastar.enums.EncryptionStrategy;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public class EncryptionAlgorithmFactory {
    
    public static EncryptionAlgorithm getEncryptionAlgorithmForStrategy(EncryptionStrategy encryptionStrategy) {
        if(encryptionStrategy != null) {
            switch(encryptionStrategy) {
                case SIMPLE:
                    return new SimpleEncryptionAlgorithm();
                case NULL:
                    return new NullEncryptionAlgorithm();
                default:
                    throw new AssertionError("Please pass the correct encryption strategy.");                
            }
        }
        return new NullEncryptionAlgorithm();
    }
    
}
