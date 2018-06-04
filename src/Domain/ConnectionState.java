package Domain;

public interface ConnectionState {
	public static final String HASH_VALUE = "#";
	public void dial(String key);
	public void record(String voice);
	public void hangUp();
}
