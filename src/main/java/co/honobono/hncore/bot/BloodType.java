package co.honobono.hncore.bot;

public enum BloodType {
	A(1),
	B(2),
	O(3),
	AB(4),
	NONE(5);

	private final int id;

	private BloodType(final int id) {
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public static BloodType getBloodType(int id) {
		for(BloodType b : values()) {
			if(b.getId() == id) {
				return b;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}
}
