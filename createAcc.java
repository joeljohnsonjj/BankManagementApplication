import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.sql.*;
import java.awt.Font;

class createAcc extends MFrame implements ActionListener
{
	ImageIcon im=new ImageIcon("bank.png");
	JButton submit,butt;
	JLabel p,nam,con,err,pi,cp;
	MFrame frame;
	JTextField name;
	JPasswordField pass,confirm,pin,cpass;
	createAcc()
	{
		super("Creating an account");
		nam=new JLabel("Enter your name");
		p=new JLabel("Enter a secure password");
		cp=new JLabel("Confirm your pin");
		cpass=new JPasswordField();
		cpass.setEchoChar('X');
		err=new JLabel();
		name=new JTextField();
		
		ret=1;
		
		pass=new JPasswordField();
		pass.setEchoChar('*');
		con=new JLabel("Confirm your password");
		confirm=new JPasswordField();
		confirm.setEchoChar('*');
		pi=new JLabel("Enter pin");
		pin=new JPasswordField();
		pin.setEchoChar('X');
		submit=new JButton("Submit details");
		getContentPane().setBackground(new Color (255,90,90));
		KeyAdapter KAD=new KeyAdapter()
        	{
            	public void keyPressed(KeyEvent ke) {
            	int keyC=ke.getKeyCode();
            	if(keyC==KeyEvent.VK_ENTER)
                    submit.doClick();
            	if(keyC==KeyEvent.VK_UP)
            	{
			if(name.isFocusOwner()==true)
			 confirm.requestFocus();
                	else if(pin.isFocusOwner()==true)
                    name.requestFocus();
               	else if(cpass.isFocusOwner()==true)
                    pin.requestFocus();
		      else if(pass.isFocusOwner()==true)
                    cpass.requestFocus();
		      else if(confirm.isFocusOwner()==true)
                    pass.requestFocus();
            }
                  else if(keyC==KeyEvent.VK_DOWN)
                  {
                  if(name.isFocusOwner()==true)
			 pin.requestFocus();
                  else if(pin.isFocusOwner()==true)
                    cpass.requestFocus();
                  else if(cpass.isFocusOwner()==true)
                    pass.requestFocus();
		      else if(pass.isFocusOwner()==true)
                    confirm.requestFocus();
		      else if(confirm.isFocusOwner()==true)
                    name.requestFocus();
            }
          }
        };
		name.addKeyListener(KAD);
		pin.addKeyListener(KAD);
		confirm.addKeyListener(KAD);
		pass.addKeyListener(KAD);
		cpass.addKeyListener(KAD);
		add(p);add(name);
		add(cp);add(cpass);
		add(pass);add(nam);
		add(con);add(confirm);
		add(submit);add(err);
		add(pi);add(pin);
		setSize(500,500);
		setLayout(null);
		setResizable(false);
		nam.setBounds(20,30,150,30);
		name.setBounds(170,30,150,30);
		pi.setBounds(20,80,150,30);
		pin.setBounds(170,80,150,30);
		cp.setBounds(20,130,150,30);
		cpass.setBounds(170,130,150,30);
		p.setBounds(20,180,150,30);
		pass.setBounds(170,180,150,30);
		con.setBounds(20,230,150,30);
		confirm.setBounds(170,230,150,30);
		submit.addActionListener(this);
		
		submit.setBounds(150,300,150,40);
		err.setBounds(150,370,250,20);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(im.getImage());
		setVisible(true);
	}
	void func(String name,String accn)
	{
		frame=new MFrame("Success Page");
        ImageIcon image=new ImageIcon("SIB.png");
        JPanel panel=new JPanel();
        panel.setBackground(new Color (255, 200, 200));
        frame.getContentPane().setBackground(new Color (225, 50, 50));
        JLabel j=new JLabel(name+" ,your account has been successfully created!");
        JLabel ic=new JLabel(); 
        ic.setIcon(image);
        ic.setOpaque(true);ic.setBackground(Color.white);
        JLabel k=new JLabel("Your account number is : "+accn);
    
		butt=new JButton("HOME");
		butt.setBounds(370,20,100,30);
		butt.setFocusable(false);
		butt.addActionListener(this);
	
        JLabel l=new JLabel("Your current account balance is : 0");
        JTextArea m=new JTextArea();
        m.setText("South Indian Bank aims to provide superior and safe customer \nservice experience to all its customers. Prevention mechanisms are in \nplace to ensure safety. Do not respond to phishing E-mails\nclaiming to be from Reserve Bank of India. Never share your\naccount details such as account number, login ID, password, PIN,\nOTP, ATM / Debit card / credit card details with anyone, not even\n with bank officials, however genuine they might sound.");
        m.setEditable(false);
		m.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel.add(j);panel.add(k);
        panel.add(l);
        
        frame.add(m);
        frame.add(panel);
        frame.add(ic);
		frame.add(butt);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
        
        frame.setSize(500,500);
        frame.setLayout(null);
		frame.setIconImage(im.getImage());
		ic.setBounds(180,25,141,90);
        panel.setBounds(20,125,440,120);
        panel.setLayout(null);
        m.setBounds(20,280,450,140);
        j.setBounds(50,10,400,50);
        k.setBounds(50,35,400,50);
        l.setBounds(50,60,400,50);
        frame.setVisible(true);
        
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==butt)
		{
			frame.dispose();
			MainBank mb=new MainBank("SouthIndian Bank");
		}
		else
	  {
		String ano="",passwordd="",pinn="";
	    int l=0;
		String s1=name.getText().trim();
		String s2=String.valueOf(cpass.getPassword()).trim();
		String s3=String.valueOf(pass.getPassword()).trim();
		String s4=String.valueOf(confirm.getPassword()).trim();
		String s5=String.valueOf(pin.getPassword()).trim();
		try{			
			if(s1.equals(""))
				err.setText("Please fill out name");
			else if(s5.equals(""))
				err.setText("Please enter a pin");
			else if(s5.length()!=4)
				err.setText("Pin should be 4 digits");
			else if(s2.equals(""))
				err.setText("Please confirm your pin");
			else if(s3.equals(""))
				err.setText("Please enter a password");
			else if((s5.equals(s2))==false)
				err.setText("Pin is not matching!");
			else if(s3.length()!=5)
				err.setText("Password should be 5 digits");
			else if(s4.equals(""))
				err.setText("Please confirm your password");
			else if((s4.equals(s3))==false)
				err.setText("Password is not matching!");
			else{			
				passwordd= s3;
				pinn=s5;
				l=1;
			}
		}
		catch(NumberFormatException nfe)
		{
			err.setText("Please enter digits only for account password and pin");
		}
		if(l==1)
		{
		 
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobank","root","1234");
		Statement stm=con1.createStatement();
	    Database db=new Database();
		int r=Integer.parseInt(db.getMax())+1;
		if(r>99)
			ano=String.valueOf(r);
		else if(r>9)
			ano="0"+String.valueOf(r);
		else
			ano="00"+String.valueOf(r);
		stm.executeUpdate("insert into user(accno,name,accpass,accbalance,accpin)values('"+ano+"','"+s1+"','"+s3+"' ,"+"0,'"+s5+"')");
		stm.close();
		con1.close();
		func(s1,ano);
		setVisible(false);
		}
		catch(SQLException s)
		{
			System.out.println(s);
		}
		catch(ClassNotFoundException c)
		{
			System.out.println(c);
		}
		}
	  }
	}
	public static void main(String st[])//For testing purposes only
	{
		createAcc a=new createAcc();
		

	}
}