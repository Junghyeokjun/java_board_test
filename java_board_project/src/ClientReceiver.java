import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver extends Thread {
	private Socket socket;
	private DataInputStream in;
	public ClientReceiver(Socket socket) {
		this.socket=socket;
		try {
			in=new DataInputStream(this.socket.getInputStream());
		} catch (Exception e) {
			System.out.println("연결에 실패했습니다.");
		}
		
	}
	@Override
	public void run() {
		while(in!=null) {
			try {
				System.out.print(in.readUTF());
			} catch (IOException e) {
				System.out.println("오류가 발생했습니다.");
				break;

			}
		}
	}
	
}
