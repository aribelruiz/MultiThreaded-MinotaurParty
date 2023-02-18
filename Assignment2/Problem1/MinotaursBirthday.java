// Aribel Ruiz
// 02/18/2023

// ===========================================================================
// COP4520 : Assignment 2 - Minotaur's Birthday Party (MinotaursBirthday.java)
// ===========================================================================
//      Concepts of Parallel and Distributed Processing Assignment 2. 
//      This program simulates a winning strategy for Problem 1 of Assignment 2, Minotaur's Birthday
//       Party.

import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class MinotaursBirthday {

    // ====================================== Class Variables ======================================
    public static int firstGuest = 0;
    public static int numOfGuests =  100;
    private static int guestInMaze = 0;

    // Boolean array where guestAteCake[i] == false when guest i has eaten cake
    public static boolean[] guestAteCake = new boolean[numOfGuests]; 

    // Variable keeps track of how many times the first guest has reset the cake
    public static int firstGuestCakeReset = 0;

    // Boolean determines if cupcake is on the table at end of maze
    public static boolean cakeOnTable = true;

    // ========================================= Functions =========================================
    public static void main(String[] args) {

        // Starts timer
        long start = System.nanoTime();

        // Creates randomizer from 0 to numOfGuests-1
        Random rand = new Random(); 
        guestInMaze = rand.nextInt(numOfGuests);

        // Sets firstGuest in maze
        firstGuest = guestInMaze;
        System.out.println("First guest is: " + firstGuest);

        // Creating a new thread for each guest at the Minotaur's Party
        while (firstGuestCakeReset != numOfGuests)
        {
            // Creates and joins new guest thread
            GuestThread guestThread = new GuestThread(guestInMaze, guestAteCake[guestInMaze]);
            Thread newThread = new Thread(guestThread);
            newThread.start();

            try {
                newThread.join();
            } catch (Exception e) {
                System.out.println("\nAn error occured joning a thread.");
            }

            // Gets next guest to enter maze
            guestInMaze = rand.nextInt(numOfGuests);
        }

        // Ends timer
        long end = System.nanoTime();

        // Prints execution time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time: " + formatter.format((end - start) / 1000000000d) + "s");

        // Prints guestsAteCake to assure all guests ate
        System.out.println("\nguestsAteCake[" + numOfGuests + "]: ");
        System.out.println(Arrays.toString(guestAteCake) + "\n");

    }
}