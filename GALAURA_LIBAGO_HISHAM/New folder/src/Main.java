import java.util.*;
/**
 *Console Snake Game written in Java!
 *@author  Tomas Jubile Libago
 *@author Alexis Lyndon Galaura
 *@author Youssef Hisham
 */
public class Main{
	/**
	 *This is the main method. Creates new objects of class Linkedstack and Mytask.
	 *This method also starts a new thread that handles user input.
	 @param args not used
	 */
	public static void main (String[] args){
		Scanner sc = new Scanner(System.in);
		Linkedstack list = new Linkedstack();
		Mytask task = new Mytask();
		int score;
		System.out.println("1 - No walls mode");
		System.out.println("2 - Wall mode");
		System.out.println("WASD for movement \nExample: D+[Enter]\n");
		int choice=sc.nextInt();
		list.walls(choice);
		System.out.println("\nSelect Speed");
		System.out.println("1 - Basic \n2 - Default\n3 - Fast\n4 - Epileptic");
		System.out.println("\nWASD for movement \nExample: A+[Enter]");
		System.out.println("");
		int s = sc.nextInt();
		list.speed = list.Speed(s); //speed here
		
		
		task.start(); //executes run() method in class Mytask
		boolean getout = false;
		
		if(choice==1) {
			while(getout==false) {
				//System.out.println(Thread.currentThread().getName() +" " + "THIS IS THE MAIN THREAD");
				delay(list.speed);
				getout=list.Mayn(task.Input());   //receives input from the thread passes back boolean
				System.out.println();
				//System.out.println(list.speed);
				
			}
			score=list.Scoring();
			System.out.println("The length of your tail is: "+score);
		}
		else if(choice == 2) {
			while(getout==false) {
				try {
				delay(list.speed);
				getout=list.Mayn(task.Input());
				System.out.println();
				
				}catch(IndexOutOfBoundsException e){  //end game if mogawas sa array
					score=list.Scoring();
					System.out.println("You hit a wall. You lose");
					System.out.println("The length of your tail is: "+score);
					getout=true;
				}
			}
		}
	}
	/**
	 *Puts the game thread to sleep every 400ms to simulate the game refreshing.
	 *Without this method, the game would refresh very fast. Each refresh means 1 square traversed by the snake.
	 *@param s user defined then later minus 5ms per good food eaten.
	 */
	public static void delay(int s) {  // gisearch ra nako ning delay
		try {
			System.out.println();
			Thread.sleep(s);
		}
		catch(java.lang.InterruptedException e) {
			System.out.println(e);
		}
    }
}