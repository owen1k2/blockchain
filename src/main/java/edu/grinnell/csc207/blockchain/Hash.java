package edu.grinnell.csc207.blockchain;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    byte[] arr;

    /**
     * Constructs a new Hash object that contains the given hash
     * as an array of bites
     * 
     * @param data The hash that is being turned into an array of bites
     */
    public Hash(byte[] data) {
        arr = data;
    }

    /**
     * Returns the hash contained in this object
     * 
     * @return byte[]: The hash contained in this object
     */
    public byte[] getData() {
        return arr;
    }

    /**
     * Returns true if this hash meets the criteria for validity
     * 
     * @return Boolean: True if the hash is valid, false otherwiseS
     */
    public boolean isValid() {
        int count = 0;
        for (int x = 0; x < 3; x++) {
            if (Byte.toUnsignedInt(arr[x]) == 0) {
                count++;
            }
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    /**
     * Returns the string representation of a hash as a string
     * 
     * @return String: String representation of a hash
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s = s + Byte.toUnsignedInt(arr[i]);
        }
        return String.format(s, s.hashCode());
    }

    /**
     * Returns true if this hash is structurally equal to the argument
     * 
     * @param other Object: the object we are comparing
     * @return Boolean: True if the hash is structurally equal,
     *         false otherwise
     */
    public boolean equals(Object other) {
        Hash o = (Hash) other;
        return Arrays.equals(o.getData(), arr);
    }

    /**
     * Calculates the hash of a given all necessary inputs.
     * 
     * @param blockNum int: The number of the block in the chain
     * @param amount   int: The amount given to the block
     * @param prevHash Hash: The hash of the previous block
     * @param nonce    long: The nonce discovered for this block
     * @return Returns the byte[] of the hash constructed for given inputs
     * @throws NoSuchAlgorithmException
     */
    public static byte[] calculateHash(int blockNum, int amount, Hash prevHash, long nonce)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(ByteBuffer.allocate(4).putInt(blockNum).array());
        md.update(ByteBuffer.allocate(4).putInt(amount).array());
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        md.update(ByteBuffer.allocate(8).putLong(nonce).array());
        byte[] hash = md.digest();
        return hash;
    }
}
