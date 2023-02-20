// Aribel Ruiz
// 02/18/2023

// ===========================================================================
// COP4520 : Assignment 2 - Minotaur's Crystal Vase (MinotaursVase.java)
// ===========================================================================
//      Concepts of Parallel and Distributed Processing Assignment 2. 
//      This program simulates strategy 2 for Problem 2 of Assignment 2, Minotaur's Crystal Vase.

import java.util.*;

public class MinotaursVase {

    // ====================================== Class Variables ======================================

    const private int numOfGuests = 10;

    const private boolean AVAILABLE = true;
    const private boolean BUSY = false;

    public static boolean showroomSign = AVAILABLE; // possibly use lock

    // ========================================= Functions =========================================
    public static void main(String args[]) {

        for (int i = 0; i < 10; i++) {
            // Creates and joins new guest thread
            GuestThread guestThread = new GuestThread(i);
            Thread newThread = new Thread(guestThread);
            newThread.start();
        }
    }
}