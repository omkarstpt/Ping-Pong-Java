import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class PaintApplication
{


    public static void main(String[] args)
    {
        new PaintApplication();
        //Declarations
        JFrame frame = new JFrame("Paint");
        //Container contains = f.getContentPane();
        JPanel panel = new JPanel();
        final DrawSpace drawHere = new DrawSpace();


        //Creating the Icons of Buttons
        Icon redIcon = new ImageIcon("red.png");
        Icon blueIcon = new ImageIcon("blue.png");
        Icon greenIcon = new ImageIcon("green.png");
        Icon yellowIcon = new ImageIcon("yellow.jpg");
        Icon blackIcon = new ImageIcon("black.png");
        Icon whiteIcon = new ImageIcon("white.jpg");
        Icon paintBrushIcon = new ImageIcon("paintBrush.jpg");
        Icon rectangleIcon = new ImageIcon("rectangle.png");
        Icon ellipseIcon = new ImageIcon("ellipse.png");
        Icon lineIcon = new ImageIcon("line.png");


        //Buttons for all the colors(red, blue, green, yellow, black)
        JButton redButton = new JButton(redIcon);
        redButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                drawHere.red();
            }
        });

        JButton blueButton = new JButton(blueIcon);
        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawHere.blue();
            }
        });

        JButton greenButton = new JButton(greenIcon);
        greenButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                drawHere.green();
            }
        });

        JButton yellowButton = new JButton(yellowIcon);
        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawHere.yellow();
            }
        });

        JButton blackButton = new JButton(blackIcon);
        blackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawHere.black();
            }
        });

        JButton whiteButton = new JButton(whiteIcon);
        whiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawHere.white();
            }
        });

        JButton paintBrushButton = new JButton(paintBrushIcon);
        paintBrushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawHere.paintBrush();
            }
        });

        JButton clearButton = new JButton("Erase");
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                drawHere.clear();
            }
        });

        JButton lineButton = new JButton(lineIcon);
        lineButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                drawHere.line();
            }
        });


        JButton rectangleButton = new JButton(rectangleIcon);
        rectangleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                drawHere.rectangle();
            }
        });

        JButton ellipseButton = new JButton(ellipseIcon);
        ellipseButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                drawHere.ellipse();
            }
        });

        //Setting size of the color buttons
        redButton.setPreferredSize(new Dimension(30,30));
        blueButton.setPreferredSize(new Dimension(30,30));
        greenButton.setPreferredSize(new Dimension(30,30));
        yellowButton.setPreferredSize(new Dimension(30,30));
        blackButton.setPreferredSize(new Dimension(30,30));
        //clearButton.setPreferredSize(new Dimension(30,20));
        whiteButton.setPreferredSize(new Dimension(30,30));
        paintBrushButton.setPreferredSize(new Dimension(30, 30));
        lineButton.setPreferredSize(new Dimension(40,30));
        rectangleButton.setPreferredSize(new Dimension(40,30));
        ellipseButton.setPreferredSize(new Dimension(40,30));

        //Adding buttons to the panel
        panel.add(redButton);
        panel.add(blueButton);
        panel.add(greenButton);
        panel.add(yellowButton);
        panel.add(blackButton);
        panel.add(whiteButton);
        panel.add(paintBrushButton);
        panel.add(lineButton);
        panel.add(rectangleButton);
        panel.add(ellipseButton);
        panel.add(clearButton);

        panel.setBackground(Color.LIGHT_GRAY);

        //frame.setLocationRelativeTo(null);

        //Set properties of frame
        frame.setLayout(new BorderLayout());
        frame.setSize(1000,600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setPreferredSize(new Dimension(60,600));
        //panel.setLayout(new BorderLayout());
        //panel.add(redIcon,);

        frame.add(drawHere,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.EAST);



        frame.setVisible(true);
    }
}

class DrawSpace extends JComponent
{
    Image image;
    Graphics2D graphics;
    //int X,Y,oldX,oldY,olderX = 0,olderY = 0;
    int X, Y, newX, newY, oldX = 0, oldY = 0, iniX, iniY, lastX, lastY;
    private boolean paint = false;

    public DrawSpace()
    {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                //oldX = newX;
                //oldY = newY;
                newX = e.getX();
                newY = e.getY();
                iniX = newX;
                iniY = newY;
                oldX = newX;
                oldY = newY;

                /*oldX = newX;
                oldY = newY;
                newX = e.getX();
                newY = e.getY();*/
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(paint == false){
                    X = e.getX();
                    Y = e.getY();
                    if(graphics != null)
                        graphics.drawLine(newX, newY, X, Y);
                    repaint();
                    newX = X;
                    newY = Y;
                }else{
                    X = e.getX();
                    Y = e.getY();
                    if(graphics != null){
                        graphics.fillOval(X, Y, 7, 7);
                    }
                    repaint();
                }

            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });


    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(image == null)
        {
            image = createImage(getSize().width,getSize().height);
            graphics = (Graphics2D)image.getGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }


    public void red()
    {
        graphics.setPaint(Color.red);
        repaint();
    }

    public void blue()
    {
        graphics.setPaint(Color.blue);
        repaint();
    }

    public void green()
    {
        graphics.setPaint(Color.green);
        repaint();
    }

    public void yellow()
    {
        graphics.setPaint(Color.yellow);
        repaint();
    }

    public void black()
    {
        graphics.setPaint(Color.black);
        repaint();
    }

    public void white()
    {
        graphics.setPaint(Color.white);
        repaint();
    }

    public void paintBrush()
    {
        if(paint == false){
            paint = true;
        }else{
            paint = false;
        }
    }

    public void clear()
    {
        graphics.setPaint(Color.white);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);
        repaint();
    }

    public void line()
    {
        graphics.drawLine(iniX, iniY, lastX, lastY);
        repaint();
    }

    public void rectangle()
    {
        //int width = Math.sqrt(ol                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  dX*oldX + old);
        graphics.drawRect(iniX, iniY, Math.abs(lastX-iniX), Math.abs(lastY-iniY));
        //graphics.
        repaint();
    }

    public void ellipse()
    {  graphics.drawOval(iniX, iniY, Math.abs(lastX-iniX), Math.abs(lastY-iniY));
        repaint();
    }

}