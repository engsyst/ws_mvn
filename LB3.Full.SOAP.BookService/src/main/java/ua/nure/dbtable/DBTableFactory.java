package ua.nure.dbtable;

public abstract class DBTableFactory {
	protected DBTableFactory() {
		// let descendants use it 
	}
	
	private static DBTable<?> instance;
	
	@SuppressWarnings("unchecked")
	public static <T> DBTable<T> instance() {
		if (instance == null) {
			instance = new DBTableInMemory<>();
		}
		return (DBTable<T>) instance;
	}
}
