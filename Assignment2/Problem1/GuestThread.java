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

    // ========================================= Functions =========================================
    public GuestThread(int threadNumber) {
        this.threadNumber = threadNumber;
        }

    @Override
    public synchronized void run() {
        System.out.println("Thread (" + threadNumber + ") run.");
    }

}