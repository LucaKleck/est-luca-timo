package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int MAX_HEALTH = 2;
	private static final int DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	
	public Warrior(int xPos, int yPos, String name) {
		super(xPos, yPos, name, MAX_HEALTH, DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
				private static final long serialVersionUID = 1L;
					{
					add(new Melee());
					} 
				}  );

	}

}
