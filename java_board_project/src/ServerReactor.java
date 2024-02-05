import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReactor extends Thread {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String tempTitle;
	private String tempDetail;
	private String tempName;
	private int tempNum;

	public ServerReactor(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			out.writeUTF(Board.monitor());
			System.out.println("연결");
			while (in != null) {
				switch (Integer.parseInt(in.readUTF())) {
				case 1: {
					out.writeUTF(Board.listMonitor());
					if (BoardBack.getSize() > 0) {
						for (int i = 0; i < BoardBack.getSize(); i++) {
							out.writeUTF(BoardBack.getSimplePost(i + 1));
						}
					}
					out.writeUTF(Board.listMonitor2());
					break;
				}
				case 2: {
					out.writeUTF("제목>");
					tempTitle = in.readUTF();
					out.writeUTF("내용>");
					tempDetail = in.readUTF();
					out.writeUTF("작성자>");
					tempName = in.readUTF();
					if (BoardBack.add(new Post(tempTitle, tempDetail, tempName))) {
						out.writeUTF(Board.addMonitor());
					} else {
						out.writeUTF(Board.addFailMonitor());
					}
					break;
				}
				case 3: {
					out.writeUTF(Board.watchMonitor());
					if (BoardBack.getSize() > 0) {
						for (int i = 0; i < BoardBack.getSize(); i++) {
							out.writeUTF(BoardBack.getSimplePost(i + 1));
						}
					}
					out.writeUTF(Board.watchMonitor2());
					try {
						if ((tempNum = Integer.parseInt(in.readUTF())) <= BoardBack.getSize()) {
							out.writeUTF(Board.detailMonitor());
							out.writeUTF(BoardBack.getDetailPost(tempNum));
							out.writeUTF(Board.detailMonitor2());

						} else {
							throw new Exception();
						}
					} catch (Exception e) {
						out.writeUTF("잘못된 입력입니다.");
						out.writeUTF(Board.listMonitor());
						if (BoardBack.getSize() > 0) {
							for (int i = 0; i < BoardBack.getSize(); i++) {
								out.writeUTF(BoardBack.getSimplePost(i + 1));
							}
						}
						out.writeUTF(Board.listMonitor2());
					}
					break;
				}
				case 4: {
					out.writeUTF(Board.deleteMonitor());
					if (BoardBack.getSize() > 0) {
						for (int i = 0; i < BoardBack.getSize(); i++) {
							out.writeUTF(BoardBack.getSimplePost(i + 1));
						}
					}
					out.writeUTF(Board.deleteMonitor2());
					try {
						if ((tempNum = Integer.parseInt(in.readUTF())) <= BoardBack.getSize()) {
							BoardBack.delete(tempNum);
							out.writeUTF(Board.listMonitor());
							if (BoardBack.getSize() > 0) {
								for (int i = 0; i < BoardBack.getSize(); i++) {
									out.writeUTF(BoardBack.getSimplePost(i + 1));
								}
							}
							out.writeUTF(Board.listMonitor2());

						} else {
							throw new Exception();
						}
					} catch (Exception e) {
						out.writeUTF("잘못된 입력입니다.");
						out.writeUTF(Board.listMonitor());
						if (BoardBack.getSize() > 0) {
							for (int i = 0; i < BoardBack.getSize(); i++) {
								out.writeUTF(BoardBack.getSimplePost(i + 1));
							}
						}
						out.writeUTF(Board.listMonitor2());
					}
					break;
				}
				case 0: {
					throw new Exception();
				}
				default: {
					out.writeUTF("잘못된 입력입니다.");
				}

				}
			}
		} catch (Exception e) {
			System.out.println(socket.getLocalAddress() + "을 종료합니다");
		}

	}

}
