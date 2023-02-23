// Aribel Ruiz
// 02/18/2023

// ======================================================================
// COP4520 : Guest Thread for Minoraurs Crystal Vase (GuestThread.java)
// ======================================================================
//      This program represents each guest checking the showroom to see a crystal vase.

import java.util.concurrent.TimeUnit;

public class GuestThread implements Runnable {

    // ====================================== Class Variables ======================================
    private int threadNumber;
    private boolean lockAcquired;

    private boolean firstTimeGuest = false;

    // ========================================= Functions =========================================

    public GuestThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public synchronized void run() {
        try {
            // System.out.println("Thread (" + threadNumber + ") run.");

            // If showroom is available, let guest enter showroom
            if (MinotaursVase.showroomSign == MinotaursVase.AVAILABLE) {
                lockAcquired = MinotaursVase.lock.tryLock(1, TimeUnit.SECONDS);

                if (lockAcquired) {
                    try {
                        // If guest acquires lock, set showroom to BUSY
                        MinotaursVase.showroomSign = MinotaursVase.BUSY;
    
                        // Update guest number to have seen vase
                        if (MinotaursVase.guestsSeenVaseArr[threadNumber] == false) {
                            MinotaursVase.guestsSeenVaseArr[threadNumber] = true;
                            firstTimeGuest = true;
                        }
                    } finally {
                        // After seeing vase, set showroom to available and unlock lock
                        MinotaursVase.showroomSign = MinotaursVase.AVAILABLE;
                        MinotaursVase.lock.unlock();
                        
                        // Keeps track of how many guests have seen the vase
                        if (firstTimeGuest)
                            MinotaursVase.guestsSeenVase++;                        
                    }
                }
            }
            // Otherwise, showroom is busy and guest does not attempt to enter

        } catch(InterruptedException e) {
            System.out.println("An error occured");
        }
    }
}