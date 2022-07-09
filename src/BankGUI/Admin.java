package BankGUI;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;

public class Admin extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTabbedPane tabbedPane;
    private JButton btnNewButton;
    
    Admin thisServer;
    ServerSocket socket = null;
    DataInputStream stream_in = null;
    Thread t;
    private JLabel lblNewLabel_2;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Admin frame = new Admin();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public Admin() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\eclipse-workspace\\BankingGUI\\src\\admin-removebg-preview.png"));
    	setTitle("Admin");
    	setResizable(false);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 660, 611);
    	contentPane = new JPanel();
    	contentPane.setBackground(new Color(0, 0, 102));
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	setContentPane(contentPane);
    	contentPane.setLayout(null);
    	
    	tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    	tabbedPane.setForeground(Color.WHITE);
    	tabbedPane.setBackground(new Color(0, 0, 102));
        tabbedPane.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        tabbedPane.setBorder(null);
        tabbedPane.setBounds(10, 97, 624, 467);
        tabbedPane.addTab(null, null, lblNewLabel_2, null);
    	contentPane.add(tabbedPane);
    	
    	btnNewButton = new JButton("KHỞI ĐỘNG");
    	btnNewButton.setForeground(new Color(255, 255, 255));
    	btnNewButton.setBackground(new Color(0, 0, 102));
        btnNewButton.setBorder(null);
        btnNewButton.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent arg0) {
    	    
    	    int port = 2003;
    	    
    	    try {
    		
    	    	socket = new ServerSocket(port);
    	    	System.out.println("Server is running...");
    	    	System.out.println("Connected to banking");
    	    	
    			JOptionPane.showMessageDialog(contentPane, "Đang chạy tại cổng: " + port);
    		               

    	    } catch (Exception e) {
    	    	JOptionPane.showMessageDialog(contentPane, "Details: " + e
    		               );
    	    }

    	   
    	    Thread t = new Thread(thisServer);
    	    t.start();
    	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(10, 10, 626, 42);
    	contentPane.add(btnNewButton);
    	
    	lblNewLabel_2 = new JLabel("Waitting for client");
        lblNewLabel_2.setBackground(Color.WHITE);
        lblNewLabel_2.setForeground(Color.RED);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblNewLabel_2);
    	thisServer = this;
    	setLocationRelativeTo(null);
    }



    

    @Override
    public void run() {
	while (true)

	    try {
	
		Socket clientSocket = socket.accept();
		
		if (clientSocket != null) {
		    
			stream_in = new DataInputStream(clientSocket.getInputStream());
		    
		   
		    String data = stream_in.readUTF();
			String arr[] = data.split(";");
			String money = arr[0].toString();
			String option = arr[1].toString();
			String sotkneed = arr[2].toString();
			String sotkroot = arr[3].toString();
			
			
			DataOutputStream stream_out = new DataOutputStream(clientSocket.getOutputStream());
			
			if (option.equals("2")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate = "update accounts set Sodu = Sodu + \"" + money + "\" where Sotaikhoan = \"" + sotkneed + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			if (option.equals("1")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate = "update accounts set Sodu = Sodu + \"" + money + "\" where Sotaikhoan = \"" + sotkneed + "\"";
					String sqlUpdate2 = "update accounts set Sodu = Sodu - \"" + money + "\" where Sotaikhoan = \"" + sotkroot + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					stmt.executeUpdate(sqlUpdate2);
					
					
					
					stream_out.writeUTF("success");
					stream_out.flush();
					
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			
			if (option.equals("3")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate = "update accounts set Sodu = Sodu - \"" + money + "\" where Sotaikhoan = \"" + sotkneed + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			if (option.equals("4")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate = "update accounts set Sodu = Sodu + \"" + money + "\" where Sotaikhoan = \"" + sotkneed + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			
			if (option.equals("5")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate = "update accounts set Sodu = Sodu - \"" + money + "\" where Sotaikhoan = \"" + sotkneed + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			
			if (option.equals("6")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate =  "delete from accounts where Sotaikhoan = \"" + sotkroot + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			
			if (option.equals("7")) {
				try {
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","andubadao123");
					Statement stmt = conn.createStatement();
					String sqlUpdate =  "update accounts set Matkhau = \"" + sotkneed + "\" where Sotaikhoan = \"" + money + "\"";
					
					stmt.executeUpdate(sqlUpdate);
					
					stream_out.writeUTF("success");
					stream_out.flush();
					System.out.println("Process success!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error!");
				}
			}
			
			if (option.equals("message")){
				 	WindowChat chatPanel = new WindowChat(clientSocket, "Admin", sotkroot);
				    tabbedPane.add(sotkroot, chatPanel);
				    chatPanel.updateUI();
				    
				    Thread t = new Thread(chatPanel);
				    t.start();
			} 
			
			
					}

		
		Thread.sleep(1000);
	    } catch (Exception e) {
		
	    }
    }

    

  
}
