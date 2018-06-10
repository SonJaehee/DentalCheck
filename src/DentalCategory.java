
public class DentalCategory {
	private int category;	// ���þȵǸ� 0, ������ 1, ������ 2, 4��° ������ 4
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

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
