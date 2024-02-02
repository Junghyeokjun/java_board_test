public class Board {
	private final static String monitor0 = "=================================";
	private final static String monitor1 = "번호  제목  작성자         작성일                             ";
	private final static String monitor2 = "---------------------------------";
	private final static String monitor3 = "1.목록	2.등록	3.내용	4.삭제    0.종료>";
	private final static String monitor4 = "번호  제목  내용  작성자         작성일                      ";
	private final static String monitor5 = "위의 게시판 번호중 하나를 넣어주세요>>";
	private final static String monitor6 = "글이 정상적으로 추가되었습니다";


	// 초기
	public static String monitor() {

		return monitor0 + "\n" + monitor1 + "\n" + monitor2 + "\n" + monitor3;
	}
	// 목록
	public static String Monitor() {

		return monitor0 + "\n" + monitor1 + "\n" + monitor4;
	}

	public static String detailMonitor2() {

		return monitor3;
	}
	// 등록
	public static String addMonitor() {

		return monitor6 + "\n" + monitor3;
	}

	// 내용
	public static String deletetMonitor() {

		return monitor0 + "\n" + monitor1 + "\n" + monitor2;
	}

	public static String deleteMonitor2() {

		return monitor5;
	}
	//삭제
	public static String deleteMonitor() {

		return monitor0 + "\n" + monitor1 + "\n" + monitor2;
	}

	public static String deleteMonitor2() {

		return monitor5;
	}

	// 글 선택
	public static String detailMonitor() {

		return monitor0 + "\n" + monitor1 + "\n" + monitor4;
	}

	public static String detailMonitor2() {

		return monitor3;
	}

}
