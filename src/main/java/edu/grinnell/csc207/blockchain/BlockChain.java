package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;

/**
 * A linked list of hash-consistent blocks representing a ledger of
 * monetary transactions.
 */
public class BlockChain {

    /**
     * The class Node which makes a node for our BlockChain
     */
    public class Node {
        public Block value;
        public Node next;

        Node(Block value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public Node first;
    public Node last;

    /**
     * The creation of block chain
     * 
     * @param initial int: The initial amount in Block Chain
     * @throws NoSuchAlgorithmException
     */
    public BlockChain(int initial) throws NoSuchAlgorithmException {
        Block b = new Block(0, initial, null);
        first = new Node(b, null);
        last = first;
    }

    /**
     * Finds the nonce for the first block of our BlockChain
     * 
     * @param amount int: The amount for the first nonce
     * @return long: The none for the first block of our BlockChain
     * @throws NoSuchAlgorithmException
     */
    public long helperNonce(int amount) throws NoSuchAlgorithmException {
        long newNonce = 0;
        Hash h = new Hash(Hash.calculateHash(last.value.getNum() + 1,
                    amount, last.value.getHash(), newNonce));

        while (!(h.isValid())) {
            newNonce++;
            // System.out.println("help");
            h = new Hash(Hash.calculateHash(last.value.getNum() + 1,
                    amount, last.value.getHash(), newNonce));
        }
        return newNonce;
    }

    /**
     * Mines a new candidate block to be added to the list and returns
     * a block that is valid to the list.
     * 
     * @param amount int: the amount in the block
     * @return block: The valid block
     */
    public Block mine(int amount) throws NoSuchAlgorithmException {
        long newNonce = 0;
        Hash h = new Hash(Hash.calculateHash(last.value.getNum() + 1,
                    amount, last.value.getHash(), newNonce));
        while (!h.isValid()) {
            newNonce++;
            h = new Hash(Hash.calculateHash(last.value.getNum() + 1,
                    amount, last.value.getHash(), newNonce));
        }
        Block b = new Block(last.value.getNum() + 1, amount, last.value.getHash(), newNonce);
        return b;
    }

    /**
     * Returns the size of the blockchain.
     * 
     * @return int: The size of the blockchain
     */
    public int getSize() {
        return last.value.getNum();
    }

    /**
     * Adds the block to the list, unless the block is invalid
     * 
     * @param blk block: The block being added to the list
     */
    public void append(Block blk) {
        if (blk.getHash().isValid()) {
            Node temp = new Node(blk, null);
            last.next = temp;
            last = last.next;

        }
    }

    /**
     * Removes the last block from the blockchain unless there
     * is only one block in the blockchain
     * 
     * @return boolean: True if a block is removed, false otherwise
     */
    public boolean removeLast() {
        if (last.value.getNum() == 0) {
            return false;
        } else {
            Node temp = first;
            while (temp.next != last) {
                temp = temp.next;
            }
            temp.next = null;
            last = temp;
            return true;
        }
    }

    /**
     * Returns the hash of the last block of the blockchain
     * 
     * @return Hash: The hash of the last block in the blockchain.
     */
    public Hash getHash() {
        return last.value.getHash();
    }

    /**
     * Makes sure that each block in the blockchain is consistant and valid
     * 
     * @return boolean: True if the blockchain is valid, false otherwise
     */
    public boolean isValidBlockChain() {
        int anna = first.value.getAmount();
        int bob = 0;
        Node temp = first;
        while (temp.next != null) {
            anna = anna + temp.next.value.getAmount();
            bob = bob - temp.next.value.getAmount();
            temp = temp.next;

            if (bob < 0 || anna < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the balances of Anna and Bob
     */
    public void printBalances() {
        int anna = first.value.getAmount();
        int bob = 0;
        String s = "";

        Node temp = first;
        while (temp.next != null) {
            anna = anna + temp.next.value.getAmount();
            bob = bob - temp.next.value.getAmount();
            temp = temp.next;
        }
        s = "Alice: " + anna + ", Bob: " + bob;
        System.out.println(s);
    }

    /**
     * Returns the string representation of the blockchain
     * 
     * @return String: The string representation of the blockchain
     */
    public String toString() {
        Node temp = first;
        String s = "";
        while (temp != null) {
            s = s + temp.value.toString() + "\n";
            temp = temp.next;
        }
        return s;
    }
}
