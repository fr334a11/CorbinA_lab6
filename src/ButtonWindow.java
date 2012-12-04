import javax.swing.*;
import java.awt.Toolkit;//for screen size
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/** 
 * Natalie Watts
 * Neumont University
 * November 2012
 */
public abstract class ButtonWindow  {  

    public abstract void act();

    protected class ButtonWindowFrame extends JFrame implements ActionListener{
        private JButton actButton, animateButton;
        private Container pane;
        private boolean isAnimating = false;
        protected int fps = 30;
        private Timer t = new Timer();

        public void setFps(int fps){ this.fps = fps; }
        public int getFps()        { return fps;     }

        public ButtonWindowFrame(String s) {
            super(s);
            int width  = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getWidth ());
            int height = (int)((Toolkit.getDefaultToolkit().getScreenSize()).getHeight());
            setBounds(0, 50, width , height-100);
            pane = getContentPane();
            pane.setLayout(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void setWinLocation(int x, int y)
        {
            this.setLocation(x,y);
        }

        public void setWinSize(int w, int h)
        {
            this.setSize(w,h);
        }

        public void addActButton(int x, int y, int w, int h,String name){
            actButton = new JButton(name);
            actButton.setBounds(x, y, w, h);
            actButton.addActionListener(this);
            pane.add(actButton,0);
            pane.repaint();
        }

        public void addAnimateButton(int x, int y, int w, int h,String name){
            animateButton = new JButton(name);
            animateButton.setBounds(x, y, w, h);
            animateButton.addActionListener(this);
            pane.add(animateButton,0);
            pane.repaint();
        }

        public void animate(){
            if (!isAnimating){
                isAnimating = true;
                t.schedule(new MyTimerTask(), 0, 1000/fps);
                animateButton.setText("Stop");
            }
            else{
                isAnimating = false;
                t.cancel();
                t = new Timer();
                animateButton.setText("Start");
            }
        }

        public void stop(){
            t.cancel();
            t = new Timer();
        }

        public class MyTimerTask extends TimerTask{
            @Override
            public void run() {
                act();
            }
        }
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == actButton)
                act();
            else if (e.getSource() == animateButton)
                animate();
        }
    }
}