package co.honobono.hncore.bot;

public enum Sex {
    MALE(1),
    FEMALE(2),
    NONE(3);

	private final int id;

	private Sex(final int id) {
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public static Sex getSex(int id) {
		for(Sex s : values()) {
			if(s.getId() == id) {
				return s;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}
}