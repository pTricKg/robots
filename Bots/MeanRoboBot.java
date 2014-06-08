package pjg.Bots;
import robocode.*;
import robocode.Robot;

import java.awt.Color;
import robocode.ScannedRobotEvent;

import robocode.WinEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * MeanRoboBot - a robot by pTricKg
 */
public class MeanRoboBot extends AdvancedRobot
{
	int turnDirection = 1;
	
	public void run() {
		// Initialization of the robot should be put here
		
		setColors(Color.black,Color.red,Color.green); // body,gun,radar

	// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			
			//
			
			ahead(10);
			turnGunRight(10);
			}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		//turnGunLeft(e.getBearing());
		// Lock on to our target (I hope...)
		//setTurnRadarRight(e.getBearing());
		// Lock on to our target (this time for sure)
		//setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
		
	
		/*
		 * 	if (e.getBearing() >= 0) {
			turnDirection = 1;
		} else {
			turnDirection = -1;
			
		}
	
		turnRight(e.getBearing());
		ahead(e.getDistance() + 5);
		scan(); // Might want to move ahead again!
		fire(15);
		 */
		
		double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngleDegrees(absoluteBearing - getGunHeading());

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
	}

		public void onHitRobot(HitRobotEvent e) {
	
		if (e.getBearing() >= 0) {
			turnDirection = 25;
		} else {
			turnDirection = -25;
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
		turnRight(45);
		back(20);
		ahead(40); // Ram him again!
		
		}


	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		fire(15);
		turnLeft(60);
		back(50);
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
		turnRight(55);
	}	
}
