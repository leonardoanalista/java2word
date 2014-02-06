package word.w2004.elements;

import word.api.interfaces.IElement;
import word.api.interfaces.IFluentElement;

/**
 * Breaks lines like when you press enter in your MS Word.
 * You can insert many Breaklines at once. Eg.:
 * <code>
 * new BreakLine(3)
 * </code>
 * This will insert 3 Breaklines.
 * FOR A MATTER OR EXERCICE, THIS CLASS FOLLOWS ALL THOSE CRASY CHECKSTYLE GUIDELINES.
 */
public class BreakLine implements IElement, IFluentElement<BreakLine> {
    /**Number of repetitions.*/
    private int times = 1;

    @Override
    public final String getContent() {
        StringBuilder res = new StringBuilder("");
        applyBreakLineTimes(res);
        return res.toString();
    }

    /** Apply the repetition of break lines.
     * @param res string to be added content
     */
    private void applyBreakLineTimes(final StringBuilder res) {
        for (int i = 0; i < times; i++) {
            res.append("\n<w:p wsp:rsidR=\"008979E8\" wsp:rsidRDefault=\"008979E8\"/>");
        }
    }

    /**
     * constructor: Number of break lines you want to add.
     * @param times number of breaklines
     */
    public BreakLine(final int times) {
        this.times = times;
    }

    /** constructor: By default, 1 Number of break line when no number is provided.
     * @param times
     */
    public BreakLine() {
    }

    /** Created Breaklines according to the number of times provided.
     * @param value number of times
     * @return the Breakline object ready to go!
     */
    public static BreakLine times(final Integer value) {
        return new BreakLine(value);
    }

    /** returns the Breakline object.
     * @return the object breakline
     */
    @Override
    public final BreakLine create() {
        return this;
    }

}
