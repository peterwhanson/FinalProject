package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

import org.eclipse.swt.graphics.Image;

import client.Player;
import GameController.Pokemon;

//Created Map class hopefully in right folder ....Test comment PWH

public abstract class Map implements Serializable{
	
	
	
	private Tile[][] grid;
	private Image img; //The background image of the map, determined by mapType (did I import the right one?)
	private LinkedList<Point> enemyPath; //A list of all of the tile coordinates that the
	                                     //enemies will attempt to pass through
	private Point firstPathTile; //The path tile on which enemies spawn
	private Point lastPathTile; //The path tile on which enemies stop and do damage to health
	private int currentEnemies; //The current total amount of enemies on the map (necessary?)
	private String mapType; //A description of the map level, can be used for theme differentiation
	private int mapTypeCode; //A code # to differentiate each level
	private Player player; //The associated player object for this map
	
	public Map(Tile[][] gridDimensions, LinkedList<Point> path, String mapType, Image background, int mapTypeCode, Player player){
		grid = gridDimensions;
		enemyPath = path;
		this.mapType = mapType;
		img = background;
		this.mapTypeCode = mapTypeCode;
		this.player = player;
		currentEnemies = 0;
		setPath();
		setTilesMap();
	}
	
	private void setTilesMap() {
		for(int r = 0; r < grid.length; r++){
			for(int c = 0; c < grid[r].length; c++){
				grid[r][c].setMap(this);
			}
		}
		
	}

	private void setPath(){
		Point tempCoords;
		for(int i = 0; i < enemyPath.size(); i++){
			tempCoords = enemyPath.get(i);
			grid[tempCoords.y][tempCoords.x].setAsPath();
			if(i == 0){
				grid[tempCoords.y][tempCoords.x].setFirstPathTile();
				firstPathTile = tempCoords;
			}
			if(i == enemyPath.size() - 1){
				grid[tempCoords.y][tempCoords.x].setLastPathTile();
				lastPathTile = tempCoords;
			}
			
		}
	}
		
	public boolean spawnEnemy(Pokemon enemy){
		grid[firstPathTile.y][firstPathTile.x].addPokemon(enemy);
		enemy.setLocation(firstPathTile);
		currentEnemies++;
		//Give Pokemon a Map instance variable and set it here
		
		
		//Also may need to do other things here for GUI/threads, ...
		
		return true;
	}
	
	public boolean updateEnemyPosition(Pokemon enemy){
		
		//get enemy's current coordinates, determine what his next coordinates will be
		Point enemyCoords = enemy.getLocation();
		int i = enemyPath.indexOf(enemyCoords);
		Point nextCoords = enemyPath.get(++i);
		
		//remove enemy from current tile and add him to the next one
		grid[enemyCoords.y][enemyCoords.x].removePokemon(enemy);
		grid[nextCoords.y][nextCoords.x].addPokemon(enemy);
		
		return true;
	}
	
	public void lostHealth(int hpLost){
		player.loseHealth(hpLost);
	}
	
	
	
	
	
		
}