package model;

public class Player {
	private String name;
	private Integer age;
	private String team;
	private Double ts;
	private Double ftr;
	private Double trb;
	private Double ast;
	private Double stl;
	private Double[] stats;
	
	public Player(String name, Integer age, String team, Double ts, Double ftr, Double trb, Double ast, Double stl) {
		super();
		this.name = name;
		this.age = age;
		this.team = team;
		this.ts = ts;
		this.ftr = ftr;
		this.trb = trb;
		this.ast = ast;
		this.stl = stl;
		Double[] s = {ts,ftr,trb,ast,stl};
		stats = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Double getTs() {
		return ts;
	}

	public void setTs(Double ts) {
		this.ts = ts;
	}

	public Double getFtr() {
		return ftr;
	}

	public void setFtr(Double ftr) {
		this.ftr = ftr;
	}

	public Double getTrb() {
		return trb;
	}

	public void setTrb(Double trb) {
		this.trb = trb;
	}

	public Double getAst() {
		return ast;
	}

	public void setAst(Double ast) {
		this.ast = ast;
	}

	public Double getStl() {
		return stl;
	}

	public void setStl(Double stl) {
		this.stl = stl;
	}
	
	public String toString() {
		return name;
	}

	public Double[] getStats() {
		return stats;
	}

	public void setStats(Double[] stats) {
		this.stats = stats;
	}
}
