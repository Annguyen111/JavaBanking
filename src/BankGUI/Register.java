package BankGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfTDN;
	private JTextField tfST;
	private JPasswordField psMk;
	

	
	public Register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\eclipse-workspace\\BankingGUI\\src\\bank-removebg-preview.png"));
		setTitle("Đăng ký");
		setBounds(100, 100, 334, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 0, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Tên đăng nhập:");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(62, 43, 112, 28);
			contentPanel.add(lblNewLabel);
		}
		{
			tfTDN = new JTextField();
			tfTDN.setBounds(62, 81, 194, 28);
			contentPanel.add(tfTDN);
			tfTDN.setColumns(10);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Mật khẩu:");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setBounds(62, 119, 112, 28);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Số tiền cần nạp:");
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setBounds(62, 195, 112, 28);
			contentPanel.add(lblNewLabel_2);
		}
		{
			tfST = new JTextField();
			tfST.setBounds(62, 233, 194, 28);
			contentPanel.add(tfST);
			
			tfST.setColumns(10);
		}
		{
			psMk = new JPasswordField();
			psMk.setBounds(62, 157, 194, 28);
			contentPanel.add(psMk);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 102));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Tạo");
				okButton.setForeground(Color.WHITE);
				okButton.setBackground(new Color(0, 0, 102));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (tfTDN.getText().equals("") || psMk.getText().equals("")) {
							JOptionPane.showMessageDialog(rootPane, "Thông tin trống!");
						}else if (checkTkMk(tfTDN.getText(), psMk.getText()) == false){
							JOptionPane.showMessageDialog(rootPane, "Tải khoản đã tồn tại!");
							
						}else {
							if (tfST.getText().equals("")) {
								createTK(0);
							}else {
								createTK(Integer.parseInt(tfST.getText()));
							}
						}
						
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Thoát");
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setBackground(new Color(0, 0, 102));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void createTK(int st) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement stmt = conn.createStatement();
			
			String ten = tfTDN.getText();
			String matk = psMk.getText();
			
			int sot = st;
			String sql = "insert into accounts(Tendangnhap,Matkhau,Sodu,Nganhang)"
					+ "values (\"" + ten + "\",\"" + matk + "\",\"" + sot + "\",\"ABCBank\")";
			
			
			
			stmt.executeUpdate(sql);
				
			conn.close();
			stmt.close();
			
			JOptionPane.showMessageDialog(rootPane, "Tạo thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	
	public boolean checkTkMk(String tendangnhap, String matkhau) {
		int count = 0;
		boolean check = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement stmt = conn.createStatement();
			
			String sql = "select * from accounts where Tendangnhap = \"" + tendangnhap + "\" and Matkhau = \"" + matkhau + "\"";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				count++;
			}
			
			if (count < 1) {
				check = true;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}

}
