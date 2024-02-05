import java.net.Socket;

public class JavaBoardClient {
	
	public static void main(String[] args) {
		Socket socket=null;
		try {
			socket=new Socket("localhost",12458);
			
			ClientSender sender=new ClientSender(socket);
			ClientReceiver receiver=new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			System.out.println("연결에 실패하였습니다.");

		}
	}
}
