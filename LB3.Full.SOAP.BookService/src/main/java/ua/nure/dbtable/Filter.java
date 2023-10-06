package ua.nure.dbtable;

public interface Filter<T> {
	/**
	 * Tests whether or not the specified item should be
     * included in a list.
	 * @param item - item to include or not
	 * @param pattern - searched pattern
     * 
	 * @return
	 */
	 <K> boolean accept(T item, K pattern);
}
