package myDiningPhilosopher;

import java.util.Random;
import java.util.concurrent.locks.Lock;

public class Philosophers implements Runnable
{
	private final int id;
	private final Lock leftChopstick;
	private final Lock rightChopstick;
	private Random random = new Random();
	
	public Philosophers(int id, Lock leftChopstick, Lock rightChopstick)
	{
		this.id = id + 1;
		this.leftChopstick = leftChopstick;
		this.rightChopstick = rightChopstick;
	}
	
	public void run() 
	{
		try
		{
			while(true)
			{
				think();
				pickUpLeftChopstick();
				pickUpRightChopstick();
				eat();
				putDownChopsticks();
			}
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}


	private void think()
	{
		System.out.println("Philsopher " + id + " is thinking...");
		try {
			Thread.sleep(random.nextInt(1500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void pickUpLeftChopstick() throws InterruptedException 
	{	
		leftChopstick.lock();
		System.out.println("Philosopher " + id + " picked up the left Chopstick.");
		long begin = System.currentTimeMillis();
		/*
		while(rightChopstick.tryLock() == false)
		{
			if(rightChopstick.tryLock() == true)
			{pickUpRightChopstick();}
			long end = System.currentTimeMillis();
			if(end - begin == 5000)
			{
				leftChopstick.unlock();
				System.out.println("Philosopher " + id + " timed out and dropped his Chopstick.");
				Thread.sleep(random.nextInt(750));
				run();
			}
		} */
	}

	private void pickUpRightChopstick() 
	{
		rightChopstick.lock();
		System.out.println("Philosopher " + id + " picked up the right Chopstick.");
	}

	private void eat() throws InterruptedException 
	{
		System.out.println("Philosopher " + id + " is now eating. Yum");
		Thread.sleep(random.nextInt(1000)+1000);
	}

	private void putDownChopsticks() throws InterruptedException 
	{
		leftChopstick.unlock();
		rightChopstick.unlock();
		System.out.println("Philosopher " + id + " finished eating.");
		Thread.sleep(random.nextInt(1000)+1500);
	}
	
	
}
