import java.util.*;


class Mytask extends Thread {
	char s;
	public void run(){ //thread asks for input constantly
		Scanner sc = new Scanner(System.in);
		boolean ex = false;
		while(ex==false){
		s = sc.next().charAt(0);
		  if(s=='z'){
			ex=true;
			}
		}
	}
	
	public char Input(){
		return s;
	}
}

public class Main{
	public static void main (String[] args){
    Linkedstack list = new Linkedstack();
    Mytask task = new Mytask();
    task.start();
    boolean getout = false;
    while(getout==false) {
		try {
			list.update();
			delay();
			list.moveDirection(task.Input());
			System.out.println();
		}
        catch(IndexOutOfBoundsException e){  //end game if mogawas sa array
            System.out.println("You hit a wall. You lose");
            getout=true;
        }
	}
}

	public static void delay(){  // gisearch ra nako ning delay
		try {
			System.out.println();
			Thread.sleep(500);
		}
		catch(java.lang.InterruptedException e) {
			System.out.println(e);
		}
	}
} 