# COP4520_Assignment2
Concepts of Parallel and Distributed Processing - Assignment 2. 
This assignment solves 2 different riddles using threads.

# Problem 1: Minotaur's Birthday Party
## How to Run Program 1 (Problem 1)
This program was written using java. To run problem 1, cd into the Assignment2/Problem1 directory and enter the following into the command line:

    javac MinotaursBirthday.java
    java MinotaursBirthday.java

## Program Description (Problem 1)
The program for problem 1 simulates a winning strategy for the 'Minotaur's Birthday Party' problem. 

The output on the command line is the execution time of the program and an array of booleans where guestsAteCake[k] = true when guest ***k*** has entered a labyrinth and eaten a cupcake at the minotaur's birthday party.

### Minotaur's Birthday Party Problem
In the 'Minotaur's Birthday Party' problem, a minotaur invites N guests to a party and starts a game where the minotaur picks guests randomly to enter his labyrinth one at a time. A cupcake is initially placed at the end of the labyrinth. When a guest reaches the cupcake at the end of the labyrinth, they can decide to eat it or leave it alone. If the cupcake was eaten by the previous guest in the labyrinth, the current guest can request another cupcake and then decide whether they want to eat it or leave it alone. Guests can be called multiple times to enter the labyrinth.

The guests must successfully announce when everyone has entered the labyrinth to win the game, but the guests are not allowed to communicate with each other about their visit to the labyrinth at any point in time.

The Minotaur allows the guests to come up with a strategy before the start of the game. Therefore, the guests must come up with a winning strategy that will allow them to successfully decide when every guest has entered the maze without communicating with each other once the game starts.

## Summary of Approach (Problem 1)
My approach to problem 1 is conceptually similar to the solution of the Prisoner Switch problem we saw in class.

### Conceptual Approach 
The winning strategy that I simulated in my solution involves the following: 
- The first guest to enter the labyrinth is in charge of replacing the cupcake everytime they enter the maze and there is no cupcake on the table. 

- Each other guest to enter the labyrinth is to eat the cupcake the first time they see it on the table. Otherwise, they leave the cupcake and table alone.

- The first guest then counts how many times they have to replace the cupcake. Once the guest counts (N-1) times, every other guest (excluding themself) has entered the maze.

### Coding Approach
My coding approach to this problem involved the use of threads and the join() method.

I represented each guest that enters the labyrinth(maze) at the minotaur's birthday party as a new thread. Each thread that is created is passed a thread number ***k*** and a boolean guestsAteCake[k], where guestsAteCake[k] = true if a guest ***k*** has entered the maze. Each thread (or guest) only has access to whether or not they have entered the maze; they are not given any information about whether or not other guests have entered the maze and eaten a cupcake.

Guests are randomly chosen (from 0 to N) to enter the maze through a random generator. The first guest (thread number) is randomly chosen (from 0 to N) and then stored in a public integer variable called firstGuest. This variable determines the action of the first guest when the thread is running.

Once a guest enters a maze and a new thread is created, I use the join() method to assure that the new thread begins to run after the previous thread has finished running. This way, there is only one guest in the maze at a time.

If the thread number of the thread is equal to the public integer firstGuest, the guest checks if there is a cake on the table. If there is no cake on the table, the guest replaces the cake on the table by setting a public boolean cakeOnTable to true and increments a public firstGuestCakeReset counter. The guest then checks if the firstGuestCakeReset counter is equal to N-1. If the counter equals N-1, then every other guest has entered the maze and eaten cake. The first guest can then eat cake and end the party.

If the thread number is not the first guest, the guest ***k*** uses the boolean guestsAteCake[k] to check whether or not they themself have entered the maze. If there is cake on the table and they have never entered the maze, the cakeOnTable boolean is set to false and guestAteCake[k] is set to true.

## Experimental Evaulation (Problem 1)
I first tested my program with a small number, such as 10, to test that my algorithm was working correctly. To test that it was working correctly, I had print statements at every critical point including when the firstGuest was selected, when a thread was created, and what actions each thread took once the guest was in the maze. After assuring the logic was working correctly through print statements, I printed firstGuestCakeReset counter to assure that the first guest reset the cake N times (N-1 times for all other guests + 1 time for himself). I also printed the guestsAteCake[] to assure that every index of the array was true; proving that every guest had entered the maze because the index is only set to true when a guest enters the maze for the first time and eats cake.

After testing the program with small cases, I incremented the number of guests manually in my program and assured the program worked corrrectly with large values of n (such as values n=50, n=100, n=200, etc.). To assure that all guests entered the maze for larger numbers, I iterated through guestsAteCake[] at the end of the program and checked if any value in guestsAteCake[] was false. Otherwise, it prints that all guests have entered the maze and eaten cake.

## Reasoning for Efficiency (Problem 1)
Each guest must wait for the previous guests to exit the maze before entering, as th emaze can only have 1 guest at a time. Therefore, I was unable to run thread concurrently to improve efficiency. 

I used the join() method to assure that every thread created waited for the previous thread to finish before running. Due to this approach, the execution time varies depending on how many guests, N, were invited to the party and a game of chance due to the random number generator possibly generating guests who have already entered the maze a significant amount of times to enter the maze. As there is no way to solve the problem concurrently, my approach is most efficient.

I also assured to use a boolean array for guestsAteCake[] to decrease the amount of memory used using another data type.

# Problem 2: Minotaur's Crystal Vase
## How To Run Program 2 (Problem 2)
This program was written using java. To run problem 2, cd into the Assignment2/Problem2 directory and enter the following into the command line:

    javac MinotaursVase.java
    java MinotaursVase.java


    

