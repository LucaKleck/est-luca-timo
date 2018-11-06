package entity;

public abstract class Ability {
	String name;
	
	public Ability(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
