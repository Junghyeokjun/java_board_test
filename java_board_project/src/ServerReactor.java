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
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void catchMonitor(DataOutputStream out) throws Exception {
		out.writeUTF(Board.listMonitor());
		if (BoardBack.getSize() > 0) {
			for (int i = 0; i < BoardBack.getSize(); i++) {
				out.writeUTF(BoardBack.getSimplePost(i + 1));
			}
		}
		out.writeUTF(Board.listMonitor2());
	}

	@Override
	public void run() {
		try {
			catchMonitor(out);
			System.out.println("연결");

			while (in != null) {
				try {
					tempNum = Integer.parseInt(in.readUTF());
				} catch (Exception e) {
					catchMonitor(out);
					continue;
				}
				switch (tempNum) {
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
						out.writeUTF("잘못된 입력입니다.\n");
						catchMonitor(out);

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
						out.writeUTF("잘못된 입력입니다.\n");
						catchMonitor(out);
					}
					break;
				}
				case 0: {
					out.writeUTF(null);
					throw new Exception();
				}
				default: {
					out.writeUTF("잘못된 입력입니다.\n");
					catchMonitor(out);
				}

				}
			}
		} catch (Exception e) {
			System.out.println(socket.getLocalAddress() + "을 종료합니다");
		}

	}

}
