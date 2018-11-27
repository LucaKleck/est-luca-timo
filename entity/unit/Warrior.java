package entity.unit;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int MAX_HEALTH = 2;
	private static final int DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	
	@SuppressWarnings("serial")
	public Warrior(int xPos, int yPos, String name) {
		super(xPos, yPos, name, MAX_HEALTH, DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}
	
	@SuppressWarnings("serial")
	public Warrior(Point pointXY, String name) {
		super(pointXY, name, MAX_HEALTH, DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}

}
