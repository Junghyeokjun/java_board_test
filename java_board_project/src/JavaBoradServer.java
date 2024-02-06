import java.net.ServerSocket;
import java.net.Socket;

public class JavaBoradServer {
		
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			BoardBack.initBoard();
			serverSocket=new ServerSocket(12458);
			
			
			while(true){//늘 돌아간다는 가정
				socket=serverSocket.accept();
				System.out.println("연결완료");
				ServerReactor serverReactor=new ServerReactor(socket);
				
				serverReactor.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("서버가 종료되었습니다.");
		}
		
		
	}

}
