package entity.unit;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int BASE_MAX_HEALTH = 2;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	
	@SuppressWarnings("serial")
	public Warrior(int xPos, int yPos, String name, int currentHealth, int level) {
		super(xPos, yPos, name, BASE_MAX_HEALTH, currentHealth, level, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}
	
	@SuppressWarnings("serial")
	public Warrior(Point pointXY, String name, int currentHealth, int level) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}

}
