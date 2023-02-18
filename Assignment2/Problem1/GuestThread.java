// Aribel Ruiz
// 02/18/2023

// ======================================================================
// COP4520 : Guest Thread for Minoraurs Birthday party (GuestThread.java)
// ======================================================================
//      This program represents each guest going through the maze as a thread.

import java.util.*;

public class GuestThread implements Runnable {

    // ====================================== Class Variables ======================================
    private int threadNumber;

    // Boolean is set to true if a guest has previously eaten cake
    private boolean ateCake;

    // ========================================= Functions =========================================

    public GuestThread(int threadNumber, boolean ateCake) {
        this.threadNumber = threadNumber;
        this.ateCake = ateCake;
    }

    @Override
    public synchronized void run() {
        System.out.println("Thread (" + threadNumber + ") run.");

        if (threadNumber == MinotaursBirthday.firstGuest) {
            System.out.println("======================  FIRST GUEST (" + threadNumber + ") ====================== ");
            
            if (MinotaursBirthday.cakeOnTable == false) {
                System.out.println("Placing new cake on table.");
                MinotaursBirthday.cakeOnTable = true;
                MinotaursBirthday.firstGuestCakeReset++;

                // Checks if all other guests (excluding the first guest) have entered the maze
                if(MinotaursBirthday.firstGuestCakeReset == (MinotaursBirthday.numOfGuests - 1)) {
                    
                    // All other guests have entered maze, first guest can now eat cake and end party
                    System.out.println("Can now eat cake.");
                    MinotaursBirthday.guestAteCake[threadNumber] = true;

                    // Increasing firstGuestCakeReset once more to end party
                    MinotaursBirthday.firstGuestCakeReset++;
                }
            }
            System.out.println("firstGuestCakeReset: " + MinotaursBirthday.firstGuestCakeReset);
            System.out.println("========================================================== ");
        }
        else {
            if (MinotaursBirthday.cakeOnTable == true) {
                if (ateCake == false) {
                    this.ateCake = true;
                    MinotaursBirthday.guestAteCake[threadNumber] = ateCake;
                    MinotaursBirthday.cakeOnTable = false;
                    System.out.println("Guest (" + threadNumber + ") ate cake set to " + ateCake + ".");
                }
                else {
                    System.out.println("Guest (" + threadNumber + ") previously ate cake.");
                }
            }
            else {
                System.out.println("Guest (" + threadNumber + ") leaving table as is. No cake on table.");
            }
        }
    }

}