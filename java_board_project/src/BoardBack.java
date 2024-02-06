import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class BoardBack {
	private static List<Post> postList = new ArrayList<>();
	final static File FOLDER = new File("C:\\java_board_server");
	final static File LISTFILE = new File("C:\\java_board_server\\listdata.txt");

	public static boolean add(Post post) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(LISTFILE, true))) {
			postList.add(post);
			bw.append(post.getAddPost());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void delete(int i) {
		synchronized (Post.class) {
			postList.remove(i - 1);
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(LISTFILE))) {
				for (Post post : postList) {
					bw.write(post.getAddPost());
				}
			} catch (Exception e) {
				System.out.println("파일삭제에 실패하였습니다.");
			}
		}

	}

	public static String getSimplePost(int i) {
		return postList.get(i - 1).getSimplePost();
	}

	public static String getDetailPost(int i) {
		return postList.get(i - 1).getPost();
	}

	public static Post getPost(int i) {
		return postList.get((i));
	}

	public static int getSize() {
		return postList.size();
	}

	public static int getIndex(Post post) {
		return postList.indexOf(post);
	}

	public static void initBoard() {

		String tempString;
		String[] tempArr;
		try {
			if (!FOLDER.exists()) {
				FOLDER.mkdir();
			}
			if (!LISTFILE.exists()) {
				LISTFILE.createNewFile();
			}
		} catch (Exception e) {
			System.out.println("파일생성에 실패하였습니다.");
		}
		try (BufferedReader br = new BufferedReader(new FileReader(LISTFILE))) {
			while (true) {
				tempString = br.readLine();
				if (tempString == null) {
					break;
				}
				tempArr = tempString.split(" ");
				postList.add(new Post(tempArr[0], tempArr[1], tempArr[2], tempArr[3]));
			}
		} catch (Exception e) {
			System.out.println("파일리스트를 불러오는것을 실패하였습니다.");
		}
	}

}
