package entity.building;

import java.util.ArrayList;

import core.Point2DNoFxReq;
import entity.unit.Unit;
import events.abilities.Ability;
import events.abilities.CreateUnit;

public class ProductionBuilding extends Building {
	
	public ProductionBuilding(Point2DNoFxReq pointXY, String name, int maxHealth, int currentHealth, int level, boolean controlable, ArrayList<Ability> abilities) {
		super(pointXY, name, maxHealth, currentHealth, level, controlable, abilities);
		if(name.equals(Building.TOWN_CENTER)) {
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_BUILDER, Ability.ABILITY_CREATE_BUILDER, Ability.ABILITY_DESC_CREATE_BUILDER, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_HERO, Ability.ABILITY_CREATE_HERO, Ability.ABILITY_DESC_CREATE_HERO, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_DRAGON, Ability.ABILITY_CREATE_DRAGON, Ability.ABILITY_DESC_CREATE_DRAGON, controlable));
		}
		if(name.equals(Building.BARRACKS)) {
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_WARRIOR, Ability.ABILITY_CREATE_WARRIOR, Ability.ABILITY_DESC_CREATE_WARRIOR, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_ARCHER, Ability.ABILITY_CREATE_ARCHER, Ability.ABILITY_DESC_CREATE_ARCHER, controlable));
		}
		if(name.equals(Building.SIEGE_WORKSHOP)) {
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_TREBUCHET, Ability.ABILITY_CREATE_TREBUCHET, Ability.ABILITY_DESC_CREATE_TREBUCHET, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_MANGONEL, Ability.ABILITY_CREATE_MANGONEL, Ability.ABILITY_DESC_CREATE_MANGONEL, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_BATTERING_RAM, Ability.ABILITY_CREATE_BATTERING_RAM, Ability.ABILITY_DESC_CREATE_BATTERING_RAM, controlable));
		}
		if(name.equals(Building.PORTAL)) {
			//-
		}
		if(name.equals(Building.STABLE)) {
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_KNIGHT, Ability.ABILITY_CREATE_KNIGHT, Ability.ABILITY_DESC_CREATE_KNIGHT, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_CAVALRY, Ability.ABILITY_CREATE_CAVALRY, Ability.ABILITY_DESC_CREATE_CAVALRY, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_CAVALRY_ARCHER, Ability.ABILITY_CREATE_CAVALRY_ARCHER, Ability.ABILITY_DESC_CREATE_CAVALRY_ARCHER, controlable));
		}
		if(name.equals(Building.CHURCH)) {
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_MAGE, Ability.ABILITY_CREATE_MAGE, Ability.ABILITY_DESC_CREATE_MAGE, controlable));
			abilities.add(new CreateUnit(pointXY, Unit.UNIT_PRIEST, Ability.ABILITY_CREATE_PRIEST, Ability.ABILITY_DESC_CREATE_PRIEST, controlable));
		}
	}
	
}
