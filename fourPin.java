import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.Timer;
 
public class fourPin extends MFrame implements ActionListener
{ 
    JButton butt;
    boolean log=false;
    JPasswordField ptf;
    CashT ct=new CashT("","");String name,func;
    JLabel wpl=new JLabel();
	String pin="",fpin;
    int wp=0,k=0,balance=0;
    Timer timer;
    public fourPin(String name,String func,String pin,int balance)
    {
		this.pin=pin;this.balance=balance;
        setSize(350,420);
        setLayout(null);
        setTitle("Security");
        getContentPane().setBackground(new Color(240,100,100));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.name=name;this.func=func;
		ImageIcon im=new ImageIcon("bank.png");
        setIconImage(im.getImage());
        
        ptf = new JPasswordField(4);
        ptf.setEchoChar('X');
        ptf.setBounds(220,70,100,30);
		
		KeyAdapter KAD=new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if(ke.getKeyCode()==KeyEvent.VK_ENTER)
                    butt.doClick();
				else{
					if(String.valueOf(ptf.getPassword()).length()==3)
						fpin=String.valueOf(ptf.getPassword()); 
					else if(String.valueOf(ptf.getPassword()).length()==4)
					{
						if(ke.getKeyCode()!=KeyEvent.VK_BACK_SPACE)
							ptf.setText(fpin);
					}
				}
            }
        };
        ptf.addKeyListener(KAD);
        
		JTextArea wab=new JTextArea("Your PIN is confidential. Nobody should see\nthe pin as you are entering it. Make your PIN\n more secure by changing it frequently.");
		wab.setFont(new Font("normal", Font.BOLD, 12));
		wab.setFont(wab.getFont().deriveFont(12f));
		wab.setEditable(false);
		wab.setBounds(20,250,300,55);
		
        JLabel plab=new JLabel("Enter your four digit pin : ");
		plab.setBounds(40,65,250,30); 
        plab.setFont(new Font("Verdana", Font.BOLD, 12));
        
        butt=new JButton("Enter");
        butt.setBounds(120,140,100,30);
        butt.setFocusable(false);
        butt.addActionListener(this);
        
        wpl.setBounds(50,200,250,30);
        
        add(wpl);
		add(wab);
        add(plab);
        add(ptf);
        add(butt);
    }
    public void actionPerformed(ActionEvent ae)  
    {
        JLabel ep=new JLabel(String.valueOf(ptf.getPassword()));
        ep.setBounds(220,220,100,30);
        if(ae.getSource()==timer)
            System.exit(0);
        else if(String.valueOf(ptf.getPassword()).equals(pin))//getPassword() returns a char array for security reasons so 
        {                                                   //we need to convert it into a String before using the .equals() function.
            ct=new CashT(name,func);
            ct.setVisible(true);
            setVisible(false);
        }
        else if(wp<3)
        {
            wp++;  
            wpl.setText("Wrong PIN : "+(4-wp)+" tries left ");
        }
        else
        {
            wpl.setText("Too many invalid tries!Reconfirm login.");
            butt.setEnabled(false);
            timer=new Timer(2000,this);  
            timer.start();
        }
    }
}
