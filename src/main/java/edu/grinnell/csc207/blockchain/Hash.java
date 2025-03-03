package edu.grinnell.csc207.blockchain;

import java.util.Arrays;

/**
 * A wrapper class over a hash value (a byte array).
 */
public class Hash {

    byte[] arr;
    /**
     * Constructs a new Hash object that contains the given hash
     * as an array of bites
     * @param data The hash that is being turned into an array of bites
     */
    public Hash(byte[] data) {
       arr = data;
    }
    /**
     * Returns the hash contained in this object
     * @return byte[]: The hash contained in this object
     */
    public byte[] getData() {
        return arr;
    }

    /**
     * Returns true if this hash meets the criteria for validity
     * @return Boolean: True if the hash is valid, false otherwiseS
     */
    public boolean isValid() {
        int count = 0;
        for(int x = 0; x < 3; x++) {
            if(arr[x] == 0) {
                count++;
            }
        }
        if(count == 3) {
            return true;
        }
        return false;
    }

    /**
     * Returns the string representation of a hash as a string of
     * hexadecimal digits
     * @return String: String representation of a hash
     */
    public String toString() {
        String s = "";
        for(int i = 0; i < arr.length; i++) {
            s = s + Byte.toUnsignedInt(arr[i]);
        }
        return String.format(s, s.hashCode());
    }

    /**
     * Returns true if this hash is structurally equal to the argument
     * @return Boolean: True if the hash is structurally equal, 
     * false otherwise
     */
    public boolean equals(Object other) {
        Hash o = (Hash) other;
       // if(Arrays.equals(o, arr){
        //    return true;
       // }
        return false;
    }
}
