import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
 
public class checkBal extends MFrame implements ActionListener
{
	JButton butt;
	ImageIcon sicon; 
	String name,pin;int balance;
    checkBal(String s ,int balance,String pin)
     {
		 name=s;this.balance=balance;this.pin=pin;
		 sicon=new ImageIcon("bank.png");
		 setIconImage(sicon.getImage());
         setTitle("Account Owner - "+s);
         setSize(500,500);
         setResizable(false);
         setLayout(null);
         setDefaultCloseOperation(MFrame.DO_NOTHING_ON_CLOSE);
		 
         Color backCol=new Color(255,130,120);
         Color LabCol=new Color(250,230,220);
		 getContentPane().setBackground(backCol);		 
		 
		 JLabel balShow,bankIm,title = new JLabel("Account Balance", JLabel.CENTER);
		 
		 balShow=new JLabel(s+" , your current balance is : Rs."+balance, JLabel.CENTER);
		 balShow.setOpaque(true);
		 balShow.setBackground(LabCol);
         balShow.setBounds(60,160,380,30);
		 
		 butt=new JButton("<- Back");
		 butt.setBounds(370,400,100,30);
		 butt.setFocusable(false);
		 butt.addActionListener(this);

         title.setFont(new Font("Serif", Font.BOLD, 20));
         title.setBounds(130,30,250,30);
 
		 add(butt);
         add(balShow);
         add(title);
		 setVisible(true);
     }
	 public void actionPerformed(ActionEvent ae)
	 {
		OperationList olp=new OperationList(name,pin,balance);
		olp.setVisible(true);
		dispose();
	 }
	 /**public static void main(String args[])//For testing purposes
	 {
		 new checkBal("Joel Joseph Justin",1234567,"007");
	 }*/
}