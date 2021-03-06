package pjg.Bots;
import robocode.*;
import java.awt.Color;
import robocode.ScannedRobotEvent;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * OneBot - a robot by pTricKg
 */
public class OneBot extends Robot
{
		
		int turnDirection = 1;
		
	/**
	 * run: OneBot's default behavior
	 */
	public void run() {
		
		setColors(Color.green,Color.blue,Color.green); // body,gun,radar
		
		// Robot main loop
		while(true) {
			
			//ahead(100);
			
			turnRight(5 * turnDirection);
			fire(15);
			//turnRight(45);
			//back(100);
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
	
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
	
		turnRight(e.getBearing());
		ahead(e.getDistance() + 5);
		scan(); // Might want to move ahead again!
		fire(15);
	}

	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
		}
		
		turnRight(e.getBearing());
		fire(15);
		

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
		if (e.getEnergy() > 16) {
			fire(3);
		} else if (e.getEnergy() > 10) {
			fire(2);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		fire(15);
		ahead(40); // Ram him again!
		
		}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
				
		fire(15);
		back(50);
		
	}

	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(5);
		back(20);
	}	
}
								
