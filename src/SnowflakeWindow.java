import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.awt.Toolkit;//window and rectangle for snowflakes based off screen res


public class SnowflakeWindow extends ButtonWindow {
	public static final int SCREEN_WIDTH  = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getWidth ());
	public static final int SCREEN_HEIGHT = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getHeight());
	public static final int world_x = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getWidth ())-50;
    public static final int world_y = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getHeight())-100;
    //public static final int world_x = 1850;
    //public static final int world_y = 950;
    private ButtonWindowFrame win;
    private Snowflake[] letItSnow;
	private Rectangle theSky;
	//private Snowflake mySnowFlake;
	/**
	 * defaults to 10 snowflakes
	 */
	SnowflakeWindow() {
		setup(10);
	}
	/**
	 * setup for given # of snowflakes
	 * @param numOfSnowflakes
	 */
	SnowflakeWindow(int numOfSnowflakes) {
		setup(numOfSnowflakes);
	}
	/**
	 * setup to easilly allow overloaded constructor
	 * creates window based off world_x and world_y
	 * @param numOfSnowflakes the number of snowflakes to be created
	 */
	private void setup(int numOfSnowflakes) {
		letItSnow = new Snowflake[numOfSnowflakes];//setting lenght of array based off sent snowflakes
		Random rand = new Random();
		win = new ButtonWindowFrame("Let It Snow");
		theSky = new Rectangle(0,0,world_x,world_y);
		theSky.setBackground(Color.BLACK);
		win.add(theSky,0);
		int w;
		for (int i=0;i<letItSnow.length;i++) {
			w = rand.nextInt(190)+100;
			letItSnow[i] = new Snowflake(rand.nextInt(world_x-w),rand.nextInt(world_y-w),w);
            theSky.add(letItSnow[i],0);
        }
		//win.setVisible(true);
		//adding buttons
		win.addAnimateButton(0,0,75,25,"Start");//creating button
        win.addActButton(100,0,100,25,"One Step");
        
        win.repaint();
	}
	/**
	 * loops through and calls the act method of each sprite
	 */
	public void act() {
		for(int i=0;i<letItSnow.length;i++) {
			letItSnow[i].act();
		}
		win.repaint();
	}
	/**
	 * will prompt given question to the user and return the user's integer responce
	 * @param question what to be prompted to the user
	 * @return user inputed int
	 */
	private static int promptInt(String question) {
		boolean valid = false;
		int ret=0;;
		do {
			try {
				System.out.println(question+"(int): ");
				ret = Integer.parseInt((new Scanner(System.in)).nextLine());
				valid = true;
			} catch (NumberFormatException e) {
				System.out.println("--Please enter a whole integer--");
				valid = false;
			}
		} while (!valid);
		return ret;
	}
	/**
	 * 
	 * @param question
	 * @param defVal if the user enters an invalid value this will be what is returned
	 * @return user inputed int
	 */
	private static int promptInt(String question, int defVal) {
		try {
			System.out.println(question+"(int): ");
			return Integer.parseInt((new Scanner(System.in)).nextLine());
		} catch (NumberFormatException e) {
			return defVal;
		}
	}
	
	public static void main(String[] args) {
		new SnowflakeWindow( promptInt("How many snowflakes would you like to see",10) );
	}
}