package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A single block of a blockchain.
 */
public class Block {
    int num;
    int amount;
    Hash previous;
    long nonce;
    Hash current;

    /**
     * Creates a new block from the parameters and will perform the
     * mining operation to discover the nonce and hash for this block
     * given these parameters
     * 
     * @param num      int: The number of the block in the blockchain
     * @param amount   int: The amount transferred between the two parties
     * @param prevHash Hash: The hash from the previous block
     * @throws NoSuchAlgorithmException
     */
    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.previous = prevHash;
        long currentNonce = helperNonce(amount);
        this.nonce = currentNonce;
        Hash currentHash = new Hash(Hash.calculateHash(num, amount, prevHash, currentNonce));
        this.current = currentHash;
    }

    /**
     * A helper function that finds the nonce for my first block.
     * 
     * @param amount int: The amount in the first block.
     * @return long: The noce of the first block
     * @throws NoSuchAlgorithmException
     */
    public long helperNonce(int amount) throws NoSuchAlgorithmException {
        long newNonce = 0;
        Hash h = new Hash(Hash.calculateHash(num, amount, previous, newNonce));

        while (!h.isValid()) {
            newNonce++;
            h = new Hash(Hash.calculateHash(num, amount, previous, newNonce));
        }

        return newNonce;
    }

    /**
     * Creates a new block from the parameters and using the provided nonce
     * and additional parameters generates the hash for the block.
     * 
     * @param num      int: The number of the block in the blockchain
     * @param amount   int: The amount transferred between the two parties
     * @param prevHash Hash: The hash of the previous block
     * @param nonce    lng: The nonce
     */
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.previous = prevHash;
        this.nonce = nonce;
        Hash currentHash = new Hash(Hash.calculateHash(num, amount, prevHash, nonce));
        this.current = currentHash;

    }

    /**
     * Returns the number of the block in the blockchain
     * 
     * @return int: The number of the block in the blockchain
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Returns the amount of the block
     * 
     * @return int: The amount of the block
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Returns the nonce of the block
     * 
     * @return long: nonce
     */
    public long getNonce() {
        return this.nonce;
    }

    /**
     * Returns the hash of the previous block
     * 
     * @return Hash: The hash of the previous block
     */
    public Hash getPrevHash() {
        return this.previous;
    }

    /**
     * Returns the hash of the block
     * 
     * @return Hash: The hash of the block
     */
    public Hash getHash() {
        return this.current;
    }

    /**
     * Returns the to string of a given block
     * 
     * @return String: The toString of the given block.
     */
    public String toString() {
        String prev;
        if (this.previous == null) {
            prev = "null";
        } else {
            prev = this.previous.toString();
        }
        String s = "Block " + this.num + "(Amount: " + this.amount + ", "
                + "Nonce: " + this.nonce + ", PrevHash:" + prev
                + ", hash: " + this.current.toString() + ")";

        return s;
    }

}
