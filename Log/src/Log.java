import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.*;
import java.sql.*;

public class Log extends JFrame {

    public static void main(String[] args) throws IOException {
        Log frameTabel = new Log();

    }

    JButton blogin = new JButton("Login");
    JPanel panel = new JPanel();
    JTextField txuser = new JTextField(15);
    JPasswordField pass = new JPasswordField(15);
    BufferedImage back;
    JLabel labelu = new JLabel("Username");


    Log() throws IOException{
        super("Login Autentification");
        setSize(800,600);
        setLocationRelativeTo(null);
        panel.setLayout (null);
        try {
            back = ImageIO.read(getClass().getResource("log.jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }

        labelu.setBounds(265,200,150,20);
        txuser.setBounds(325,200,150,20);
        pass.setBounds(325,250,150,20);
        blogin.setBounds(360,300,80,20);
        JLabel picLabel = new JLabel(new ImageIcon(back));
        picLabel.setBounds(0,0,800,800);
        panel.add(picLabel);
        panel.add(labelu);



        panel.add(blogin);
        panel.add(txuser);
        panel.add(pass);
        //panel.setBackground(Color.BLACK);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        actionlogin();

    }


    public void actionlogin(){
        blogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String puname = txuser.getText();
                String ppaswd = pass.getText();
                boolean st = false;
                try{
                    Class.forName("com.mysql.jdbc.Driver");

                    //creating connection with the database
                    Connection  con=DriverManager.getConnection
                            ("jdbc:mysql://localhost:3306/studentdb?useSSL=false","myuser","1234");
                    PreparedStatement ps =con.prepareStatement
                            ("select * from Student where name=? and pass=?");
                    ps.setString(1, puname);
                    ps.setString(2, ppaswd);
                    ResultSet rs =ps.executeQuery();
                    st = rs.next();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                if(st) {
                    try
                    {
                       PongGame.main(null);
                    }
                    catch(IOException e)
                    {
                        System.err.println("Error");
                        e.printStackTrace();
                    }
                } else {

                    JOptionPane.showMessageDialog(null,"Wrong Password / Username");
                    txuser.setText("");
                    pass.setText("");
                    txuser.requestFocus();
                }

            }
        });
    }
}