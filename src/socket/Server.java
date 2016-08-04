package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import other.Result;

public class Server {
	ServerSocket serverSkt=null;//服务器端侦听的Socket
	Socket clientSkt=null;//客户端
	BufferedReader in=null;//客户端输入流
	PrintStream out=null;//客户端输出流
	
	public Server (int port){
		System.out.println("服务器代理正在监听，端口："+port);
		try{
			serverSkt=new ServerSocket(port);//创建监听socket
			
		}catch(IOException e){
			System.out.println("监听端口:"+port+"失败");
		}
		try{
			clientSkt=serverSkt.accept();//接受连接请求
		}
		catch(IOException e){
			System.out.println("连接失败");
		}
		try{
			in=new BufferedReader (new InputStreamReader(clientSkt.getInputStream()));
			out=new PrintStream(clientSkt.getOutputStream());
		}
		catch(IOException e){
			
		}
	}
	//收到客户端请求
	public String getRequest(){
		String frmClt=null;
		
		try{
			frmClt=in.readLine();
			String result=new Result().getResult(frmClt);
			System.out.println("Server 收到请求："+frmClt);
			return result;
		}
		catch(IOException e){
			System.out.println("无法读取端口......");
			System.exit(0);
		}
		return frmClt;
	}
	
	//发送响应给客户端
	public void sendResponse(String response){
		try{
			out.println(response);
			System.out.print("Server 响应请求："+response);
		}
		catch(Exception e){
			System.out.print("写端口失败......");
			System.exit(0);
			
		}
	}

	
	public static void main(String[] args) throws IOException {
		Server s=new Server(8080);
		while(true){
			s.sendResponse(s.getRequest());
		}

	}
}
