import java.util.*;

public class Main{
	/**
	 *This is the main method. Creates new objects of class Linkedstack and Mytask.
	 *This method also starts a new thread that handles user input.
	 */
	public static void main (String[] args){
		Linkedstack list = new Linkedstack();
		Mytask task = new Mytask();
		task.start();
		boolean getout = false;
		while(getout==false) {
			try {
				delay();
				getout=list.Mayn(task.Input());
				System.out.println();
			}
			catch(IndexOutOfBoundsException e){  //end game if mogawas sa array
				System.out.println("You hit a wall. You lose");
				getout=true;
			}
		}
	}
	/**
	 *Puts the game thread to sleep
	 *every 1 second to simulate
	 *the game refreshing
	 */
	public static void delay() {  // gisearch ra nako ning delay
		try {
		System.out.println();
		Thread.sleep(500);
		}
		catch(java.lang.InterruptedException e) {
        System.out.println(e);
		}
    }
}
