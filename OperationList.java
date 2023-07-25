import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JTextField;

public class OperationList extends MFrame implements ActionListener 
{
    JLabel rlabel;
    JButton rb1,rb2,rb3,rb4,rb5;
	String pin,e;int balance;
    fourPin fp;
    OperationList(String s,String pin,int balance)
    {
		this.pin=pin;this.balance=balance; 
        setTitle("Account Owner - "+s);
        setSize(500,500);e=s;
        ImageIcon im=new ImageIcon("bank.png");
        setIconImage(im.getImage());
        setSize(500,500);
        setResizable(false);
        Color mcol=new Color(250,80,70);
		Color bcol=new Color(250,150,100);
        
        JPanel panel=new JPanel();
        panel.setBounds(0,0,500,500);
        panel.setBackground(mcol);
        panel.setLayout(null);
        
        rlabel=new JLabel("");
        rlabel.setBounds(100,360,335,30);
        
        JLabel glabel=new JLabel("Welcome to South Indian Bank! ",JLabel.CENTER);
        glabel.setBounds(145,65,210,30);
	    glabel.setOpaque(true);
	    glabel.setBackground (Color.WHITE);
        
        rb1=new JButton("Deposit Cash");
        rb1.setActionCommand("deposit");
        rb1.setBounds(10,130,179,50);
        rb1.setBackground(bcol);
        rb1.setFocusable(false);
	    rb1.addActionListener(this);
        
        rb2=new JButton("Withdraw Cash");
        rb2.setActionCommand("withdraw");
		rb2.setBounds(10,210,179,50);
        rb2.setBackground(bcol);
        rb2.setFocusable(false);
		rb2.addActionListener(this);
        
        rb3=new JButton("Check balance");
        rb3.setActionCommand("check");
        rb3.setBounds(298,130,179,50);
        rb3.setBackground(bcol);
        rb3.setFocusable(false);
        rb3.setSelected(true);
		rb3.addActionListener(this);
		
		rb4=new JButton("Delete Account");
        rb4.setActionCommand("delete");
        rb4.setBounds(298,210,179,50);
        rb4.setBackground(bcol);
        rb4.setFocusable(false);
		rb4.addActionListener(this);
		
		rb5=new JButton("Change PIN");
        rb5.setActionCommand("change");
        rb5.setBounds(160,290,179,50);
        rb5.setBackground(bcol);
        rb5.setFocusable(false);
		rb5.addActionListener(this);
        
        panel.add(rb3);
        panel.add(rb2);
        panel.add(rb1);
		panel.add(rb4);
		panel.add(rb5);
        panel.add(rlabel);
        panel.add(glabel);
        add(panel);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent ae)
    {
				JButton button=(JButton)ae.getSource();
				if(button.getActionCommand().equals("check"))
					new checkBal(e,balance,pin);
				else if(button.getActionCommand().equals("delete"))
					new delcust(e);
				else if(button.getActionCommand().equals("change"))
					new nPin(e);
				else{
					fp=new fourPin(e,button.getActionCommand(),pin,balance);
					fp.setVisible(true);
				}
				setVisible(false);               
    }  
	public static void main(String args[])//For Testing purposes
	{
		try{
			Database dataB=new Database();
			String acno=dataB.getMax();
			if(acno.equals("000"))
				System.out.println("Table doesn't have any entries.");
			else{
				OperationList test=new OperationList(dataB.getName(acno),dataB.getPin(acno),dataB.getBalance(acno));
				test.setVisible(true);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
