import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientSender extends Thread{
	private Socket socket;
	private DataOutputStream out;
	public ClientSender(Socket socket) {
		this.socket=socket;
		
		try {
			out=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			System.out.println("연결에 실패했습니다.");
		}
	
	}
	@Override
	public void run() {
		Scanner sc=new Scanner(System.in);
		while(out!=null) {
			try {
				out.writeUTF(sc.nextLine());
			} catch (IOException e) {
				System.out.println("오류가 발생했습니다.");
				break;
			}
		}
	}


}
