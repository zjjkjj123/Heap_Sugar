package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import other.Result;

public class Server {
	ServerSocket serverSkt=null;//��������������Socket
	Socket clientSkt=null;//�ͻ���
	BufferedReader in=null;//�ͻ���������
	PrintStream out=null;//�ͻ��������
	
	public Server (int port){
		System.out.println("�������������ڼ������˿ڣ�"+port);
		try{
			serverSkt=new ServerSocket(port);//��������socket
			
		}catch(IOException e){
			System.out.println("�����˿�:"+port+"ʧ��");
		}
		try{
			clientSkt=serverSkt.accept();//������������
		}
		catch(IOException e){
			System.out.println("����ʧ��");
		}
		try{
			in=new BufferedReader (new InputStreamReader(clientSkt.getInputStream()));
			out=new PrintStream(clientSkt.getOutputStream());
		}
		catch(IOException e){
			
		}
	}
	//�յ��ͻ�������
	public String getRequest(){
		String frmClt=null;
		
		try{
			frmClt=in.readLine();
			String result=new Result().getResult(frmClt);
			System.out.println("Server �յ�����"+frmClt);
			return result;
		}
		catch(IOException e){
			System.out.println("�޷���ȡ�˿�......");
			System.exit(0);
		}
		return frmClt;
	}
	
	//������Ӧ���ͻ���
	public void sendResponse(String response){
		try{
			out.println(response);
			System.out.print("Server ��Ӧ����"+response);
		}
		catch(Exception e){
			System.out.print("д�˿�ʧ��......");
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
