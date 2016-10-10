/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

public void run() {
	placeOuterBeepers(); //placing beepers on the outside corners of the environment.
	findMid(); //placing beepers in between the outer beepers and once the middle beeper is placed, taking away all other beepers to be left with the middle beeper.
}

/* precondition: Karel facing East at East most corner.
 * postcondition: Karel facing West at West most corner.
 * Placing beepers on the outside corners of the environment.
 */

private void placeOuterBeepers(){
	putBeeper();
	while(frontIsClear()){ // moves Karel to the opposite side.
		move();
	}
	if(noBeepersPresent()){ // once Karel has reached the opposite side, it will place a beeper, turn around, and then move one space.
		putBeeper();
		turnAround();
		move();
	}
}

/* precondition: Karel facing West at the West most corner.
 * postcondition: Karel has placed beepers across the entire first row.
 * placing beepers in between the outer beepers and once the middle beeper is placed, method will be called to take away all other beepers to be left with the middle beeper.
 */

private void findMid(){
	while(noBeepersPresent()) // precondition: no beepers present. if no beepers are present, then Karel must move to the other side and place a beeper.
		move();
	turnAround(); // once Karel comes across a beeper as it moves to the opposing side, it'll need to be turned around and moved to meet the precondition of the while loop.
	move();
	putBeeper();
	move();
	if(beepersPresent()) // once all of the inner beepers are placed, and Karel comes across two beepers next to each other(we can assume that these beepers are the middle and the beeper next to it) Karel will pick up all beepers except the middle.
		pickOuterBeepers();
	else // if there are no beepers present, this means the row has not been filled yet and this method will call itself to fill all of the corners of the row.
		findMid();
}
		
/* precondition: all beepers must be placed across the first street.
 * postcondition: all beepers have been picked up except for the middle beeper.
 */
private void pickOuterBeepers(){
	while(beepersPresent()){ // clearing out the right side of beepers.
		pickBeeper();
		if(frontIsClear())
			move();
	}
	turnAround();
	while(noBeepersPresent())
		move();
	move(); // necessary in order to skip over the middle beeper.
	while(beepersPresent()){ // clearing out the left side of beepers.
		pickBeeper();
		if(frontIsClear())
			move();
	}
	turnAround();
	while(noBeepersPresent())
		move();
}
}