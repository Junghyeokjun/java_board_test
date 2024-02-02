import java.net.ServerSocket;
import java.net.Socket;

public class JavaBoradServer {
		
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket=new ServerSocket(7777);
			while(true){//늘 돌아간다는 가정
				socket=serverSocket.accept();
				
				ServerReceiver serverReceiver=new ServerReceiver(socket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
