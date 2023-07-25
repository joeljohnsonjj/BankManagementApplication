import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JTextField;

public class MainBank extends MFrame implements ActionListener
{
    JTextField fnt,lnt;
    JPasswordField ptf;
    JTextArea ad;
    JButton sub,newAc;
    JLabel rlabel,noacc;
    OperationList ol; 
	Database db;
	String pin="";int balance=10000;
	createAcc ca;
    MainBank(String s)
    {
		try{
			this.db=new Database();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
        setTitle(s); 
        ImageIcon im=new ImageIcon("bank.png");
        setIconImage(im.getImage());
        setSize(500,500);
		setLayout(null);
        setResizable(false);
		getContentPane().setBackground(new Color(255,90,90));
        
        JPanel panel=new JPanel();
        panel.setBounds(20,20,400,400);
        panel.setBackground(new Color(255,240,240));
        panel.setLayout(null); 
        
        KeyAdapter KAD=new KeyAdapter()
        {
          public void keyPressed(KeyEvent ke) {
            int keyC=ke.getKeyCode();
            if(keyC==KeyEvent.VK_ENTER)
                    sub.doClick();
            if(keyC==KeyEvent.VK_UP)
            {
                fnt.requestFocus();
                if(ptf.isFocusOwner()==true)
                    lnt.requestFocus();
                else if(fnt.isFocusOwner()==true)
                    ptf.requestFocus();
            }
            else if(keyC==KeyEvent.VK_DOWN)
            {
                lnt.requestFocus();
                if(lnt.isFocusOwner()==true)
                    ptf.requestFocus();
                else if(ptf.isFocusOwner()==true)
                    fnt.requestFocus();
            }
          }
        };
        
        JLabel Fn=new JLabel("Name : ");
        Fn.setBounds(69,30,45,30);
        fnt=new JTextField();
        fnt.setBounds(130,30,210,30);
        fnt.addKeyListener(KAD);
        
		ImageIcon rib=new ImageIcon("ribbon.png");
		JLabel ribbon=new JLabel(); 
		ribbon.setIcon(rib);
		ribbon.setBounds(0,215,400,83);
		
        JLabel Ln=new JLabel("Acc. Number : ");
        Ln.setBounds(30,70,100,30);
        lnt=new JTextField();
        lnt.setBounds(130,70,100,30);
        lnt.addKeyListener(KAD);
        
        JLabel plabel=new JLabel("Password : ");
        plabel.setBounds(45,110,100,30);
        
        ptf=new JPasswordField();
        ptf.setEchoChar('*');
        ptf.setBounds(130,110,100,30);
        ptf.addKeyListener(KAD);
        
		JLabel noacc=new JLabel("Don't have an account already?");
        noacc.setBounds(45,170,200,30);
		
		newAc=new JButton("Sign Up!");
        newAc.setBounds(245,170,100,30);
        newAc.setFocusable(false);
		newAc.setBackground(new Color(255,240,240));
        newAc.addActionListener(this);
		
        sub=new JButton("Submit");
        sub.setBounds(130,320,100,30);
        sub.setFocusable(false);
        sub.addActionListener(this);
        
        rlabel=new JLabel("");
        rlabel.setBounds(100,360,335,30);
        
		panel.add(ribbon);
        panel.add(rlabel);
        panel.add(sub);
        panel.add(ptf);
        panel.add(plabel);
        panel.add(lnt);
        panel.add(fnt);
        panel.add(Ln);
        panel.add(Fn);
		panel.add(noacc);
		panel.add(newAc);
        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
		if(ae.getSource()==sub)
		{
		try
		{
			String name=fnt.getText().trim();
			rlabel.setText("");
			rlabel.setForeground(Color.RED);
			if(name.equals("")) 
				rlabel.setText("Enter your Name!");
			else if(lnt.getText().trim().equals("")) 
				rlabel.setText("Enter your Account Number!");
			else if(String.valueOf(ptf.getPassword()).equals("")) 
				rlabel.setText("Enter your Account Password!");
			else
			{
				int l=0;
				String acno= lnt.getText(),pass= String.valueOf(ptf.getPassword());
				rlabel.setForeground(Color.RED);
				if(db.getAccno(name).equals(acno))
				{
					if(db.getPassword(acno).equals(pass))
					{
						rlabel.setForeground(Color.BLACK);
						rlabel.setText(name+" , your registration has been completed!");l=1;
						pin=db.getPin(acno);
						balance=db.getBalance(acno);
					}
					else
						rlabel.setText( " Password Incorrect! ");
				}
				else
						rlabel.setText( "Incorrect Name/Account Number !");
				if(l==1)
				{
					ol=new OperationList(name,pin,balance);
					ol.setVisible(true);
					setVisible(false);
				}       
			}
		}
		catch(NumberFormatException nfe)
		{
			rlabel.setForeground(Color.RED);
			rlabel.setText("Enter valid inputs only please !");
		}
		}
		else
		{
			ca = new createAcc();
			setVisible(false);
		}
    }
	public static void main(String args[]) 
    {
        new MainBank("SouthIndian Bank");
    }
}