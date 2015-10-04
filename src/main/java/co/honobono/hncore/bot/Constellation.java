package co.honobono.hncore.bot;

public enum Constellation {
	ARIES(1),
	TAURUS(2),
	GEMINI(3),
	CANCER(4),
	LEO(5),
	VIRGO(6),
	LIBRA(7),
	SCORPIO(8),
	SAGITTARIUS(9),
	CAPRICORN(10),
	AQUARIUS(11),
	PISCES(12);

	private final int id;

	private Constellation(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Constellation getConstellation(int id) {
		for(Constellation c : values()) {
			if(c.getId() == id) {
				return c;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}
}
