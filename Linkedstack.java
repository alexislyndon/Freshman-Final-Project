import java.util.Random;
/**
 *This class contains all the elements found in the board; including the snake, food, moving Scissor
 */
class Linkedstack {
	Node head;	Node tail; Node badfood;
	Node food; int counting;	 boolean theendgame;
	String[][] arr;
	Node scissors; boolean reachedlast;
	
	Linkedstack() { //constructor
		Random rand = new Random(); 
		this.counting=1;
		this.arr=new String[15][15]; //size of the board
		Node firstnode= new Node(); 
		firstnode.x = 0;	firstnode.y = 0; //where the snake starts
		theendgame=false;

		Node food1 = new Node();
		food1.x = rand.nextInt(14) + 1;
		food1.y = rand.nextInt(14) + 1;
		this.food = food1;
		head=tail=firstnode;

		Node scissors1 = new Node();
		scissors1.x = 0;
		scissors1.y = 7; 
		scissors = scissors1;

		Node badfood1 = new Node();
		badfood = badfood1;
		reachedlast = false;
	}

	public void deleteTail() { //for badfood
		Node temp;
		for(temp=head;temp.next!=null;temp=temp.next) {
			if(temp.next==tail) {
				break;
			}
		}
		temp.next=null;
		this.tail=temp;
	}

	public void add() { //when eating food
		Node temp;
		Node node = new Node();
		this.tail.next = node;
		this.tail = node;
	}
	public void Coordinatesmove(char move){

		Node temp;
		int tempx=head.x;
		int tempy=head.y;
		int tempx1, tempy2;
		for(temp=head.next;temp!=null;temp=temp.next) {
			tempx1=temp.x;
			tempy2=temp.y;
			temp.x=tempx;
			temp.y=tempy;
			tempx=tempx1;
			tempy=tempy2;
		}

		if(move=='a') {
			head.x=head.x-1;
		}
		if(move=='s') {
			head.y=head.y+1;
		}
		if(move=='d') {
			head.x=head.x+1;
		}

		if(move=='w') {
			head.y=head.y-1;
		}
	}

	public boolean Mayn(char move) {

		Node temp;
		if(counting==1) {
				badfoodgene();
		}
		if(this.head.x==food.x&&this.head.y==food.y) {	//checks if head eats food
			add();
			Foodgene();
			this.counting++; //for bad food to appear when counting%5=0
		}
		
		if((this.counting%5)==0) {
			if(this.head.x==this.badfood.x&&this.head.y==this.badfood.y) {//checks if head eats badfood 
				deleteTail();
				badfoodgene();
			}
		}
		if(counting%3==0){
			if(this.head.x==this.scissors.x&&this.head.y==this.scissors.y) {
				System.out.println("Scissors hit your head. Too bad your dead");
				update();
				return true;
			}
			Node tempo;
			for(tempo=head;tempo!=null;tempo=tempo.next) {
				if(tempo.x==this.scissors.x&&tempo.y==this.scissors.y) {
					Node tempora;
					for(tempora=head;tempora.next!=tempo;tempora=tempora.next) {
						this.tail=tempora;
						tempora.next=null;
					}
				}
			} movingScissor();
		}
		Coordinatesmove(move);
		while(counting==3){
			if(this.head.x==this.scissors.x&&this.head.y==this.scissors.y){
				System.out.println("Scissors hit your head. Too bad you're dead");
				update(); //giutro nako pag update para makita ang last print.
				return true;
			} break;
		}

		for(temp=head.next;temp!=null;temp=temp.next) {  //checks if it bite its own tail
			if(this.head.x==temp.x&&this.head.y==temp.y) {
				System.out.println("Oops you ate your own tail");
				System.out.println("u ded. Gym uber");
				return true;
			}
		}
		update();
		return false;
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

		if(this.counting==3||this.counting==20) {
			arr[this.scissors.y][this.scissors.x]="@";
		}

		for(temp=this.head;temp!=null;temp=temp.next) { // snakes 
			this.arr[temp.y][temp.x]="S"; //S means snake
		}
		arr[this.food.y][this.food.x]="o";	// o means food


		for(i=0; i<15;i++){ 	//prints array
			for(j=0; j<15;j++) {
				System.out.print(this.arr[i][j]+ " ");
			} System.out.println();
		}
	}

	public void Foodgene() {
		Random rand = new Random();
		boolean reachedlast = false;		boolean found=false;
		int randomx= rand.nextInt(14);
		int randomy= rand.nextInt(14);
		while(found==false) {
			if(this.arr[randomy][randomx]!="O"&&this.arr[randomy][randomx]!="8") {
				this.food.x=randomx;
				this.food.y=randomy;
				found = true;
			}
			else {
				randomx=rand.nextInt(14);
				randomy=rand.nextInt(14);
			}
		}
	}

	public void badfoodgene() {
		Random ran = new Random();
		boolean found = false;
		int randomx=ran.nextInt(14);
		int randomy=ran.nextInt(14);
		while(found==false) {
			if(this.arr[randomy][randomx]!="O"&&this.arr[randomy][randomx]!="o") {
				this.badfood.x=randomx;
				this.badfood.y=randomy;
				found = true;
			}
			else {
				randomx=ran.nextInt(14);
				randomy=ran.nextInt(14);
			}
		}

	}
	public void movingScissor(){
		if(reachedlast==false){
			if(this.scissors.x==14){
				reachedlast=true;
			} this.scissors.x =this.scissors.x+1;
		}

		if(reachedlast==true){
			if(this.scissors.x==0){
				reachedlast=false;
			}this.scissors.x =this.scissors.x-1;
		}
	}
}
