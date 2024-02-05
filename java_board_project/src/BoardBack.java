import java.util.ArrayList;
import java.util.List;

public class BoardBack {
	private static List<Post> postList = new ArrayList<>();

	public static boolean add(Post post) {
		try {
			postList.add(post);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static void delete(int i) {
		postList.remove(i - 1);
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
	

}
