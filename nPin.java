import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import javax.swing.JTextField;

public class nPin extends MFrame implements ActionListener
{ 
    JPasswordField ptf,fnt,lnt,nptf;
    JTextArea ad;
    JButton sub,butt ;
    JLabel rlabel; 
	Database db;
	String pin="",name;int balance=10000;
    nPin(String s)
    {
		name=s;
        setTitle("Account Owner - "+s); 
        ImageIcon im=new ImageIcon("bank.png");
        setIconImage(im.getImage());
        setSize(500,500);
		setLayout(null);
        setResizable(false);
		getContentPane().setBackground(new Color(117, 0, 0));
        
        JPanel panel=new JPanel();
        panel.setBounds(40,30,400,400);
        panel.setBackground(new Color(255,70,70));
        panel.setLayout(null);

	  butt=new JButton("HOME");
	  butt.setBounds(370,415,100,25);
	  butt.setFocusable(false);
	  //butt.setVisible(false);
	  //butt.addActionListener(this); 
        
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
                else if(nptf.isFocusOwner()==true)
                    ptf.requestFocus();
				else if(fnt.isFocusOwner()==true)
                    nptf.requestFocus();
            }
            else if(keyC==KeyEvent.VK_DOWN)
            {
                nptf.requestFocus();
                if(lnt.isFocusOwner()==true)
                    ptf.requestFocus();
                else if(fnt.isFocusOwner()==true)
                    lnt.requestFocus();
				else if(nptf.isFocusOwner()==true)
                    fnt.requestFocus();
            }
          }
        };
        
        JLabel Fn=new JLabel("Old PIN : ");
        Fn.setBounds(30,50,150,30);
        fnt=new JPasswordField();
		fnt.setEchoChar('X');
        fnt.setBounds(140,50,100,30);
        fnt.addKeyListener(KAD);
		
        JLabel Ln=new JLabel("Confirm old PIN : ");
        Ln.setBounds(30,90,150,30);
        lnt=new JPasswordField();
		lnt.setEchoChar('X');
        lnt.setBounds(140,90,100,30);
        lnt.addKeyListener(KAD);
        
        JLabel plabel=new JLabel("New Pin : ");
        plabel.setBounds(30,130,150,30);
        ptf=new JPasswordField();
        ptf.setEchoChar('X');
        ptf.setBounds(140,130,100,30);
        ptf.addKeyListener(KAD);
        
		JLabel nplabel=new JLabel("Confirm new PIN : ");
        nplabel.setBounds(30,170,150,30);
        nptf=new JPasswordField();
        nptf.setEchoChar('X');
        nptf.setBounds(140,170,100,30);
        nptf.addKeyListener(KAD);
		 
        sub=new JButton("Submit");
        sub.setBounds(130,300,100,30);
        sub.setFocusable(false);
        sub.addActionListener(this);
        
        rlabel=new JLabel("");
        rlabel.setBounds(100,340,335,30);
        
	  add(butt);
        panel.add(rlabel);
        panel.add(sub);
        panel.add(ptf);
        panel.add(plabel);
		panel.add(nptf);
        panel.add(nplabel);
        panel.add(lnt);
        panel.add(fnt);
        panel.add(Ln);
        panel.add(Fn);
        add(panel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
		String opin="",ano="";
		try
		{
			try{
				this.db=new Database();
				ano=db.getAccno(name);
				opin=db.getPin(ano);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			String opin1=String.valueOf(fnt.getPassword());
			String opin2=String.valueOf(lnt.getPassword());
			String npin1=String.valueOf(ptf.getPassword());
			String npin2=String.valueOf(nptf.getPassword());
			//System.out.println(opin+" "+opin1+" "+opin2);
			rlabel.setText("");
			if((opin1.equals(opin2))==false)
				rlabel.setText("Old PIN does not match");
			else if((opin1.equals(opin))==false)
				rlabel.setText("Incorrect pin!");
			else if((npin1.equals(npin2))==false)
				rlabel.setText("New PIN does not match");
			else if(npin1.length()!=4)
				rlabel.setText("New PIN should be 4 characters long!");
			else
			{
				int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to change your PIN",
                                                            "Transaction Confirmation Window", JOptionPane.YES_NO_OPTION);
				if(confirmed==JOptionPane.YES_OPTION){
					try{
						this.db=new Database(ano,npin1);
						sub.setEnabled(false);
						rlabel.setText("Your PIN has been updated succesfully!");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
			}
		}
		catch(NumberFormatException nfe)
		{
			rlabel.setText("Enter valid inputs only please !");
		}
		 
    }
}