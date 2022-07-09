	package BankGUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class WindowChat extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JButton btnNewButton;
    private JTextArea textArea_1;
   
    Socket socket = null;
    String sender;
    String receiver;
    BufferedReader bf = null;
    DataOutputStream os = null;

    
    @Override
    public void run() {
	while (true) {
	    try {
		if (socket != null) {
		    String msg = "";
		    while ((msg = bf.readLine()) != null) {
			
		    	textArea_1.append(msg + "\n");
		    }
		}
	    } catch (Exception e) {
		
	    }
	}
    }

    public WindowChat(Socket s, String sender, String receiver) {
		
    	
    	textArea = new JTextArea();
    	textArea.setForeground(new Color(0, 0, 102));
	    textArea.setBounds(10, 367, 495, 49);
	    textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    
	    btnNewButton = new JButton("Gửi");
	    btnNewButton.setBounds(515, 367, 87, 49);
	    btnNewButton.addActionListener(new ActionListener() {
	    	
		@Override
		public void actionPerformed(ActionEvent arg0) {
		    if (textArea.getText().isEmpty()) return;
		    try {
		    	os.writeBytes(sender + ": " + textArea.getText() + "\n");
		    	os.flush();
		    	textArea_1.append(sender + ": " + textArea.getText() + "\n");
		    	textArea.setText("");
		    } catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, "Lỗi trong khi gửi tin nhắn!");
		    }
		}
	    });
	    btnNewButton.setBackground(new Color(0, 0, 102));
	    btnNewButton.setForeground(Color.WHITE);
	    btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 26));

	    
	    textArea_1 = new JTextArea();
	    textArea_1.setForeground(new Color(0, 0, 102));
	    textArea_1.setBounds(10, 10, 592, 347);
	    textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
	    
	    
	    //Thiết lập socket của client hoặc máy chủ gửi qua
	    
		socket = s;
		this.sender = sender;
		this.receiver = receiver;
		try {
		    bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    os = new DataOutputStream(socket.getOutputStream());
		    setLayout(null);
		    add(textArea_1);
		    add(textArea);
		    add(btnNewButton);
		    
		    
		    (new Thread(this)).start();
		} catch (Exception e) {
		    JOptionPane.showMessageDialog(null, "Error chat");
		}
	
	}
}

