# Multi-threaded Practice (Minotaur Problems)
Below you can find the ReadMe for two separate multi-threaded problems. 



# Problem 1: Minotaur's Birthday Party
## How to Run Program 1 (Problem 1)
This program was written using java. To run problem 1, cd into the Assignment2/Problem1 directory and enter the following into the command line:

    javac MinotaursBirthday.java
    java MinotaursBirthday.java

## Program Description (Problem 1)
The program for problem 1 simulates a winning strategy for the 'Minotaur's Birthday Party' problem. The output on the command line is the execution time of the program and an array of booleans where guestsAteCake[k] = true when guest ***k*** has entered a labyrinth and eaten a cupcake at the minotaur's birthday party.

### Minotaur's Birthday Party Problem
In the 'Minotaur's Birthday Party' problem, a minotaur invites N guests to a party and starts a game where the minotaur picks guests randomly to enter his labyrinth one at a time. A cupcake is initially placed at the end of the labyrinth. When a guest reaches the cupcake at the end of the labyrinth, they can decide to eat it or leave it alone. If the cupcake was eaten by the previous guest in the labyrinth, the current guest can request another cupcake and then decide whether they want to eat it or leave it alone. Guests can be called multiple times to enter the labyrinth.

The guests must successfully announce when everyone has entered the labyrinth to win the game, but the guests are not allowed to communicate with each other about their visit to the labyrinth at any point in time.

The Minotaur allows the guests to come up with a strategy before the start of the game. Therefore, the guests must come up with a winning strategy that will allow them to successfully decide when every guest has entered the maze without communicating with each other once the game starts.

## Summary of Approach (Problem 1)
My approach to problem 1 is conceptually similar to the solution of the Prisoner Switch problem we saw in class. The winning strategy that I simulated in my solution involves the following: 
- The first guest to enter the labyrinth is in charge of replacing the cupcake everytime they enter the maze and there is no cupcake on the table. 

- Each other guest to enter the labyrinth is to eat the cupcake the first time they see it on the table. Otherwise, they leave the cupcake and table alone.

- The first guest then counts how many times they have to replace the cupcake. Once the guest counts (N-1) times, every other guest (excluding themself) has entered the maze.

### Coding Approach
I represented each guest that enters the labyrinth(maze) at the minotaur's birthday party as a new thread. Each thread that is created is passed a thread number ***k*** and a boolean guestsAteCake[k], where guestsAteCake[k] = true if a guest ***k*** has entered the maze. Each thread (or guest) only has access to whether or not they have entered the maze; they are not given any information about whether or not other guests have entered the maze and eaten a cupcake.

Guests are randomly chosen (from 0 to N) to enter the maze through a random generator. The first guest (thread number) is randomly chosen (from 0 to N) and then stored in a public integer variable called firstGuest. This variable determines the action of the first guest when the thread is running. Once a guest enters a maze and a new thread is created, I use the join() method to assure that the new thread begins to run after the previous thread has finished running. This way, there is only one guest in the maze at a time.

If the thread number of the thread is equal to the public integer firstGuest, the guest checks if there is a cake on the table. If there is no cake on the table, the guest replaces the cake on the table by setting a public boolean cakeOnTable to true and increments a public firstGuestCakeReset counter. The guest then checks if the firstGuestCakeReset counter is equal to N-1. If the counter equals N-1, then every other guest has entered the maze and eaten cake. The first guest can then eat cake and end the party.If the thread number is not the first guest, the guest ***k*** uses the boolean guestsAteCake[k] to check whether or not they themself have entered the maze. If there is cake on the table and they have never entered the maze, the cakeOnTable boolean is set to false and guestAteCake[k] is set to true.

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

## Program Description (Problem 2)
The program for problem 2 simulates a strategy 2 for the 'Minotaur's Crystal vase' problem. The output on the command line is the execution time of the program and an array of booleans where guestsSeenVaseArr[k] = true when guest ***k*** has entered the showroom and seen the vase at the minotaur's party.
    
### Minotaur's Crystal Vase Problem (Strategy 2)
The Minotaur decides to show his guests a crystal vase in a dedicated showroom with a single door. Only one guest is allowed at a time. Strategy #2 allows guests to place a sign on the door indicating when the showroom is "AVAILABLE" or "BUSY". Every guest is responsible to set the sign to “BUSY” when entering the showroom and back to “AVAILABLE” when they exit. Therefore, guests will not try entering the room if the sign read "BUSY".

## Summary of Approach (Problem 2)
To simulate strategy 2 I used concurrent threads and a lock. I simulated this strategy until every guest at the party has entered the showroom and seen the vase. Each thread represents a guest who attempts to enter the showroom when the showroom sign is "AVAILABLE".

### Coding Approach
To keep track of which guests have seen the vase, I created a boolean array called guestsSeenVaseArr[], where guestsSeenVaseArr[k] = true when guest ***k*** has entered the showroom and seen the vase. I then randomly generated a number ***nextGuestInRoom*** (0 to N-1) to represent a guest wanting to see the vase. If ***nextGuestInRoom*** does not equal the current guest viewing the vase and the showroom is available, I create a new thread where threadNumber equals ***nextGuestInRoom***. 

When a thread begins to run, the thread tries to acquire the lock to ensure only one thread is accessing the showroom sign at a time. If the lock is acquired, the guest sets the showroom sign to "BUSY" and updates the guestsSeenVaseArr[k] to true so that guest who entered the showroom, guest ***k***, has seen the vase. Once the guest has seen the vase, they set the showroom sign to "AVAILABLE" and unlock the lock so other guests may enter the showroom and have access to changing the showroom sign. If the lock is not acquired, this means another guest gained access to the showroom and showroom sign, therefore the room is busy and the guest who failed to acquire the lock must try again at a later time in the party.

## Experimental Evaulation (Problem 2)
To test the simulation, I assured that at the end of the program all guests had seen the vase by checking the guestSeenVaseArr[]. If any value in the array was equal to false, then some of the guests never entered the showroom and saw the vase before the party ended.

To assure that the lock functioned correctly, I used print statements at any critical points of the program to assure that only one thread was accessing the showroomSign variable at a time. To do this, I printed when a thread began, when a thread changes the showroomSign along with the thread number, and when a thread was unable to enter the showroom because the showroom sign was set to9 "BUSY" or when a thread was unable to acquire the lock. 

I first tested with small values of N, where N is the total number of guests. I first tested with a small value of 5 to check the print statements and assure the program was functioning correctly and giving me the proper output. When a guest entered the showroom, the guest switched the sign to "BUSY". Once I saw that no other guests attempted to enter the showroom until the current guest in the showroom switched the showroomSign back to "AVAILABLE", I knew the program was working as inteded. 

## Reasoning for Efficiency (Problem 2)
I chose strategy 2 as the most efficient of all 3 strategies b making a pros and cons list for all 3 strategies listed in the assignment.

Strategy 2 is the best strategy because it allows guests to enjoy the party and removes long lines or crowds from the door because the showroom sign would be visible to all guests wanting to see the vase. Guests would not attempt to enter the showroom is they saw the sign read "BUSY" and they would return to the party and try again at another time. Another pro is that this strategy allows guests to enjoy the party instead of waiting in long lines or large crowds.

The cons for strategy 1 include guests crowding at the door or large crowds trying to enter the showroom at the same time since the door never closes. This can cause dead-locks between threads within a programming implementation and a halt to guests viewing the vase. There is also no guarantee that guests who arrive at the door will go in at the order they arrived due to crowding. I saw no advantages to strategy 1.

The cons of strategy 3 include super long lines forming outside the door due to the queue system. Using strategy 3 also does not allow any guarantee that guests in line will be able to see the vase because if the party ends when all guests have seen the vase, guests who were waiting in line to see the vase again would not get the opportunnity to see the vase again. Therefore with streategy 3, guests would be wasting time in line instead of enjoying the party which is another con. The pros of this stratgy is that guests will be able to see the vase in the order they entered the room and there would be no dead-locks or halt in guests viewing the vase.

Some cons strategy 2 may have is that guests may not see the vase in the order they intially went to see the vase. If the room is busy and a guest decides to return at a later time, they may continue visiting the showroom and see it is busy each time until the party is almost over. Guests having to revisit the room until the showroom is available is a con and may be an annoyance, but the guest will be able to enjoy the party in the meantime and is still guaranteed to see the vase at least once since the party does not end until ALL guests have entered the showroom at least once.
