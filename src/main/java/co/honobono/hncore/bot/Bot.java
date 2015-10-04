package co.honobono.hncore.bot;

import java.time.LocalDate;

import org.bukkit.entity.Player;

public class Bot {
	private Player player = null;
	private String context = "";
	private String NickName = "";
	private String NickNameY = "";
	private Sex sex = null;
	private BloodType bloodtype = null;
	private LocalDate birthday = null;
	private int age = 0;
	private Constellation constellation = null;
	private String place = "東京";
	private Mode mode = null;

	public Bot(Player player, String context, String NickName, String NickNameY, Sex sex, BloodType bloodtype,
			LocalDate birthday, int age, Constellation constellation, Mode mode) {
		this.player = player;
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

	public Player getPlayer() {
		return player;
	}

	public String getContext() {
		return context;
	}

	public String getNickName() {
		return NickName;
	}

	public String getNickNameY() {
		return NickNameY;
	}

	public Sex getSex() {
		return sex;
	}

	public BloodType getBloodType() {
		return bloodtype;
	}

	public LocalDate getBirthDay() {
		return birthday;
	}

	public int getAge() {
		return age;
	}

	public Constellation getConstellation() {
		return constellation;
	}

	public String getPlace() {
		return place;
	}

	public Mode getMode() {
		return mode;
	}
}
