package edu.grinnell.csc207.blockchain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    @DisplayName("TestBlockChain")
    public void testBlockChain() throws NoSuchAlgorithmException {
        BlockChain bc = new BlockChain(300);
        Block b = new Block(1, -150, null);
        assertEquals(0, bc.getSize());
        bc.append(b);
        assertEquals(true, bc.isValidBlockChain());
        assertEquals(1, bc.getSize());
        bc.removeLast();
        assertEquals(0, bc.getSize());
    }

    @Test
    @DisplayName("TestHash")
    public void testHash() {
         byte[] b = {0, 0, 0, 1, 2, 3};
         Hash h = new Hash(b);
         assertEquals(true, h.isValid());
         assertEquals("000123", h.toString());
         assertEquals(b, h.getData());
    }

    @Test
    @DisplayName("TestBlock") 
    public void testBlock() throws NoSuchAlgorithmException {
        Block b = new Block(0, 300, null, 10); 
        assertEquals(300, b.getAmount());
        assertEquals(0, b.getNum());
        assertEquals(10, b.getNonce());
        assertEquals(null, b.getPrevHash());
    }

}
