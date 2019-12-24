package ua.nure.dbtable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import ua.nure.log.Log;

class DBTableInMemory<T> implements DBTable<T> {
	private ConcurrentHashMap<Integer, T> items = new ConcurrentHashMap<>();
	private int index;
	
	/**
	 * Set Log.DEBUG as first parameter to see log messages
	 */
	private final Log log = Log.getInstance(Log.DEBUG, DBTableInMemory.class);

	@Override
	public int insert(T item) {
		if (item == null) {
			log.error("item is null");
			throw new IllegalArgumentException("T can not be a null");
		}
		items.put(++index, item);
		log.debug("T added -->" + item);
		return index;
	}

	@Override
	public int[] insert(T... items) {
		int[] ids = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			ids[i] = insert(items[i]);
		}
		return ids;
	}
	
	@Override
	public T delete(int id) throws SQLException {
		T t = items.remove(id);
		if (t == null) {
			throw new SQLException("Not exist with id --> " + id);
		}
		log.debug("Removed --> " + t);
		return t;
	}

	@Override
	public void delete(Object item, Filter filter) {
		for (Entry<Integer, T> entry : items.entrySet()) {
			if (filter.accept(item, entry.getValue())) {
				log.debug("Accepted to remove --> " + entry.getValue());
				boolean res = items.remove(entry.getKey(), entry.getValue());
				log.debug("IsRemoved --> " + res);
			}
		}
	}

	@Override
	public boolean update(int id, T item) throws SQLException {
		if (item == null) {
			log.debug("Not found. Id --> " + id);
			throw new NullPointerException("Item is null");
		}
		T o = items.get(id);
		if (o == null) {
			log.debug("Not found. Id --> " + id);
			throw new SQLException("Not found");
		}
		T res = items.put(id, item);
		log.debug("Found. Updated --> " + res);
		return true;
	}

	@Override
	public Collection<T> filter(Object pattern, Filter filter) {
		ArrayList<T> found = new ArrayList<T>();
		for (T item : items.values()) {
			if (filter.accept(pattern, item)) {
				log.debug("Found --> " + item);
				found.add(item);
			}
		}
		return found;
	}

	@Override
	public Collection<T> selectAll() {
		log.debug("selectAll() values --> " + items.values());
		return items.values();
	}

	@Override
	public T get(int id) throws SQLException {
		T b = items.get(id);
		if (b == null) {
			log.debug("Not found --> " + id);
			throw new SQLException("Not found id = " + id);
		}
		log.debug("Found --> " + id);
		return b;
	}

	@Override
	public void clear() {
		items = new ConcurrentHashMap<>();
		index = 0;
	}

	@Override
	public int size() {
		return items.size();
	}

}
