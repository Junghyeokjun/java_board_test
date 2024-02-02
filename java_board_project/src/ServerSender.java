import java.net.Socket;

public class ServerSender extends Thread{
	private Socket socket;
	public ServerSender(Socket socket) {
		this.socket=socket;
	}


}
