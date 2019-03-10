import java.util.Random;
/**
 *This class contains all the elements found in the board
 *including the snake, food, moving Scissor, and the board array itself.
 *@author  TJ Libago
 */
class Linkedstack {
	Node head; Node tail; Node badfood;
	Node food; int counting; //Node scissors;
	String[][] arr; int score; boolean walls;
	//boolean theendgame; boolean reachedlast;
	
	Linkedstack() { //constructor
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

		/*Node scissors1 = new Node();
		scissors1.x = 0;
		scissors1.y = 7; //scissors will spawn from the middle
		scissors = scissors1;*/

		Node badfood1 = new Node();
		badfood = badfood1;
		//reachedlast = false;
		this.score=0;
		this.walls = false;
	}
	/**
	 *
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

	public void add() { //when eating food
		//Node temp;
		Node node = new Node();
		this.tail.next = node;
		this.tail = node;
	}
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
	
	public void update() {
		int i,j;
		Node temp;

		for(i=0;i<15;i++){		
			for(j=0;j<15;j++) {
				if(i == 0 || i == 14) {
					this.arr[i][j]="."; //border dots
				}
				else if(j == 0 || j == 14) {
					this.arr[i][j]="."; //border dots
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
		for(i=0; i<15;i++){ 	//prints array
			for(j=0; j<15;j++) {
				System.out.print(this.arr[i][j]+ " ");
			} System.out.println();
		}
	}
	/**
	 *This method creates a (good)food and makes sure it doesn't 
	 *spawn on spaces that are occupied by other (bad)food and the snake.
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
	 *This method creates a badfood and makes sure it doesn't 
	 *spawn on spaces that are occupied by other food and the snake.
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
	public int Scoring() {
		return this.score;
	}
	
	public void walls(int i) {
		if(i == 1) {
			this.walls = false;
		}else if(i == 2) {
			this.walls = true;
		}
	}
}
