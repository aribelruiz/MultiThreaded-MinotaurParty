// Aribel Ruiz
// 02/18/2023

// ===========================================================================
// COP4520 : Assignment 2 - Minotaur's Birthday Party (MinotaursBirthday.java)
// ===========================================================================
//      Concepts of Parallel and Distributed Processing Assignment 2. 
//      This program simulates a winning strategy for Problem 1 of Assignment 2, Minotaur's Birthday
//       Party.

import java.util.*;

public class MinotaursBirthday {

    public static void main(String[] args) {
        
        // Takes number of guests (integer) as input
        Scanner sc = new Scanner(System.in);
        System.out.println("How many guests are at the Minotaur's Party: ");
        int numOfGuests =  sc.nextInt();

        // Creating a new thread for each guest at the Minotaur's Party
        for (int i = 0; i < numOfGuests; i++)
        {
            GuestThread guestThread = new GuestThread(i);
            Thread newThread = new Thread(guestThread);
            newThread.start();

            try {
                newThread.join();
            } catch (Exception e) {
                System.out.println("\nAn error occured joning a thread.");
            }

        }

    }
}