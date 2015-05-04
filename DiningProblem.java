package myDiningPhilosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningProblem 
{
	private final static int NUM_OF_PHILOSOPHERS = 5;
	
	public static void main(String[] args)
	{
		Lock[] Chopsticks = new Lock[NUM_OF_PHILOSOPHERS];
		Philosophers[] philosopher = new Philosophers[NUM_OF_PHILOSOPHERS];
		
		for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++)
		{
			Chopsticks[i] = new ReentrantLock();
		}
		
		for (int i = 0; i < NUM_OF_PHILOSOPHERS; i++)
		{
			philosopher[i] = new Philosophers(i,Chopsticks[i],Chopsticks[(i+1)/NUM_OF_PHILOSOPHERS]);
			new Thread(philosopher[i]).start();
		}
	}
}
