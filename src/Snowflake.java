import java.awt.Color;
import java.util.Random;


public class Snowflake extends Rectangle {
	private int size = 100;//default sideLength
	private double accY = 10;
	
	
	private Color leColor;
	private int numOfSides;
	private double angle=1;
	Polygon poly[];//holds the total number of polygons per snowflake
	Snowflake() {
		super(100,100,300,300);
		this.size = new Random().nextInt(190)+100;//same when declaring each
		setup(Color.white);
	}
	Snowflake(int x, int y) {
		super(x,y,5,5);
		setup(Color.white);
	}
	Snowflake(int x, int y, int size) {
		super(x,y,300,300);
		this.size = size;
		setup(Color.white);
	}
	Snowflake(int x, int y, int size, Color c) {
		super(x,y,300,300);
		this.size = size;
		setup(c);
	}
	/**
	 * basically replaces the constructor allowing for nice overload without excess code
	 * @param c
	 */
	private void setup(Color c) {
		accY = new Random().nextInt(10)+10;
		this.leColor = c;
		//this.setBackground(Color.BLACK);
		this.setBackground(new Color(0,0,0,0));
		this.numOfSides = new Random().nextInt(10)+4;
		poly = new Polygon[ 2*((new Random()).nextInt(4)+3) ];
		//adding all sides
		int thisIsCrap = poly.length;
		while(thisIsCrap-->0) {
			poly[thisIsCrap] = new Polygon(getWidth()/2,getHeight()/2,size/8,numOfSides,thisIsCrap,c);
			this.add(poly[thisIsCrap],0);
		}
	}
	/**
	 * rounds a double to an int
	 * @param num double to be rounded
	 * @return rounded integer of passed double
	 */
	int roundInt(double num) {
		num = (int)(num*10);
		if ( num-((int)(num/10)*10.0)>=5 ) { return (int)((num/10)+1); }
		return (int)(num/10);
	}
	/**
	 * places the snowflake above the top of the screen
	 */
	void reset() {
		Random rand = new Random();
		this.angle = rand.nextDouble();
		this.size = rand.nextInt(190)+100;//same when declaring each
		setPos(rand.nextInt(getParent().getWidth()-size*2)-size/2,0-size);
		setup ( new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)) );
	}
	/**
	 * rotates the snowflake and moves it according to accY
	 */
	void rotateNMove() {
		angle+=Math.PI/16;
		this.removeAll();//remove existing snowflake (so it doens't just become a clump
		setPos(getX(),roundInt(getY()+accY));
		//this.repaint();
		int thisIsCrap = poly.length;//a for loop would be better
		while(thisIsCrap-->0) {
			poly[thisIsCrap] = new Polygon(getWidth()/2,getHeight()/2,size/8,numOfSides,thisIsCrap+angle,leColor);
			this.add(poly[thisIsCrap],0);
		}
	}
	void act() {
		rotateNMove();
		if (getY()>getParent().getHeight()) {
			reset();
		}
	}
}
