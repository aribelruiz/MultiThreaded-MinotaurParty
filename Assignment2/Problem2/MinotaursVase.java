// Aribel Ruiz
// 02/18/2023

// ===========================================================================
// COP4520 : Assignment 2 - Minotaur's Crystal Vase (MinotaursVase.java)
// ===========================================================================
//      Concepts of Parallel and Distributed Processing Assignment 2. 
//      This program simulates strategy 2 for Problem 2 of Assignment 2, Minotaur's Crystal Vase.

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MinotaursVase {

    // ====================================== Class Variables ======================================

    private static int nextGuestInRoom = 0;
    private static int numOfGuests = 100;

    public static int guestViewingVase;
    public static int guestsSeenVase = 0;
    public static boolean[] guestsSeenVaseArr = new boolean[numOfGuests];

    public final static boolean AVAILABLE = true;
    public final static boolean BUSY = false;
    public static boolean showroomSign = AVAILABLE; 


    public static Lock lock = new ReentrantLock();

    // ========================================= Functions =========================================
    public static void main(String args[]) {
        
        // Starts timer
        long start = System.nanoTime();

        // Creates randomizer from 0 to numOfGuests-1
        Random rand = new Random(); 
        nextGuestInRoom = rand.nextInt(numOfGuests);

        // Continues selecting guests who want to see the vase
        while(guestsSeenVase != numOfGuests) {

            // Creates new guest thread to enter showroom if showroom is available
            if ((showroomSign == AVAILABLE) && (nextGuestInRoom != guestViewingVase)) {
    
                // Update guestViewing vase
                guestViewingVase = nextGuestInRoom;
                
                // Allows guest to enter show room
                GuestThread guestThread = new GuestThread(guestViewingVase);
                Thread newThread = new Thread(guestThread);
                newThread.start();
            } 
            else {
                // Gets next guest who wants to check to see vase
                nextGuestInRoom = rand.nextInt(numOfGuests);
            }
        }

        // Ends timer
        long end = System.nanoTime();

        
        // Prints execution time
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time: " + formatter.format((end - start) / 1000000000d) + "s");

        // Prints guestsSeenVase[] to assure all guests have seen vase
        System.out.println("\nguestsSeenVase[" + numOfGuests + "]: ");
        System.out.println(Arrays.toString(guestsSeenVaseArr) + "\n");
        
    }
}