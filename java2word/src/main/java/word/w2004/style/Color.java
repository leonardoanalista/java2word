package word.w2004.style;

public enum Color { 
	YELLOW("FFFF00"), BLACK("000000"), RED("FF0000"), BLUE("0000FF"), GREEN("008000");
	private String value;
	Color(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}