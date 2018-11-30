package entity.unit;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	
	@SuppressWarnings("serial")
	public Warrior(int xPos, int yPos, String name, int currentHealth, int level, boolean controlable) {
		super(xPos, yPos, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}
	
	@SuppressWarnings("serial")
	public Warrior(Point pointXY, String name, int currentHealth, int level, boolean controlable) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}

	/*@SuppressWarnings("serial")
	public Warrior(Point pointXY, String name) {
		super(pointXY, name, BASE_MAX_HEALTH, BASE_MAX_HEALTH, 1, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
						add(new Melee());
						add(new Move());
					} 
				}  );
	}*/

}
