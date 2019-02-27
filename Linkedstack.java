import java.util.Random;
class Linkedstack {
	Node head;	Node tail;
	Node food; Node badfood;	
	//int counting;
	String[][] arr;
	
	Linkedstack() { //constructor no args
		Random rand = new Random();
		//this.counting=0;
		this.arr=new String[15][15];
		Node firstnode= new Node();
		firstnode.x = 0;	firstnode.y = 0;
		Node food1 = new Node();
		food1.x = rand.nextInt(14) + 1;
		food1.y = rand.nextInt(14) + 1;
		this.food = food1;
		head=tail=firstnode;
	}


	public void add(){
		//Node temp;
		Node node = new Node();
		this.tail.next=node;
		this.tail=node;


	}
	
	public void Coordinatesmove() {

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
	}

	public void moveDirection(char move) {
		if(this.head.x==food.x&&this.head.y==food.y) {
			add();
			FoodGenerator();
		}

		Coordinatesmove();
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
	
	public void update() {
		int i,j;
		Node temp;
		
		for(i=0;i<15;i++){
			for(j=0;j<15;j++){
				if(i==0 || i==14) {
					this.arr[i][j] = ".";
				}
				else if(j==0 || j==14) {
					this.arr[i][j] = ".";
				}
				else this.arr[i][j]=" ";
			}
		} arr[this.food.y][this.food.x]="a";

		for(temp=this.head;temp!=null;temp=temp.next) {
			this.arr[temp.y][temp.x]="S"; 
		}
		
		for(i=0; i<15;i++) {
			for(j=0; j<15;j++){
				System.out.print(this.arr[i][j]+ " ");
			}
			System.out.println();
		}

	}

	public void FoodGenerator() {
		Random rand = new Random();
		boolean found=false;
		int randomx= rand.nextInt(14) + 1;
		int randomy= rand.nextInt(14) + 1;
		while(found==false) {
			if(this.arr[randomy][randomx]!="S") { 
				this.food.x=randomx;
				this.food.y=randomy;
				found = true;
			}
			else {
				randomx=rand.nextInt(14) + 1;
				randomy=rand.nextInt(14) + 1;
			}
		}
	}

	public void badfood() {
		
	}

}
