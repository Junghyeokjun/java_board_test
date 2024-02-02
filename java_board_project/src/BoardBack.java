import java.util.ArrayList;
import java.util.List;

public class BoardBack {
	private static List<Post> postList = new ArrayList<>();
	public static void add(Post post) {
		postList.add(post);
	}
	public static void delete(int i) {
		postList.remove(i-1);
	}
	public static String getSimplePost (int i) {
		return postList.get(i-1).getSimplePost();
	}
	public static String getPost (int i) {
		return postList.get(i-1).getPost();
	}
	
}
