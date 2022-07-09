package BankGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfTendn;
	private JPasswordField psMk;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\eclipse-workspace\\BankingGUI\\src\\bank-removebg-preview.png"));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("ABCBANK");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(94, 51, 182, 77);
		contentPane.add(lblTitle);
		
		JLabel lblTendn = new JLabel("Tên đăng nhập:");
		lblTendn.setForeground(Color.WHITE);
		lblTendn.setBounds(94, 161, 98, 20);
		contentPane.add(lblTendn);
		
		tfTendn = new JTextField();
		tfTendn.setBounds(94, 191, 182, 29);
		contentPane.add(tfTendn);
		tfTendn.setColumns(10);
		
		JLabel lblMk = new JLabel("Mật khẩu");
		lblMk.setForeground(Color.WHITE);
		lblMk.setBounds(94, 238, 98, 20);
		contentPane.add(lblMk);
		
		JButton btnDn = new JButton("Đăng nhập");
		btnDn.setForeground(Color.WHITE);
		btnDn.setBackground(new Color(0, 0, 102));
		btnDn.setBounds(94, 334, 182, 29);
		contentPane.add(btnDn);
		btnDn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfTendn.getText().equals("") || psMk.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Tên đăng nhập hoặc mật khẩu trống!");
				}else {
					
						if (checkAccount(tfTendn.getText(), psMk.getText()) == true) {
							JOptionPane.showMessageDialog(rootPane, "Đăng nhập thành công!");
							
							MainGUI mainGUI = new MainGUI(tfTendn.getText());
							setVisible(false);	
							mainGUI.setVisible(true); 
							    
							
						}else {
							JOptionPane.showMessageDialog(rootPane, "Tài khoản không tồn tại!");
						}
					
				}
				
				
			}
		});
		
		JButton btnTaik = new JButton("Tạo tài khoản ");
		btnTaik.setBackground(new Color(0, 0, 102));
		btnTaik.setForeground(Color.WHITE);
		btnTaik.setBounds(94, 375, 182, 29);
		contentPane.add(btnTaik);
		btnTaik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				register.setVisible(true);
			}
		});
		
		psMk = new JPasswordField();
		psMk.setBounds(94, 268, 182, 29);
		contentPane.add(psMk);
		
		
		
		setLocationRelativeTo(null);
	}
	
	public boolean checkAccount(String tendangnhap,String matkhau) {
		
		boolean check = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from accounts");
			
			while (rs.next()) {
				if (tendangnhap.equals(rs.getString(1)) && matkhau.equals(rs.getString(2))) {
					check = true;
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return check;
	}
}
