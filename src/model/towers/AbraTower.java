package model.towers;

import java.util.ArrayList;
import java.util.Random;

import model.enemies.Enemy;
import GUI.GameView.towerType;

public class AbraTower extends Tower{

	private static final long serialVersionUID = -4124433768315956201L;
	
	private String level1= "src/images/tower6Level1.png";
	private String level2= "src/images/tower6Level2.png";
	private String level3= "src/images/tower6level3.png";
	
	public static final int Cost = 150;
	private final int costOfUpgrade1 = 130;
	private final int costOfUpgrade2 = 720;
	private final int maxLevelAttainable = 3;
	//String Name, int Attack, int Radius, int FireRateSec, String PlayersName
	/**
	 * The default settings for the charmander gym a attack power of 25, a radius range of 3 tiles,
	 * 3 shots per a second and the Image URL to the gym
	 * @param PlayersName is the only value the gym 
	 */
	public AbraTower(){
		/* String Name, int Attack, int Radius, double FireRateSec, String PlayersName,
					String Image, int cost */
		super("Abra", 27, 2, 1.0, "src/images/tower6Level1.png", Cost);	
		 setTowerType(towerType.PSYCHIC);
		// TODO Auto-generated constructor stub
	}

	/**
	 * (non-Javadoc)
	 * @see model.towers.Tower#attackEnemy(java.util.ArrayList)
	 * Attack Enemy algorithm is simply.  It is passed the arraylist
	 * of current enemies on the board that are alive.  It then runs a 
	 * for loop for each enemy getting their current point on the board
	 * checks if that enemy is within range with canAttackEnemy() method which
	 * returns true if it can be attacked.  It then uses the incomingAttack()
	 * method from the pokemon object and subtracts from the current pokemons
	 * instance of its health.  
	 * 
	 * TODO add the images and sprites for the attack.  Update the list of
	 * when a pokemon dies to the listeners or observers.  If a pokemon faints
	 * add currency to the player.
	 */
	 
	 //This method will be called by map every tick() as long as tower is not on cooldown from last attack and passed
	 //a list of all enemies currently on the map
	@Override
	public boolean attackEnemy(ArrayList<Enemy> enemies) {
		
		Enemy target;
		
		target = super.findFarthestEnemyInRange(enemies);
		
		if (target == null){
			return false;
		}
			
			target.incomingAttack(super.getAttackPower());
			
			if ( super.getCurrentLevel() == 1)
			{
				Random r1 = new Random();
				int chanceOfEffect1 = r1.nextInt(100);
				if (chanceOfEffect1 < 5)
					target.teleportToBeginning();
			}
			else if( super.getCurrentLevel() == 2)
			{
					Random r2 = new Random();
					int chanceOfEffect2 = r2.nextInt(100);
					if (chanceOfEffect2 < 7)
						target.teleportToBeginning();
			}
				else if(super.getCurrentLevel() == 3)
				{
					Random r3 = new Random();
					int chanceOfEffect3 = r3.nextInt(100);
					if (chanceOfEffect3 < 7)
							target.teleportToBeginning();
				}	
			
		getMap().notifyOfAttack(this.getType(), this.getPosition(), target.getLocation());
				
		return true;
	}

	// This sets modifers when we figure it out
	@Override
	public boolean setModifer() {
		// TODO Auto-generated method stub
		return false;
	}

	// This method is for later time when we figure out modifiers
	@Override
	public boolean getModifer() {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean levelUp() {
		if (super.getCurrentLevel() == 2){
			super.setImageURL(level2);
			super.setPokemonName("Kadabra");
			this.gainAttackPower(8); 	// increase attack power by 8 points
			this.increaseRange(0);// increase attack radius by none
			this.increaseFireRate(0.15); 	// increase the fire rate
		}else if (super.getCurrentLevel() == 3){
			super.setImageURL(level3);
			super.setPokemonName("Alakazam");
			this.gainAttackPower(10); 	// increase attack power by 10 points
			this.increaseRange(1);// increase attack radius by 1
			this.increaseFireRate(0.15); 	// increase the fire rate
		}
		return true;
	}

	@Override
	public String printTowerStats() {
		
		String stats = new String ("Name: "+ getTowerName() + "\n" +
									"Attack: " + getAttackPower() + "\n"+
									"Rate of Fire: "+ getFireRate() + "\n" +
									"Cost: " + getCost() + "\n"+
									"Modifier: " + getModifer() + "\n"
									);
		return stats;
	}

	@Override
	public int getCostOfLevelingUp() {
		if (super.getCurrentLevel() == 1)
			return this.costOfUpgrade1;
		return this.costOfUpgrade2;
	}

	@Override
	public boolean upgradeCurrentTower(int playersCoins) {
		if ( getCostOfLevelingUp() <= playersCoins && super.getCurrentLevel() < maxLevelAttainable){
			levelUp();
			return true;
		}
		return false;
	}

}
