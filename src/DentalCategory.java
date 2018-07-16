import java.io.Serializable;

import javax.swing.JTextField;

public class DentalCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int category;	// 선택안되면 0, 없음은 1, 있음은 2, 4번째 라디오는 4
	private int top;
	private int bottom;
	
	private String reason;
	
	public DentalCategory(int category) {
		this.category = category;
	}
	
	public DentalCategory(int category, int top, int bottom) {
		this(category);
		this.top = top;
		this.bottom = bottom;
	}
	
	public DentalCategory(int category, String reason) {
		this(category);
		this.reason = reason;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getTop() {
		return top;
	}

	public String getTopToString() {
		return Integer.toString(top);
	}
	
	public void setTop(int top) {
		this.top = top;
	}

	public void setTop(JTextField top) {
		this.top = Integer.parseInt(top.getText());
	}
	
	public int getBottom() {
		return bottom;
	}

	public String getBottomToString() {
		return Integer.toString(bottom);
	}
	
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public void setBottom(JTextField bottom) {
		this.bottom = Integer.parseInt(bottom.getText());
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
