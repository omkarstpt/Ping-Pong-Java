import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
    static boolean flag=false;
    BufferedImage back;
    BufferedImage home;
    private int time=10;
    Timer t = new Timer(time, this);
    private int paddlew= 150;
    private int score1=0;
    private int score2=0;
    private static int i=0;
    private static int j=0;
    // private static boolean s1=false;


    public PongGame() throws IOException {
        back = ImageIO.read(getClass().getResource("image2.jpg"));
        home = ImageIO.read(getClass().getResource("title.png"));
    }

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Ping Pong");
        PongGame game = new PongGame();
        window.add(game);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.addMouseMotionListener(game);
        window.addKeyListener(game);
        window.setFocusable(true);
        //Timer t = new Timer(10, game);
        //t.start();



    }
    public Dimension getPreferredSize(){
        return new Dimension(800,600);
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(flag) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);//Background

            g.drawImage(back, 0, 0, null);

            g.setColor(Color.BLUE);//Paddle
            g.fillRect(780, paddleY, 15, paddlew);
            g.drawString("Score : "+score2, 720, 30);
            g.setColor(new Color(186, 0, 9));
            g.fillRect(10, paddleY2, 15, paddlew);
            g.drawString("Score : "+score1, 30, 30);

            g.setColor(new Color(249, 178, 33));//Ball
            g.fillOval(ballX, ballY, 15, 15);
            if (ballX < 0) {
                g.drawString("Player 2 wins ", 380, 100);
                //score2=score2+1;
                g.drawString("Press Enter to continue", 355, 115);
                g.drawString("Press Esc to return to title screen ", 330, 130);
                if(j==0) score2=score2+1;
                j++;
            }
            if (ballX > 800) {
                g.drawString("Player 1 wins ", 380, 100);
                g.drawString("Press Enter to continue", 355, 115);
                g.drawString("Press Esc to return to title screen ", 330, 130);
                //s1=true;
                if(i==0) score1=score1+1;
                i++;

            }

        }else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 800, 600);
            g.drawImage(home,0,0,null);
        }


    }

    public void actionPerformed(ActionEvent e) {
        ballX = ballX + ballXSpeed;
        ballY = ballY + ballYSpeed;
        paddleY2 = paddleY2 + paddleY2Speed;
        if (ballY + 15 >= paddleY && ballY <= paddleY +paddlew && ballX +15 >= 780 && ballX <=780+15){
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
        if (paddleY>600-paddlew){
            paddleY=600-paddlew;
        }
        if (paddleY2<0){
            paddleY2=0;
        }
        if (paddleY2>600-paddlew){
            paddleY2=600-paddlew;
        }
        if (ballY + 15 >= paddleY2 && ballY <= paddleY2 +paddlew && ballX +15 >= 10 && ballX <=10+15){
            ballXSpeed = 5;
        }



        repaint();

    }


    public void mouseDragged(MouseEvent e) {
    }


    public void mouseMoved(MouseEvent e) {

        paddleY = e.getY() - 75;
    }


    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if(c== KeyEvent.VK_W){
            paddleY2Speed =-10;
        }
        if(c== KeyEvent.VK_S){
            paddleY2Speed=10;
        }
        if(c== KeyEvent.VK_ENTER){
            flag=true;
            i=0;
            j=0;

            if(flag) {
                t.start();
                ballX=400;
                ballY=150;
                repaint();

            }

        }
        if(c== KeyEvent.VK_ESCAPE) {
            flag=false;
            time=time-5;
        }
        if(c== KeyEvent.VK_UP) {
            paddlew = paddlew+50;
            if(paddlew>250)paddlew=250;
        }
        if(c== KeyEvent.VK_DOWN) {
            paddlew = paddlew-50;
            if(paddlew<50)paddlew=50;
        }
        if(c== KeyEvent.VK_R) {
            score1=score2=0;
        }
        if(c== KeyEvent.VK_O) {
            time=time+1;
        }
        if(c== KeyEvent.VK_P) {
            time=time-1;
        }
    }


    public void keyReleased(KeyEvent e) {

        paddleY2Speed=0;
    }
}
