package abilities;

public abstract class Ability implements Runnable {
	String name;

	public Ability(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		
	}
}
