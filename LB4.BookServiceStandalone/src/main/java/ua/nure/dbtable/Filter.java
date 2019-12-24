package ua.nure.dbtable;

public interface Filter {
	/**
	 * Tests whether or not the specified item should be
     * included in a list.
     * 
	 * @param pattern - searched pattern
	 * @param item - item to include or not
	 * @return
	 */
	boolean accept(Object pattern, Object item);
}
