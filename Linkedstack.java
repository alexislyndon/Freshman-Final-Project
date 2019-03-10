import java.util.Random;

/**
 *This class contains all the elements found in the board.
 *Including the snake, food, moving Scissor, and the board array itself.
 *@author  Tomas Jubile Libago
 *@author Alexis Lyndon Galaura
 *@author Youssef Hisham
 */
public class Linkedstack {
	Node head; Node tail; Node badfood;
	Node food; int counting;
	String[][] arr; int score; boolean walls; int speed;
	
	/**
	 *Zero args constructor sets the size of the board creates food. 
	 */
	public Linkedstack() { 
		Random rand = new Random(); 
		this.counting=1;
		this.arr=new String[15][15]; //sets size of board
		Node firstnode = new Node(); 
		firstnode.x = 0;	firstnode.y = 0; //where the snake starts
		//theendgame=false;

		Node food1 = new Node();
		food1.x = 3; //food will always start 3 spaces to the right from origin
		food1.y = 0;
		this.food = food1;
		head=tail=firstnode;

		Node badfood1 = new Node();
		badfood = badfood1;
		//reachedlast = false;
		this.score=0;
		this.walls = false;
	}
	/**
	 *Removes the tail(last link) section of the snake.
	 */
	public void deleteTail() { //for badfood
		Node temp;
		if(this.tail==this.head) { //if there's only 1 section of snake left
			this.tail=null;
			this.head=null;
		}
		else {
			for(temp=head;temp.next!=null;temp=temp.next) {
				if(temp.next==tail) {
					break;
				}
			}
			temp.next=null;
			this.tail=temp;
		}
	}
	
	/**
	 *This method adds 1 section after tail and sets it as tail.
	 */
	public void add() { //when eating food
		//Node temp;
		Node node = new Node();
		this.tail.next = node;
		this.tail = node;
		if(this.speed >= 90){
			this.speed = speed - 5;
		}
	}
	
	/**
	 *This method take user {@link Mytask#Input() input} as parameter and moves the snake head to that direction.
	 *The body of the snake follows the head.
	 *@return boolean if snake dies - to break loop on {@linkplain Main#main(String[] args) main} method.
	 *@see Mytask#Input()
	 *@param move user input. 
	 */
	public boolean Coordinatesmove(char move){
		Node temp;
		int tempx=head.x;
		int tempy=head.y;
		
		if(move=='a') {
			if((this.head.x-1 == -1) && (this.walls == false)) {
				this.head.x = 14; //jump to the other side - walls off
			}
			else{
			head.x=head.x-1;
			}
		}
		else if(move=='s') {
			if((this.head.y+1 == 15) && (this.walls == false)) {
				this.head.y = 0;//jump to the other side - walls off
			}
			else {
				head.y=head.y+1;
			}
		}
		else if(move=='d') {
			if((this.head.x+1 == 15) && (this.walls == false)) {
				this.head.x = 0;//jump to the other side - walls off
			}
			else {
				head.x=head.x+1;
			}
		}

		else if(move=='w') {
			if((this.head.y-1 == -1) && (this.walls == false)) {
				this.head.y = 14;//jump to the other side - walls off
			}
			else {
				head.y = head.y-1;
			}
		}
		
		int tempx1, tempy2;
		for(temp = head.next; temp != null; temp = temp.next) {
			tempx1=temp.x;
			tempy2=temp.y;
			temp.x=tempx;
			temp.y=tempy;
			tempx=tempx1;
			tempy=tempy2;
		}
		
		for(temp=head.next; temp!=null; temp=temp.next)  { 
			if(this.head.x==temp.x&&this.head.y==temp.y) { //checks if head collides with other sections of snake
				System.out.println("Oops you ate your own tail");
				System.out.println("u ded. Gim uber");
				update();
				return true;
			}
		}update(); return false;
	}

	/**
	 *Mayn method checks whether foods in the board are eaten and proceeds to add more food and add to score. 
	 *It also checks if badfood is eaten and decrease score.
	 *@see #Foodgene
	 *@see #badfoodgene
	 *@param move {@link Mytask#Input() move} user input.
	 *@return boolean 
	 */
	public boolean Mayn(char move) {

		if(counting==1) {
			badfoodgene();
		}
		if(this.head.x == food.x && this.head.y == food.y) {	//checks if head eats food
			add();
			Foodgene();
			this.score++;
			this.counting++; //for bad food to appear when counting%5=0
		}
		
		if((this.counting%5)==0) {
			if(this.head.x==this.badfood.x&&this.head.y==this.badfood.y) {//checks if head eats badfood 
				deleteTail();
				badfoodgene();
				this.score--;
				if(this.head==null&&this.tail==null) {
					System.out.println("Oopx too much badfood can kill you");
					return true;
				}
			}
		} return Coordinatesmove(move);
	}
	
	/**
	 *This method prints everything in the board.
	 */
	public void update() {
		int i,j;
		Node temp;
		
		for(i=0;i<15;i++){ 
			for(j=0;j<15;j++) {
				if(i == 0 || i == 14) {
					this.arr[i][j]=" "; //border dots
				}
				else if(j == 0 || j == 14) {
					this.arr[i][j]=" "; //border dots
				}
				else this.arr[i][j]=" "; //the board
			}
		}

		if((this.counting%5)==0) { // bad food in arr
			arr[this.badfood.y][this.badfood.x]="8";
		}

		for(temp=this.head;temp!=null;temp=temp.next) { 
			this.arr[temp.y][temp.x]="S"; //S means snake
		}this.arr[this.head.y][this.head.x]="H";
		arr[this.food.y][this.food.x]="o";	// o means food

			//everything before this line fills the array.
		//after this line prints the array(board)
		System.out.println(". . . . . . . . . . . . . . . ..");
		for(i=0; i<15;i++){ 	//prints array
			System.out.print(".");
			for(j=0; j<15;j++) {
				System.out.print(this.arr[i][j]+ " ");
				
			} System.out.print("."); System.out.println();
		}System.out.println(". . . . . . . . . . . . . . . ..");
	}
	/**
	 *Spawns (good)food.
	 *@see #badfoodgene()
	 *Sets food in the board.
	 */
	public void Foodgene() {
		Random rand = new Random();
		boolean found=false;
		int randomx;
		int randomy;
		while(found==false) {
			randomx = rand.nextInt(14);
			randomy = rand.nextInt(14);
			if(this.arr[randomy][randomx]!="S" && this.arr[randomy][randomx]!="8"  && this.arr[randomy][randomx]!="H") { 
				this.food.x=randomx;
				this.food.y=randomy;
				found = true;
			}
		}
	}
	/**
	 *Spawns badfood.
	 *@see #Foodgene()
	 *Sets badfood in the board.
	 */
	public void badfoodgene() {
		Random ran = new Random();
		boolean found = false;
		int randomx;
		int randomy;
		while(found==false) { 
			randomx=ran.nextInt(14);
			randomy=ran.nextInt(14);
			if(this.arr[randomy][randomx]!="S"&&this.arr[randomy][randomx]!="o"&&this.arr[randomy][randomx]!="H") {
				this.badfood.x=randomx;
				this.badfood.y=randomy;
				found = true;
			}
		}
	}
	
	/**
	 *Gets score.
	 *@return {@link #score}
	 */
	public int Scoring() {
		return this.score;
	}
	
	/**
	 *Sets game mode - [1] - no walls [2] - walled.
	 *@param i user input - mode.
	 */
	public void walls(int i) {
		if(i == 1) {
			this.walls = false;
		}else if(i == 2) {
			this.walls = true;
		}
	}
	
	public int Speed(int s) {
		if(s == 1) {
			return  600;
		}
		else if(s == 2) {
			return  400;
		}
		else if(s == 3) {
			return  200;
		}
		else if(s == 4) {
			return  90;
		}
		return 400;
	}
}
