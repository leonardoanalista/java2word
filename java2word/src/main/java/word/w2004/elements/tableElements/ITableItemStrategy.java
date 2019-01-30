package word.w2004.elements.tableElements;


/**
 * @author leonardo_correa
 * Strategy Design Pattern
 */
public interface ITableItemStrategy{
	public String getTop();
	public String getTop(int cols);
	public String getMiddle();
	public String getBottom();
}
