package model;

public class Player {
	private String name;
	private Integer age;
	private String team;
	private Double[] stats;
	
	public Player(String n, Integer a, String t, Double[] s) {
		name = n;
		age = a;
		team = t;
		stats = s;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}
	public String getTeam() {
		return team;
	}

	public Double[] getStats() {
		return stats;
	}
}
