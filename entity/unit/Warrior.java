package entity.unit;

import java.util.ArrayList;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int DAMAGE = 2;
	
	public Warrior(int xPos, int yPos, String name, int health,  int movementRange) {
		super(xPos, yPos, name, health, DAMAGE, movementRange, 
				new ArrayList<Ability>(){
				private static final long serialVersionUID = 1L;
					{
					add(new Melee());
					} 
				}  );

	}

}
