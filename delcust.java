import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class delcust extends MFrame implements ActionListener{
	ImageIcon im=new ImageIcon("bank.png");
	int id;
	JTextField aid;
	JPasswordField pass,conpass,pin,conpin;
	JLabel aidd,done, p, cp, pi, res, cpi;
	JButton comp,butt;
	String str1, str2, str3, str4, str5;
	Database db;
	MFrame frame;
	delcust(String user) {
		setTitle("Account Deletion");
		comp=new JButton("Delete Account");
		comp.setFocusable(false);
		pass=new JPasswordField(10);
		conpass=new JPasswordField(10);
		pin=new JPasswordField(10);
		conpin=new JPasswordField(10);
		aid=new JTextField(10);
		aidd=new JLabel("Account No: ");
		p=new JLabel("Password: ");
		cp=new JLabel("Confirm Password: ");
		pi=new JLabel("Pin: ");
		cpi=new JLabel("Confirm Pin: ");
		res=new JLabel();
		
		KeyAdapter KAD=new KeyAdapter()
        {
          public void keyPressed(KeyEvent ke) {
            int keyC=ke.getKeyCode();
            if(keyC==KeyEvent.VK_ENTER)
                    comp.doClick();
            if(keyC==KeyEvent.VK_UP)
            {
                aid.requestFocus();
                if(conpass.isFocusOwner()==true)
                    pass.requestFocus();
                else if(pin.isFocusOwner()==true)
                    conpass.requestFocus();
				else if(conpin.isFocusOwner()==true)
                    pin.requestFocus();
				else if(aid.isFocusOwner()==true)
                    conpin.requestFocus();
            }
            else if(keyC==KeyEvent.VK_DOWN)
            {
                conpin.requestFocus();
                if(aid.isFocusOwner()==true)
                    pass.requestFocus();
               else if(pass.isFocusOwner()==true)
                    conpass.requestFocus();
				else if(conpass.isFocusOwner()==true)
                    pin.requestFocus();
				else if(conpin.isFocusOwner()==true)
                    aid.requestFocus();
            }
          }
        };
		
		pass.setEchoChar('*');
		conpass.setEchoChar('*');
		pin.setEchoChar('X');
		conpin.setEchoChar('X');
		
		setSize(400,400);
		setIconImage(im.getImage());
        setResizable(false);
		getContentPane().setBackground(new Color (240, 100, 100));
		
		add(aidd);
		add(aid);
		add(p);
		add(cp);
		add(pi);
		add(cpi);
		add(pass);
		add(conpass);
		add(pin);
		add(conpin);
		add(comp);
		add(res);
		
		setLayout(null);
		
		aidd.setBounds(20,20,150,50);
		p.setBounds(20,60,150,50);
		cp.setBounds(20,100,170,50);
		pi.setBounds(20,140,150,50);
		cpi.setBounds(20,180,150,50);
		
		aid.setBounds(195,30,150,30);aid.addKeyListener(KAD);
		pass.setBounds(195,70,150,30);pass.addKeyListener(KAD);
		conpass.setBounds(195,110,150,30);conpass.addKeyListener(KAD);
		pin.setBounds(195,150,150,30);pin.addKeyListener(KAD);
		conpin.setBounds(195,190,150,30);conpin.addKeyListener(KAD);
		
		comp.setBounds(120,240,150,50);
		res.setBounds(97,275,500,100);
		
		aidd.setFont(aidd.getFont().deriveFont(17f));
		p.setFont(p.getFont().deriveFont(17f));
		cp.setFont(cp.getFont().deriveFont(17f));
		pi.setFont(pi.getFont().deriveFont(17f));
		cpi.setFont(cpi.getFont().deriveFont(17f));
		
		setVisible(true);
		setDefaultCloseOperation(MFrame.DO_NOTHING_ON_CLOSE);
		
		comp.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if (source==comp) {
			str1=String.valueOf(pass.getPassword());
			str2=String.valueOf(conpass.getPassword());
			str3=String.valueOf(pin.getPassword());
			str4=String.valueOf(conpin.getPassword());
			str5=aid.getText();
			try{
			ellampoyi(str5); }
			catch(Exception e){
				System.out.println(e);}
		}
		else
		{
			frame.dispose();
			MainBank mb=new MainBank("SouthIndian Bank");
		}
	}
	
	public void ellampoyi(String accno) throws SQLException, ClassNotFoundException
		{
		int bal=0;
		String name="",check=str5.trim(),pa="",pn="";
		try{
			db=new Database();
			name=db.getName(accno);
			pa=db.getPassword(accno);
			pn=db.getPin(accno);
			bal=db.getBalance(accno);
		}	 
		catch(Exception e)
		{}
		if(accno.equals(check)) {
			if(str1.equals(str2) && str1.equals(pa))
			{
				if(str3.equals(str4) && str3.equals(pn))
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jobank","root","1234");
					Statement stm=con.createStatement();
					int confirmed = JOptionPane.showConfirmDialog(null,name+", are you sure you want to delete your account in South Indian Bank?" ,
																"Account Deletion Confirmation Window", JOptionPane.YES_NO_OPTION);
					if(confirmed==JOptionPane.YES_OPTION){
						String fry="delete from user where accno='"+accno+"'";
						stm.executeUpdate(fry);
						successpage();
						setVisible(false);
					}
					stm.close();
					con.close();
					
				}
				else
					res.setText("Pin Incorrect/Did not match");
			}
			else
				res.setText("Password Incorrect/Did not match ");
			}
		else
			res.setText("Acc No. does not exist in Database");
	}
	
		
	public void successpage() {
	frame=new MFrame();
    ImageIcon image=new ImageIcon("SIB.png");
    JLabel ic=new JLabel(); 
    ic.setIcon(image);
    ic.setOpaque(true);ic.setBackground(Color.white);
    ic.setBounds(130,25,141,90);
	frame.setTitle("Successful Deletion");
	JTextArea text;
	text=new JTextArea("\n    Your Account has succesfully been\n    scheduled for deletion, and a support\n    request has been sent to the IT \n    Department. Visit nearest SIB Branch for \n    withdrawal of existing savings deposits.\n    Thank You for being a part of the SIB Family.");
	frame.setSize(400,400);
	frame.setIconImage(im.getImage());
	frame.setResizable(false);
	frame.getContentPane().setBackground(new Color (240, 100, 100));
	frame.add(text);
	frame.add(ic);
	frame.setLayout(null);
	text.setBounds(20,150,347,155);
	text.setFont(text.getFont().deriveFont(15f));
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	text.setEditable(false);
	
	butt=new JButton("HOME");
	butt.setBounds(270,320,100,30);
	butt.setFocusable(false);
	butt.addActionListener(this);
	
	frame.add(butt);
	frame.setVisible(true);  
	}
	

	public static void main(String st[]) {//For testing purposes only
		delcust lol=new delcust("Joel"); }
}