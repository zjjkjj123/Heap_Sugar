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
	JButton sendButton;//���Ͱ�ť
	JButton clearButton;//��հ�ť
	JTextField inputField;//���͵�����
	JTextArea outputArea;//���ܷ��������ؽ�����ı���
	Client client;//�ͻ��˶���
	
	public ClientFrame(){

		inputField=new JTextField(20);
		outputArea=new JTextArea(6,20);
		JLabel label1=new JLabel("�����룺");
		JLabel label2=new JLabel("�����");
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
		
		sendButton=new JButton("����");
		clearButton=new JButton("���");
		sendButton.addActionListener(this);
		clearButton.addActionListener(this);
		panel2.add(sendButton);
		panel2.add(clearButton);
		
		JPanel panel4=new JPanel(new BorderLayout());
		panel4.add(panel1,BorderLayout.NORTH);
		panel4.add(panel2,BorderLayout.CENTER);
		panel4.add(panel3,BorderLayout.PAGE_END);
		
		setTitle("Socket�ͻ���");
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
				JOptionPane.showConfirmDialog(this, "���벻��Ϊ�գ�");
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
