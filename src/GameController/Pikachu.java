package GameController;

//package src.model;

import GameController.Enemy;
import model.Map;

/**
 * THis is the first concrete class for our pokemon.  It inherits from Pokemon.java
 * and contains nearly all of the methods to be used
 * 
 * Not to much to report here.
 * @author Max Justice
 *
 */
public class Pikachu extends Enemy{

	/**
	 * The constructor for Pokemon it takes the following variables
	 * @param health for the initial state of the pokemons health
	 * @param attackPower for the attack power should we use attacks to take player health
	 * @param defense The defense modifier.  It takes the attack incoming minus the defense and subtracts from health
	 * @param speed The number (or fraction) of tiles the enemy moves through each second
	 * @param name the name of the monster
	 * @param worth the worth of the monster as it is created
	 * @param Image
	 *
	 *public Pokemon (int health, int attackPower, int defense, int speed, String name, int worth, String Image, Map map)
	 */
	
	public Pikachu(Map currentMap) {
		super(100, 10, 12, 1.0, "Pikachu", 25, "src/images/pikachuStatic.png", currentMap);
	}

	
}
