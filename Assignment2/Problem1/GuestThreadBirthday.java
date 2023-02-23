// Aribel Ruiz
// 02/18/2023

// ======================================================================
// COP4520 : Guest Thread for Minoraurs Birthday party (GuestThreadBirthday.java)
// ======================================================================
//      This program represents each guest going through the maze as a thread.

public class GuestThreadBirthday implements Runnable {

    // ====================================== Class Variables ======================================
    private int threadNumber;

    // Boolean is set to true if a guest has previously eaten cake
    private boolean ateCake;

    // ========================================= Functions =========================================

    public GuestThreadBirthday(int threadNumber, boolean ateCake) {
        this.threadNumber = threadNumber;
        this.ateCake = ateCake;
    }

    @Override
    public synchronized void run() {
        // System.out.println("Thread (" + threadNumber + ") run.");

        if (threadNumber == MinotaursBirthday.firstGuest) {            
            if (MinotaursBirthday.cakeOnTable == false) {

                // First guest places new cake on table
                MinotaursBirthday.cakeOnTable = true;
                MinotaursBirthday.firstGuestCakeReset++;

                // Checks if all other guests (excluding the first guest) have entered the maze
                if(MinotaursBirthday.firstGuestCakeReset == (MinotaursBirthday.numOfGuests - 1)) {
                    
                    // All other guests have entered maze, first guest can now eat cake and end party
                    MinotaursBirthday.guestAteCake[threadNumber] = true;

                    // Increasing firstGuestCakeReset once more to end party
                    MinotaursBirthday.firstGuestCakeReset++;
                }
            }
        }
        else {
            // Checks if non-first guest ate cake already when there is cake on table
            if (MinotaursBirthday.cakeOnTable == true) {
                
                // If guest never ate cake/entered maze, eat cake on table
                if (ateCake == false) {
                    this.ateCake = true;
                    MinotaursBirthday.guestAteCake[threadNumber] = ateCake;
                    MinotaursBirthday.cakeOnTable = false;
                }

            }
            // Otherwise, there was no cake on table
        }
    }

}