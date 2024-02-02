import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
	private String title;
	private String detail;
	private String name;
	private String time;

	public Post(String title, String detail, String name) {
		LocalDateTime now = LocalDateTime.now();
		
		this.setTitle(title);
		this.setDetail(detail);
		this.setName(name);
		this.setTime(now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")));
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public String getSimplePost() {
		return this.getTitle()+" "+this.getName()+" "+this.getTime();
	}
	public String getPost() {
		return this.getTitle()+" "+this.getDetail()+" "+this.getName()+" "+this.getTime();
	}
}
