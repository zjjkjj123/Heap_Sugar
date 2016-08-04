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
			//根据服务器名和端口号连接服务器
			Socket clientSocket=new Socket(serverName,port);
			//获取socket的输入/输入流
			out=new PrintStream(clientSocket.getOutputStream());
			in= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}
		catch(Exception e){
			System.out.println("无法连接服务器");
		}
	}
	public void sendRequest(String request){
		out.println(request);
		System.out.println("Client 发送请求："+request);
	}
	//发送请求
	public String getResponse(){
		String str=new String();
		try{
			str=in.readLine();
			System.out.println("Client收到Server返回："+str);
		}
		catch(IOException e){
			
		}
		return str;
	}
	
}
