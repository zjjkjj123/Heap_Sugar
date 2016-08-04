package other;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import socket.Client;

public class ClientFrame extends JFrame implements ActionListener {
	JButton sendButton;//发送按钮
	JButton clearButton;//清空按钮
	JTextField inputField;//发送的内容
	JTextArea outputArea;//接受服务器返回结果的文本域
	Client client;//客户端对象
	
	public ClientFrame(){

		inputField=new JTextField(20);
		outputArea=new JTextArea(6,20);
		JLabel label1=new JLabel("请输入：");
		JLabel label2=new JLabel("结果：");
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel(new BorderLayout());
		JScrollPane scroll=new JScrollPane(outputArea);
//		JPanel panel5=new JPanel();
		panel1.add(label1);
		panel1.add(inputField);
//		panel5.add(outputArea);
		panel3.add(label2,BorderLayout.NORTH);
		panel3.add(scroll,BorderLayout.CENTER);
		
		sendButton=new JButton("发送");
		clearButton=new JButton("清空");
		sendButton.addActionListener(this);
		clearButton.addActionListener(this);
		panel2.add(sendButton);
		panel2.add(clearButton);
		
		JPanel panel4=new JPanel(new BorderLayout());
		panel4.add(panel1,BorderLayout.NORTH);
		panel4.add(panel2,BorderLayout.CENTER);
		panel4.add(panel3,BorderLayout.PAGE_END);
		
		setTitle("Socket客户端");
		this.getContentPane().add(panel4);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==sendButton){
			if(!inputField.getText().trim().isEmpty()){
				try{
					client.sendRequest(inputField.getText().trim());
				}catch(Exception ee){
					ee.printStackTrace();
				}
				outputArea.append(client.getResponse()+"\n");
			}
			else{
				JOptionPane.showConfirmDialog(this, "输入不能为空！");
			}
		}
		else{
			inputField.setText("");
			outputArea.setText("");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientFrame frame=new ClientFrame();
		frame.pack();
		frame.client=new Client("127.0.0.1",8080);
		frame.setVisible(true);
	}
}
