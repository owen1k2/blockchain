package edu.grinnell.csc207.blockchain;

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
     * @param num int: The number of the block in the blockchain
     * @param amount int: The amount transferred between the two parties
     * @param prevHash Hash: The hash from the previous block
     */
    public Block(int num, int amount, Hash prevHash) {
        this.num = num;
        this.amount = amount;
        this.previous = prevHash;
    }

    /**
     * Creates a new block from the parameters and using the provided nonce
     * and additional parameters generates the hash for the block.
     * @param num int: The number of the block in the blockchain
     * @param amount int: The amount transferred between the two parties
     * @param prevHash Hash: The hash of the previous block
     * @param nonce lng: The nonce
     */
    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.previous = prevHash;
        this.nonce = nonce;

    }

    /**
     * Returns the number of the block in the blockchain
     * @return int: The number of the block in the blockchain
     */
    public int getNum() {
        return this.num;
    }

    /**
     * Returns the amount of the block
     * @return int: The amount of the block
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Returns the nonce of the block
     * @return long: nonce
     */
    public long getNonce() {
        return this.nonce;
    }

    /**
     * Returns the hash of the previous block
     * @return Hash: The hash of the previous block
     */
    public Hash getPrevHash() {
        return this.previous;
    }

    /**
     * Returns the hash of the block
     * @return Hash: The hash of the block
     */
    public Hash getHash() {
        return this.current;
    }
/* 
    public String toString() {

    }
*/
}
