
import java.util.*;
/**
 *Class Mytask's purpose is to get user input using multithreading. 
 */
public class Mytask extends Thread {
	char s;
	char t;
	public void run(){
		Scanner sc = new Scanner(System.in);
		boolean ex = false;

		while(ex==false) { 
			//System.out.println(Thread.currentThread().getName() +" "+  "this is the thread-0");
			t = sc.next().charAt(0);
			if(t=='a' && s=='d'){//prevent 180 turns
				t = 'd';
			}
			else if(t=='d' && s=='a'){//prevent 180 turns
				t = 'a';
			}
			else if(t=='w' && s=='s'){//prevent 180 turns
				t = 's';
			}
			else if(t=='s' && s=='w'){//prevent 180 turns
				t = 'w';
			}
			else{ 
				s = t;
			}
			if(s=='z'){
					ex=true;
			}
		}
	}
	/**
	 *This method returns the user's input
	 *@return {@link Mytask#s s}
	 */
	public char Input() {
		return s;
	}
}
