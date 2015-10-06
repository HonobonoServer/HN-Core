package co.honobono.hncore.bot;

import java.time.LocalDate;

public enum Constellation {
	ARIES(1, "牡羊座"),
	TAURUS(2, "牡牛座"),
	GEMINI(3, "双子座"),
	CANCER(4, "蟹座"),
	LEO(5, "獅子座"),
	VIRGO(6, "乙女座"),
	LIBRA(7, "天秤座"),
	SCORPIO(8, "蠍座"),
	SAGITTARIUS(9, "射手座"),
	CAPRICORN(10, "山羊座"),
	AQUARIUS(11, "水瓶座"),
	PISCES(12, "魚座"),
	NONE(13, "なし");

	private final int id;
	private final String JPName;

	private Constellation(final int id, final String JPName) {
		this.id = id;
		this.JPName = JPName;
	}

	public int getId() {
		return id;
	}

	public String getJPName() {
		return JPName;
	}

	public static Constellation getConstellation(int id) {
		for(Constellation c : values()) {
			if(c.getId() == id) {
				return c;
			}
		}
		throw new IllegalArgumentException("no such enum object for the id: " + id);
	}

	public static Constellation getConstellation(String JPName) {
		for(Constellation c : values()) {
			if(c.getJPName() == JPName) {
				return c;
			}
		}
		throw new IllegalArgumentException("no such enum object for the Name: " + JPName);
	}

	public static Constellation getConstellation(LocalDate BirthDay) {
		int mouth = BirthDay.getMonthValue();
		int day = BirthDay.getDayOfMonth();
		switch(mouth) {
		case 1:
			if(19 >= day) {
				return Constellation.CAPRICORN;
			}
			return Constellation.AQUARIUS;
		case 2:
			if(20 >= day) {
				return Constellation.AQUARIUS;
			}
			return Constellation.PISCES;
		case 3:
			if(21 >= day) {
				return Constellation.PISCES;
			}
			return Constellation.ARIES;
		case 4:
			if(22 >= day) {
				return Constellation.ARIES;
			}
			return Constellation.TAURUS;
		case 5:
			if(20 >= day) {
				return Constellation.TAURUS;
			}
			return Constellation.GEMINI;
		case 6:
			if(21 >= day) {
				return Constellation.GEMINI;
			}
			return Constellation.CANCER;
		case 7:
			if(22 >= day) {
				return Constellation.CANCER;
			}
			return Constellation.LEO;
		case 8:
			if(23 >= day) {
				return Constellation.LEO;
			}
			return Constellation.VIRGO;
		case 9:
			if(23 >= day) {
				 return Constellation.VIRGO;
			}
			return Constellation.LIBRA;
		case 10:
			if(23 >= day) {
				return Constellation.LIBRA;
			}
			return Constellation.SCORPIO;
		case 11:
			if(23 >= day) {
				return Constellation.SCORPIO;
			}
			return Constellation.SAGITTARIUS;
		case 12:
			if(23 >= day) {
				return Constellation.SAGITTARIUS;
			}
			return Constellation.CAPRICORN;
		}
		throw new IllegalArgumentException("out of bounds enum object for the type: " + BirthDay.toString());
	}
}
