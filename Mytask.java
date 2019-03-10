
import java.util.*;
/**
 *Class Mytask's purpose is to get user input using multithreading. 
 */
public class Mytask extends Thread {
	char s;
	char t;
	char o;
	public void run(){
		Scanner sc = new Scanner(System.in);
		boolean ex = false;

		while(ex==false) {
			t = sc.next().charAt(0);
			if(t=='a' && s=='d'){
				t = 'd';
			}
			else if(t=='d' && s=='a'){
				t = 'a';
			}
			else if(t=='w' && s=='s'){
				t = 's';
			}
			else if(t=='s' && s=='w'){
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
