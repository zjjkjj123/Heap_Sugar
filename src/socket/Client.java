package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	PrintStream out;
	BufferedReader in;
	
	public Client(String serverName,int port){
		try{
			//���ݷ��������Ͷ˿ں����ӷ�����
			Socket clientSocket=new Socket(serverName,port);
			//��ȡsocket������/������
			out=new PrintStream(clientSocket.getOutputStream());
			in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(Exception e){
			System.out.println("�޷����ӷ�����");
		}
	}
	public void sendRequest(String request){
		out.println(request);
		System.out.println("Client ��������"+request);
	}
	//��������
	public String getResponse(){
		String str=new String();
		try{
			str=in.readLine();
			System.out.println("Client�յ�Server���أ�"+str);
		}
		catch(IOException e){
			
		}
		return str;
	}
	
}
