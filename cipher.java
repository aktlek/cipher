/**
 * @author  <Your name here>
 * @version <Date>
 * CS312 Assignment 5.
 *
 * On my honor, <NAME>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *
 * A program to encrypt and decrypt phrases using
 * columnar transposition cipher.
 *
 *  email address:
 *  UTEID:
 *  Section 5 digit ID:
 *  Grader name:
 *  Number of slip days used on this assignment:
 */
import java.util.Scanner;

public class Ciphers {

    // main method to demonstrate various encrytpions and
    // decryptions using a columnar transposition cipher
     public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
         showIntro();
         doEncryptions(keyboard);
         doDecryptions(keyboard);
         showTests();
         keyboard.close();
     }

     // show the introduction to the program
     public static void showIntro() {
        System.out.println("This program demonstrates a transposition cipher.");
         System.out.println("A cipher is an algorithm to encrypt or decrypt a message.");
         System.out.println();
         System.out.println("This program will demonstrate encrypting a message with");
         System.out.println("a columnar transposition cipher both with and without");
         System.out.println("padding characters. The program will then decrypt a message");
         System.out.println("assuming it was encrypted with a columnar transposition cipher");
         System.out.println("with padding.");
         System.out.println("After accepting user input, the program displays some tests.");
         System.out.println();
     }


     // do various encryptions both padded with Xs and
     // not padded
     public static void doEncryptions(Scanner keyboard) {
         // finish this method.
         // Break the problem up into methods
         System.out.print("Enter message to encrypt: ");
         String message = keyboard.nextLine();
         
         encryptMessage(message, 2, false);
         encryptMessage(message, 3, false);
         encryptMessage(message, 4, false);
         encryptMessage(message, 5, false);
         encryptMessage(message, 6, false);
     }


     // do the decryptions with the of messages for
     // various values of the number of rows
     public static void doDecryptions(Scanner keyboard) {
         // finish this method.
         // Break the problem up into methods
         System.out.print("Enter message to decrypt: ");
         String message = keyboard.nextLine();
         
         decryptMessage(message, 2, true);
         decryptMessage(message, 3, true);
         decryptMessage(message, 4, true);
         decryptMessage(message, 5, true);
         decryptMessage(message, 6, true);
     }


     // show tests of the methods
     public static void showTests() {
         System.out.println();
         System.out.println("This displays automatic tests of the program: ");
         String encryptedMessage = "AEIBFJCGXDHX";
         String expected = "ABCDEFGHIJXX";
         // last 2 int parameters are rows, then test number
         decryptTest(encryptedMessage, expected, 4, 1);

         encryptedMessage = "CPEOURMT!";
         expected = "COMPUTER!";
         decryptTest(encryptedMessage, expected, 3, 2);

         // *** CS312 students add 2 tests for decrypting

         String clearMessage = "Dell_CS_HallXXX";
         expected = "DCleSll_XlHX_aX";
         encryptTest(clearMessage, expected, 5, 1);

         clearMessage = "Texas_LonghornsX";
         expected = "Tsnre_gnxLhsaooX";
         encryptTest(clearMessage, expected, 4, 2);

         // *** CS312 students add 2 tests for encrypting with padding
     }


     public static void decryptTest(String encryptedMessage,
             String expected, int rows, int testNumber) {

         System.out.println();
         String actual = decryptMessage(encryptedMessage, rows, true);
         System.out.println("expected: " + expected + ", actual: " + actual);
         if(expected.equals(actual))
             System.out.println("passed decrypt test" + testNumber);
         else
             System.out.println("FAILED DECRYPT TEST " + testNumber);
     }

     public static void encryptTest(String clearMessage,
             String expected, int rows, int testNumber) {

         System.out.println();
         String actual = encryptMessage(clearMessage, rows, false);
         System.out.println("expected: " + expected + ", actual: " + actual);
         if(expected.equals(actual))
             System.out.println("passed encrypt test" + testNumber);
         else
             System.out.println("FAILED ENCRYPT TEST " + testNumber);
     }
     
     public static String encryptMessage(String message, int rows, Boolean padding) {
         System.out.print("Encrypted with " + rows + " rows: ");
         int columns = message.length() / rows + 1;
         int position = 0;
         char[][] temp;
         temp = new char [rows][columns];
         
         for (int i = 0; i < columns; i++) {
             for (int j = 0; j < rows; j++) {
                 if (position < message.length()) {
                     temp[j][i] = message.charAt(position);
                 }
                 else if (padding == true) {
                     temp[j][i] = 'X';
                 }
                 position++;
             }
         }
         position = 0;
         char[] charArrays = new char[rows*columns];
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < columns; j++) {
                 charArrays[position] = temp[i][j];
                 position++;
             }
         }
         String encryptedMessage = new String(charArrays);
         System.out.print(encryptedMessage); 
         System.out.println();
         
         return encryptedMessage;
     }
     
     public static String decryptMessage(String message, int rows, Boolean padding) {
         System.out.println();
         int columns = message.length() / rows + 1;
         int position = 0;
         char[][] temp = new char [rows][columns];
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < columns; j++) {
                 if (position < message.length()) {
                     temp[i][j] = message.charAt(position);
                }
                else if (padding == true) {
                    temp[i][j] = 'X';
                }
                 position++;
             }
         }
         position = 0;
         char[] charArrays = new char[rows*columns];
         for (int i = 0; i < columns; i++) {
             for (int j = 0; j < rows; j++) {
                 charArrays[position] = temp[j][i];
                 position++;
             }
         }
         System.out.print("Decrypted with " + rows + " rows: ");
         String decryptedMessage = new String(charArrays);
         System.out.print(decryptedMessage); 
         System.out.println(); 
         
         return decryptedMessage;
     }
}
