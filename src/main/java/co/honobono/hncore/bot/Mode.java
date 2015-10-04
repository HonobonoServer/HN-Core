package co.honobono.hncore.bot;

public enum Mode {
	DIALOG(1, "dialog"),
	SRTR(2, "srtr");

	private final int id;
	private final String name;

	private Mode(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Mode getMode(int id) {
		for(Mode m : values()) {
			if(m.getId() == id) {
				return m;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}

	public Mode getMode(String name) {
		for(Mode m : values()) {
			if(m.getName().equals(name)) {
				return m;
			}
		}
		throw new IllegalArgumentException("no such enum object for the name: " + name);
	}
}
