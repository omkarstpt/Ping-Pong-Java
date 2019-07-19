import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by HP on 14-Nov-15.
 */
public class PongGame extends JComponent implements ActionListener,MouseMotionListener, KeyListener {
    private int ballX=400;
    private int ballY=150;
    private int paddleY=0;
    private int ballYSpeed = 5;
    private int ballXSpeed = 5;
    private int paddleY2 = 0;
    private int paddleY2Speed=0;

    public static void main(String[] args){
       JFrame window = new JFrame("Ping Pong");
       PongGame game = new PongGame();
       window.add(game);
       window.pack();
       window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       window.setLocationRelativeTo(null);
       window.setVisible(true);


       Timer t = new Timer(10, game);
       t.start();

       window.addMouseMotionListener(game);
       window.addKeyListener(game);
       window.setFocusable(true);

   }
    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,800,600);//Backgroung

        g.setColor(Color.BLUE);//Paddle
        g.fillRect(780,paddleY,15,150);
        g.setColor(new Color(186,0, 9));
        g.fillRect(10,paddleY2,15,150);

        g.setColor(new Color(255, 252, 249));//Ball
        g.fillOval(ballX,ballY,15,15);
        if(ballX <0){
            g.drawString("Player 2 wins ", 380,100);
        }
        if(ballX >800){
            g.drawString("Player 1 wins ", 380,100);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX = ballX + ballXSpeed;
        ballY = ballY + ballYSpeed;
        paddleY2 = paddleY2 + paddleY2Speed;
        if (ballY + 15 >= paddleY && ballY <= paddleY +150 && ballX +15 >= 780 && ballX <=780+15){
            ballXSpeed = -5;
        }
        if (ballY <= 0){
            ballYSpeed = 5;
        }
        if(ballY>=600-15){
            ballYSpeed = -5;
        }
        if (paddleY<0){
            paddleY=0;
        }
        if (paddleY>600-150){
            paddleY=600-150;
        }
        if (paddleY2<0){
            paddleY2=0;
        }
        if (paddleY2>600-150){
            paddleY2=600-150;
        }
        if (ballY + 15 >= paddleY2 && ballY <= paddleY2 +150 && ballX +15 >= 10 && ballX <=10+15){
            ballXSpeed = 5;
        }


        repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddleY = e.getY() - 75;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if(c== KeyEvent.VK_W){
            paddleY2Speed =-10;
        }
        if(c== KeyEvent.VK_S){
            paddleY2Speed=10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddleY2Speed=0;
    }
}
