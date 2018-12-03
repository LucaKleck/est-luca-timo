package entity.unit;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import abilities.Ability;
import abilities.Melee;

public class Warrior extends Unit {

	private static final int BASE_MAX_HEALTH = 3;
	private static final int BASE_DAMAGE = 2;
	private static final int MOVEMENT_RANGE = 2;
	
	@SuppressWarnings("serial")
	public Warrior(Point2D pointXY, String name, int currentHealth, int level, boolean controlable) {
		super(pointXY, name, BASE_MAX_HEALTH, currentHealth, level, controlable, BASE_DAMAGE, MOVEMENT_RANGE, 
				new ArrayList<Ability>(){
					{
					add(new Melee());
					} 
				}  );
	}

}
