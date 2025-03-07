package edu.grinnell.csc207.blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * The main driver for the block chain program.
 */
public class BlockChainDriver {

    public static void printHelper(BlockChain b) {
        // System.out.println(b.toString());
        System.out.println("\n\nWill Print BlockChain");
        System.out.print("Command? ");
    }

    /**
     * The main entry point for the program.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        if (args.length != 1) {
            System.out.println("Must enter a non-negative integer");
            System.exit(0);
        } else {
            Scanner in = new Scanner(System.in);
            BlockChain b = new BlockChain(Integer.parseInt(args[0]));
            int a = 0;
            Block newBlock = null;
            long n = 0;

            // System.out.println(b.toString());
            System.out.println("This will pring ToString\n" +
                    "Command? ");

            while (true) {
                switch (in.next()) {
                    case "mine":
                        System.out.print("Amount transferred? ");
                        a = in.nextInt();
                        newBlock = b.mine(a);
                        System.out.print("amount = " + a + ", nonce = " + newBlock.getNonce() + "\n");
                        printHelper(b);
                        break;
                    case "append":
                        System.out.print("Amount transferred? ");
                        a = in.nextInt();
                        System.out.print("Nonce? ");
                        n = in.nextLong();
                        b.append(newBlock);
                        printHelper(b);
                        break;
                    case "remove":
                        b.removeLast();
                        printHelper(b);
                        break;
                    case "check":
                        if(b.isValidBlockChain()) {
                            System.out.println("Chain is valid!");
                        } else {
                            System.out.println("Chain is invalid!");
                        }
                        printHelper(b);
                        break;
                    case "report":
                        b.printBalances();
                        printHelper(b);
                        break;
                    case "help":
                        System.out.println("Valid commands:\n" +
                                "\tmine: discovers the nonce for a given transaction\n" +
                                "\tappend: appends a new block onto the end of the chain\n" +
                                "\tremove: removes the last block from the end of the chain\n" +
                                "\tcheck: checks that the block chain is valid\n" +
                                "\treport: reports the balances of Alice and Bob\n" +
                                "\thelp: prints this list of commands\n" +
                                "\tquit: quites the program");
                        printHelper(b);
                        break;
                    case "quite":
                        in.close();
                        System.exit(-1);
                        break;
                    default:
                        System.out.println("Invalid Command. Enter command <help> to see a list of commands");
                        break;
                }
            }
        }
    }
}
