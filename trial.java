import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class delcust extends MFrame implements ActionListener{
	int id;
	JTextField aid;
	JPasswordField pass,conpass,pin,conpin;
	JLabel aidd,done, p, cp, pi, res, cpi;
	JButton comp;
	String str1, str2, str3, str4, str5,name;
	
	delcust(String s) {
		setTitle("Account Owner - "+s);
		comp=new JButton("Delete Account");
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
		
		name=s;
		
		pass.setEchoChar('*');
		conpass.setEchoChar('*');
		pin.setEchoChar('X');
		conpin.setEchoChar('X');
		
		setSize(400,400);
		
        setResizable(false);
		
		getContentPane().setBackground(new Color (242, 180, 104));
		
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
		
		aid.setBounds(195,30,150,30);
		pass.setBounds(195,70,150,30);
		conpass.setBounds(195,110,150,30);
		pin.setBounds(195,150,150,30);
		conpin.setBounds(195,190,150,30);
		
		comp.setBounds(120,240,150,50);
		comp.addActionListener(this);
		res.setBounds(97,275,500,100);
		
		aidd.setFont(aidd.getFont().deriveFont(17f));
		p.setFont(p.getFont().deriveFont(17f));
		cp.setFont(cp.getFont().deriveFont(17f));
		pi.setFont(pi.getFont().deriveFont(17f));
		cpi.setFont(cpi.getFont().deriveFont(17f));
		
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		res.setText("");
		try
		{
			str1=String.valueOf(pass.getPassword());
			str2=String.valueOf(conpass.getPassword());
			str3=String.valueOf(pin.getPassword());
			str4=String.valueOf(conpin.getPassword());
			str5=aid.getText();
			int passno=Integer.parseInt(str1),pinno=Integer.parseInt(str3);
		
			Object source=ae.getSource();
		
			if (source==comp) {
				int d=Integer.parseInt(str5);
				try{
				ellampoyi(d); }
				catch(Exception e){
					System.out.println(e);}
			}
		}			
		catch(NumberFormatException nfe)
		{
			res.setText("Please enter digits only for Account Number,account password and pin");
		}
	}
	
	public void ellampoyi(int accno ) throws SQLException, ClassNotFoundException
	{
		int check=Integer.parseInt(str5);
		int pash=0,pish=0, no=0, bal=0;String nam="";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jbank","root","1234");
		Statement stm=con.createStatement();
		String qry1="SELECT *FROM user where accno="+String.valueOf(accno);
		ResultSet rs=stm.executeQuery(qry1);
		while(rs.next())
		{
			no=rs.getInt(1);
			nam=rs.getString(2);
			pash=rs.getInt(3);
			bal=rs.getInt(4);
			pish=rs.getInt(5);
		}
		String pa=Integer.toString(pash);
		String pn=Integer.toString(pish);
		if(no==check) {
			if(str1.equals(str2) && str1.equals(pa))
			{
				if(str3.equals(str4) && str3.equals(pn))
				{
					if(nam.equals(this.name)){
						String fry="delete from user where accno = "+String.valueOf(accno);
						int confirmed = JOptionPane.showConfirmDialog(null,nam+", are you sure you want to delete your account in South Indian Bank?" ,
																"Account Deletion Confirmation Window", JOptionPane.YES_NO_OPTION);
						if(confirmed==JOptionPane.YES_OPTION){
							stm.executeUpdate(fry);
							res.setText(name+",your account has been succesfully deleted.");
						}
					}
					else
					{
						System.out.println("Name did not match ");
						res.setText("Name did not match.");
					}
				}
				else
					res.setText("Pin Incorrect/Did not match ");
			}
			else
				res.setText("Password Incorrect/Did not match ");
		}
		else
			res.setText("Acc No. does not exist in Database");			
		stm.close();
		con.close();
		rs.close();
	}
	

	public static void main(String st[]) {
		new delcust("Ram Mohan");}
}