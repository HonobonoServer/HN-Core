package co.honobono.hncore.bot;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Bot implements ConfigurationSerializable {
	private String context = "";
	private String NickName = "";
	private String NickNameY = "";
	private Sex sex = Sex.NONE;
	private BloodType bloodtype = BloodType.NONE;
	private LocalDate birthday = null;
	private int age = 0;
	private Constellation constellation = Constellation.NONE;
	private String place = "";
	private Mode mode = Mode.DIALOG;

	public Bot() {

	}

	/*
	public Bot(String context, String NickName, String NickNameY, Sex sex, BloodType bloodtype, LocalDate birthday,
			int age, Constellation constellation, Mode mode) {
		this.context = context;
		this.NickName = NickName;
		this.NickNameY = NickNameY;
		this.sex = sex;
		this.bloodtype = bloodtype;
		this.birthday = birthday;
		this.age = age;
		this.constellation = constellation;
		this.mode = mode;
	}
	*/

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String NickName) {
		this.NickName = NickName;
	}

	public String getNickNameY() {
		return NickNameY;
	}

	public void setNickNameY(String NickNameY) {
		this.NickNameY = NickNameY;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public BloodType getBloodType() {
		return bloodtype;
	}

	public void setBloodType(BloodType bloodtype) {
		this.bloodtype = bloodtype;
	}

	public LocalDate getBirthDay() {
		return birthday;
	}

	public void setBirthDay(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Constellation getConstellation() {
		return constellation;
	}

	public void setConstellation(Constellation constellation) {
		this.constellation = constellation;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Context", this.context);
		map.put("NickName", this.NickName);
		map.put("NickNameY", this.NickNameY);
		map.put("Sex", this.sex.getId());
		map.put("BloodType", this.bloodtype.getId());
		map.put("BirthDay", this.birthday);
		map.put("Age", this.age);
		map.put("Constellation", this.constellation.getId());
		map.put("Place", this.place);
		map.put("Mode", this.mode.getId());
		return map;
	}

	public static Bot deserialize(Map<String, Object> map) {
		Bot bot = new Bot();
		bot.setContext(map.get("Contect").toString());
		bot.setNickName(map.get("NickName").toString());
		bot.setNickNameY(map.get("NickNameY").toString());
		bot.setSex(Sex.getSex(Integer.valueOf(map.get("Sex").toString())));
		bot.setBloodType(BloodType.getBloodType(Integer.valueOf(map.get("BloodType").toString())));
		String[] l = map.get("BirthDay").toString().split("-");
		LocalDate lo = LocalDate.of(Integer.valueOf(l[0]), Integer.valueOf(l[1]), Integer.valueOf(l[2]));
		bot.setBirthDay(lo);
		bot.setAge(Integer.valueOf(map.get("Age").toString()));
		bot.setConstellation(Constellation.getConstellation(Integer.valueOf(map.get("Constellation").toString())));
		bot.setPlace(map.get("Place").toString());
		bot.setMode(Mode.getMode(Integer.valueOf(map.get("Mode").toString())));
		return bot;
	}
}
