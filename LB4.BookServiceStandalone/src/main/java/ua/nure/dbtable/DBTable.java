package ua.nure.dbtable;

import java.sql.SQLException;
import java.util.Collection;

public interface DBTable<T> {
	/**
	 * Add a object to table 
	 * 
	 * @param object
	 * @return id of object
	 * @throws DAOException 
	 */
	public int insert(T item);

	/**
	 * Delete object from table 
	 * 
	 * @param object
	 * @return
	 * @throws SQLException 
	 */
	public T delete(int id) throws SQLException;
	
	/**
	 * Delete all instance of object from table 
	 * 
	 * @param object
	 * @return
	 * @throws DAOException 
	 */
	public void delete(Object item, Filter filter);
	
	/**
	 * Add a object to table 
	 * 
	 * @param object
	 * @return
	 * @throws SQLException 
	 */
	public boolean update(int id, T item) throws SQLException;
	
	/**
	 * Find object with patter in the table 
	 * 
	 * @param name
	 * @return
	 */
	public Collection<T> filter(Object pattern, Filter filter);

	/**
	 * 
	 * @return All objects from table
	 */
	public Collection<T> selectAll();

	public T get(int id) throws SQLException;
	
	public void clear();
	
	public int size();

	public int[] insert(T... item);

}
