import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;

	public ServerReceiver(Socket socket) {
		this.socket=socket;
		try {
			in=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			while(in!=null) {
				switch(Integer.parseInt(in.readUTF())){
				case 1:{
					out.writeUTF("");
				}
				case 2:
				case 3:
				case 4:
				case 0:{
					throw new Exception();
				}
				default:{
					out.writeUTF("잘못된 입력입니다.");
				}
					
				}
			}
		} catch (Exception e) {
			System.out.println(socket.getLocalAddress()+"을 종료합니다");
		}
		
	}

}
