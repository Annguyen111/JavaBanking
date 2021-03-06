package BankGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.RadialGradientPaint;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblSotk;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblSodu;
	private Socket socket;
	private PrintWriter printWriter;
	private DataInputStream dataInputStream;
	private int mm = 0;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private String title[] = {"Ho???t ?????ng","T??i kho???n nh???n","Th???i gian"}; 
	private DefaultTableModel model;
	private LocalDateTime current;
	private DateTimeFormatter formatter;
	
	
	public MainGUI(String tendangnhap) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\eclipse-workspace\\BankingGUI\\src\\bank-removebg-preview.png"));
		setTitle("ABCBANK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblTkthanhtoan = new JLabel("S??? t??i kho???n");
		lblTkthanhtoan.setForeground(Color.WHITE);
		lblTkthanhtoan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTkthanhtoan.setBounds(257, 10, 126, 29);
		contentPane.add(lblTkthanhtoan);
		
	
		lblSotk = new JLabel();
		lblSotk.setForeground(Color.WHITE);
		lblSotk.setHorizontalAlignment(SwingConstants.CENTER);
		lblSotk.setBounds(257, 49, 126, 29);
		contentPane.add(lblSotk);
		loadSotk(tendangnhap);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 127, 612, 457);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setToolTipText("");
		
		tabbedPane.addTab("Chuy???n ti???n",null, panel,null); 
		
		tabbedPane.setForegroundAt(0, new Color(0, 0, 102));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S??? t??i kho???n");
		lblNewLabel.setForeground(new Color(0, 0, 102));
		lblNewLabel.setBounds(10, 10, 331, 30);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 102));
		textField.setBounds(10, 50, 587, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ng??n h??ng");
		lblNewLabel_1.setForeground(new Color(0, 0, 102));
		lblNewLabel_1.setBounds(10, 90, 331, 30);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 102));
		comboBox.setBounds(10, 130, 587, 35);
		panel.add(comboBox);
		comboBox.addItem("ABCBANK");
		comboBox.addItem("MBBANK");
	
		comboBox.addItem("VIETCOMBANK");
	
		comboBox.addItem("SACOMBANK");
		
		
		JLabel lblNewLabel_2 = new JLabel("S??? ti???n");
		lblNewLabel_2.setForeground(new Color(0, 0, 102));
		lblNewLabel_2.setBounds(10, 175, 331, 30);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setForeground(new Color(0, 0, 102));
		textField_1.setBounds(10, 215, 587, 35);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Chuy???n");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 0, 102));
		btnNewButton.setBounds(10, 372, 587, 48);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane, "Ch??a ??i???n th??ng tin s??? t??i kho???n ho???c s??? ti???n!");
				}else {
					if (checkTkExist(textField.getText()) == false) {
						JOptionPane.showMessageDialog(rootPane, "T??i kho???n kh??ng t???n t???i!");
					}else {
						if (textField.getText().equals(lblSotk.getText())) {
							JOptionPane.showMessageDialog(rootPane, "Tr??ng t??i kho???n g???c, vui l??ng chuy???n sang n???p!");
						}else {
							JOptionPane.showConfirmDialog(rootPane,
			                        "B???n x??c nh???n chuy???n " + textField_1.getText() + " ?????n s??? t??i kho???n " + textField.getText(),
			                        "X??c nh???n",
			                        JOptionPane.YES_NO_OPTION,
			                        JOptionPane.QUESTION_MESSAGE);
							try {
								Socket socket = new Socket("localhost", 2003);
								DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
								dataOutputStream.writeUTF(textField_1.getText()+ ";1;" + textField.getText() + ";" + lblSotk.getText());
								dataOutputStream.flush();	
								DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
								if (dataInputStream.readUTF().equals("success")) {
									JOptionPane.showMessageDialog(rootPane, "Chuy???n th??nh c??ng " + textField_1.getText() + "VN?? sang s??? t??i kho???n " + textField.getText());
									current = LocalDateTime.now();
									formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
									String time = current.format(formatter);
									History history = new History("Chuy???n ti???n", textField.getText(), time );
									addHistory(lblSotk.getText(), history.getHoatdong(), history.getTaikhoannhan(), history.getThoigian());
								}else {
									JOptionPane.showMessageDialog(rootPane, "Ti???n ch??a chuy???n ??i!");
								}
								
							} catch (Exception e2) {
								
							}
							
							loadSodu(tendangnhap);
						}
						
					}
					
				}
				
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 102));
		tabbedPane.addTab("N???p ti???n", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("S??? ti???n");
		lblNewLabel_3.setForeground(new Color(0, 0, 102));
		lblNewLabel_3.setBounds(10, 10, 331, 29);
		panel_1.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(0, 0, 102));
		textField_2.setBounds(10, 49, 587, 36);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("200.000");
		btnNewButton_1.setBackground(new Color(0, 0, 102));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(125, 95, 125, 36);
		panel_1.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("200000");
				
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("500.000");
		btnNewButton_1_1.setBackground(new Color(0, 0, 102));
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBounds(260, 95, 115, 36);
		panel_1.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("500000");
				
			}
		});
		
		JButton btnNewButton_1_2 = new JButton("1.000.000");
		btnNewButton_1_2.setBackground(new Color(0, 0, 102));
		btnNewButton_1_2.setForeground(Color.WHITE);
		btnNewButton_1_2.setBounds(385, 95, 113, 36);
		panel_1.add(btnNewButton_1_2);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("1000000");
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("N???p");
		btnNewButton_2.setBackground(new Color(0, 0, 102));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBounds(10, 373, 587, 47);
		panel_1.add(btnNewButton_2);
		tabbedPane.setForegroundAt(1, new Color(0, 0, 102));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ch??a nh???p s??? ti???n!");
					
				}else {
					try {
						Socket socket = new Socket("localhost", 2003);
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF(textField_2.getText()+ ";2;" + lblSotk.getText() + ";" + lblSotk.getText() );
						dataOutputStream.flush();	
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						if (dataInputStream.readUTF().equals("success")) {
							JOptionPane.showMessageDialog(rootPane, "N???p th??nh c??ng " + textField_2.getText() + "VN??");
							current = LocalDateTime.now();
							formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
							String time = current.format(formatter);
							History history = new History("N???p ti???n", "T??i kho???n hi???n t???i", time );
							addHistory(lblSotk.getText(), history.getHoatdong(), history.getTaikhoannhan(), history.getThoigian());
						}else {
							JOptionPane.showMessageDialog(rootPane, "N???p ch??a th??nh c??ng!");
						}
						
					} catch (Exception e2) {
						
					}
				}
				
				textField_2.setText("");
				loadSodu(tendangnhap);
				
				
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 102));
		tabbedPane.addTab("X??a t??i kho???n", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("X??a ");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(new Color(0, 0, 102));
		btnNewButton_3.setBounds(257, 152, 104, 99);
		panel_2.add(btnNewButton_3);
		tabbedPane.setForegroundAt(2, new Color(0, 0, 102));
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket = new Socket("localhost", 2003);
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					dataOutputStream.writeUTF("X??a;6;" + lblSotk.getText() + ";" + lblSotk.getText() );
					dataOutputStream.flush();	
					DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
					JOptionPane.showConfirmDialog(rootPane,
	                        "B???n c?? ch???c ch???n mu???n x??a t??i kho???n",
	                        "X??c nh???n",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.QUESTION_MESSAGE);
					if (dataInputStream.readUTF().equals("success")) {
						JOptionPane.showMessageDialog(rootPane, "X??a th??nh c??ng " + lblSotk.getText() + "\nS??? ????ng nh???p l???i ???ng d???ng");
						setVisible(false);
						Login login = new Login();
						login.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(rootPane, "X??a ch??a th??nh c??ng!");
					}
					
				} catch (Exception e2) {
					
				}
			}
		});
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("R??t ti???n", null, panel_3, null);
		tabbedPane.setForegroundAt(3, new Color(0, 0, 102));
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("S??? ti???n c???n r??t");
		lblNewLabel_8.setForeground(new Color(0, 0, 102));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 10, 587, 45);
		panel_3.add(lblNewLabel_8);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 67, 587, 45);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("R??t");
		btnNewButton_5.setBackground(new Color(0, 0, 102));
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setBounds(10, 375, 587, 45);
		panel_3.add(btnNewButton_5);
		
		JPanel panel_4 = new JPanel();
		panel_4.setForeground(new Color(0, 0, 102));
		tabbedPane.addTab("?????i m???t kh???u", null, panel_4, null);
		tabbedPane.setForegroundAt(4, new Color(0, 0, 102));
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("M???t kh???u c??");
		lblNewLabel_4.setForeground(new Color(0, 0, 102));
		lblNewLabel_4.setBounds(10, 36, 151, 29);
		panel_4.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 75, 587, 29);
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("M???t kh???u m???i");
		lblNewLabel_5.setForeground(new Color(0, 0, 102));
		lblNewLabel_5.setBounds(10, 139, 151, 29);
		panel_4.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(10, 179, 587, 29);
		panel_4.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("?????i");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(new Color(0, 0, 102));
		btnNewButton_4.setBounds(10, 370, 587, 50);
		panel_4.add(btnNewButton_4);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("L???ch s???", null, panel_7, null);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("L???ch s??? ho???t ?????ng");
		lblNewLabel_6.setForeground(new Color(0, 0, 102));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(235, 10, 135, 38);
		panel_7.add(lblNewLabel_6);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(0, 58, 607, 372);
		panel_7.add(panel_8);
		panel_8.setLayout(null);
		

		model = new DefaultTableModel();
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T??i kho???n g???i","Ho???t ?????ng", "T??i kho???n nh???n", "Th???i gian"
			}
		));
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 102));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 607, 372);
		panel_8.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		tabbedPane.setForegroundAt(5, new Color(0, 0, 102));
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Li??n h???", null, panel_5, null);
		tabbedPane.setForegroundAt(6, new Color(0, 0, 102));
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(0, 0, 607, 430);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btnNewButton_6 = new JButton("K???t n???i");
		btnNewButton_6.setBounds(264, 23, 85, 42);
		panel_6.add(btnNewButton_6);
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setBackground(new Color(0, 0, 102));
		btnNewButton_6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Socket socket = new Socket("localhost",2003);
					DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
					dataOutputStream.writeUTF(";message;" + lblSotk.getText() + ";" + lblSotk.getText() );
					dataOutputStream.flush();	
					
					if (socket == null) throw new Exception("Null Socket");
					
					tabbedPane.remove(6);;
					WindowChat chatWindow = new WindowChat(socket, lblSotk.getText(), "Admin");
					tabbedPane.add("Li??n h???",chatWindow);
					tabbedPane.setForegroundAt(6, new Color(0, 0, 102));
					chatWindow.updateUI();
					
					Thread thread = new Thread(chatWindow);
					thread.start();
					
					JOptionPane.showMessageDialog(contentPane, "K???t n???i th??nh c??ng!");
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(contentPane, "L???i k???t n???i!" );
					
				}
				
			}
		});
		
		
		
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkMk(textField_4.getText())  == true) {
					JOptionPane.showMessageDialog(rootPane, "M???t kh???u ch??a ????ng!");
				}else if (textField_4.getText() == textField_5.getText()) {
					JOptionPane.showMessageDialog(rootPane, "B??? tr??ng m???t kh???u!");
				}else if (checkMk(textField_5.getText()) == false) {
					JOptionPane.showMessageDialog(rootPane, "M???t kh???u ???? t???n t???i!");
				}else if (textField_4.getText().equals("") || textField_5.getText().equals("")){
					JOptionPane.showMessageDialog(rootPane, "Ch??a nh???p d??? li???u!");
				}else {
					try {
						Socket socket = new Socket("localhost", 2003);
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF(lblSotk.getText() +  ";7;" + textField_5.getText() + ";" + textField_4.getText() );
						dataOutputStream.flush();	
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						if (dataInputStream.readUTF().equals("success")) {
							JOptionPane.showMessageDialog(rootPane, "?????i th??nh c??ng " + textField_4.getText() + " sang " + textField_5.getText());
							
						}else {
							JOptionPane.showMessageDialog(rootPane, "?????i ch??a th??nh c??ng!");
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				
			}
		});
		
		
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textField_3.getText().equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ch??a nh???p s??? ti???n!");
				}else {
					try {
						Socket socket = new Socket("localhost", 2003);
						DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
						dataOutputStream.writeUTF(textField_3.getText()+ ";5;" + lblSotk.getText() + ";" + lblSotk.getText() );
						dataOutputStream.flush();	
						DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
						if (dataInputStream.readUTF().equals("success")) {
							JOptionPane.showMessageDialog(rootPane, "R??t th??nh c??ng " + textField_3.getText() + "VN??");
							current = LocalDateTime.now();
							formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
							String time = current.format(formatter);
							History history = new History("R??t ti???n", "T??i kho???n hi???n t???i", time );
							addHistory(lblSotk.getText(),  history.getHoatdong(), history.getTaikhoannhan(), history.getThoigian());
						}else {
							JOptionPane.showMessageDialog(rootPane, "R??t ch??a th??nh c??ng!");
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					loadSodu(tendangnhap);
					textField_3.setText("");
					
				}
				
			}
		});
		
		
		lblSodu = new JLabel("");
		lblSodu.setForeground(Color.WHITE);
		lblSodu.setHorizontalAlignment(SwingConstants.CENTER);
		lblSodu.setBounds(241, 88, 157, 29);
		contentPane.add(lblSodu);
		
		JButton btnDangxuat = new JButton("????ng xu???t");
		btnDangxuat.setForeground(Color.WHITE);
		btnDangxuat.setBackground(new Color(0, 0, 102));
		btnDangxuat.setBounds(264, 594, 119, 44);
		contentPane.add(btnDangxuat);
		btnDangxuat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				Login login = new Login();
				login.setVisible(true);
			}
		});
		loadSodu(tendangnhap);
		
	}
	
	public void loadSotk(String tendn) {
		
		try {
			String temp = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select Sotaikhoan from accounts where Tendangnhap = \"" + tendn + "\"");
			
			while (rs.next()) {
				temp = rs.getString(1);
			}
			
			lblSotk.setText( temp );
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void loadSodu(String tendn) {
		try {
			String temp = "";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select Sodu from accounts where Tendangnhap = \"" + tendn + "\"");
			
			while (rs.next()) {
				temp = rs.getString(1);
			}
			
			lblSodu.setText("S??? d??: " + temp + " VN??");
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean checkTkExist(String sotaikhoan) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select Tendangnhap from accounts where Sotaikhoan = \"" + sotaikhoan + "\"");
			
			int count = 0;
			
			while (rs.next()) {
				count++;
			}
			
			if (count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	
	public boolean checkMk(String mkcu) {
		int count = 0;
		boolean check = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select Tendangnhap from accounts where Matkhau = \"" + mkcu + "\"");
			
			while  (rs.next()) {
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

	
	public void addHistory(String tkg,String hd,String tkn,String tg) {
		model = (DefaultTableModel)table.getModel();
		model.addRow(new Object[] {tkg,hd,tkn,tg});
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
			Statement stmt = conn.createStatement();
			String sqlUpdate =  "insert into history values(\"" + tkg + "\",\"" + hd + "\",\"" + tkn + "\",\"" + tg + "\")";
			
			
			stmt.executeUpdate(sqlUpdate);
			
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
