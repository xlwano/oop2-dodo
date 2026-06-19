import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
            System.out.println("moved " + nrStepsTaken); 
        }
    }

    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){
            // print coordinates
            System.out.println(getX() + getY());    
            move();
        }
    }
    
        public void walkToWorldEdgeClimbingOverFence( ){
        while( ! borderAhead() ){    
            
            if(fenceAhead()) {
                climbOverFence();
            } else { 
                move(); 
        }
    }
    }
    
    public void walkToWorldEdge() { 
        while( ! borderAhead()) {
            move();
        }
    }
    
    public void goBackToStartOfRowAndFaceBack() {
        turn180();
        walkToWorldEdge();
        turn180();
    }
    
    public void pickUpGrainsAndPrintCoordinates() {
        while ( ! borderAhead()) {
            if(onGrain()) {
                pickUpGrain();
                System.out.println(getX() + " , "+ getY());
            } else { 
                move(); 
            }
        }
    }
    
    public void goToWorldEdgeAndLayEggs() {
        while (!borderAhead()) {
            if(onNest() && canLayEgg()){
                layEgg();
            } 
            move();
        
        }
        if(onNest() && canLayEgg()){
                layEgg();
            }
    }
    
    public void stepOneCellBackwards() {
        if ( canMove()) {
        turn180();
        move();
        turn180();
    }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if( onEgg() ){
            return false;
       }else{
            return true;
        }
    }  
    
    
    public void turn180() {
        turnRight();
        turnRight();
    }
    
    
    public void climbOverFence() {
        if (fenceAhead()) {
        turnLeft();
        move(); 
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();
    }
    }
    
    public boolean grainAhead() {
        move();
        if (onGrain()) {
            stepOneCellBackwards();
            return true;
        } else {
            stepOneCellBackwards();
            return false;
        }
    }
    
    
    public void goToEgg() {
        while (! onEgg()) {
            move();
        }
    }
    
    public void goToNestAndAvoidFences() {
        while (!borderAhead() && !onNest()) {
                climbOverFence();
                move();
            }
        if (onNest()) {
            layEgg();
        }
    }
    
    public void walkAroundFencedArea() {
        while(!onEgg()) {
            if(!fenceAhead()) {
            turnRight();
            if(canMove()) {
                move();
            }
            if(fenceAhead()) {
                turnLeft();
                move();
            } 
            }
        }
    }
    
    public void eggTrailToNest() {
        while(!onNest()) {
            if(eggAhead() || nestAhead()) {
                move();
                turnLeft();
            } else {
                turnRight();
            }
        }
    }
    
    
    public void faceDirection(int direction) {
        if (direction >= 0 && direction <= 3)
        {
        while (getDirection() !=direction){
            turnRight();
        }
    }
    }
    
    public void solveMaze() {
                while(!onNest()) {
            if(canMove()) {
                move();
                turnLeft();
            } else {
                turnRight();
            }
            if(onNest()) {
                showCompliment("Good Job!!");
            }
        }
    }
    
    public void goToLocation(int coordX, int coordY) {
    if (validCoordinates(coordX, coordY)) {
        while (!locationReached(coordX, coordY)) {
        if (getX() < coordX) {
            faceDirection(EAST);
            move();
        } else if (getX() > coordX) {
            faceDirection(WEST);
            move();
        } else if (getY() < coordY) {
            faceDirection(SOUTH);
            move();
        } else if (getY() > coordY) {
            faceDirection(NORTH);
            move();
        }
    }
} else {
showError("Invalid Coordinates.");
}
}

public boolean validCoordinates(int x, int y) {
    if((x >= 0 && x <= getWorld().getWidth()) && (y >= 0 && y <=getWorld().getHeight())) { 
    return true;
    } else {
        return false;
    }
}

public boolean locationReached(int x, int y) {
    return getX() == x && getY() == y;
}

public int countEggsInRow() {
    int eggCount = 0;
        if (onEgg()) {
    eggCount++;
    }
    while (!borderAhead()) {
    move();
    if (onEgg()) {
    eggCount++;
    }
    }
    goBackToStartOfRowAndFaceBack();
    showCompliment("Aantal eieren: " + eggCount);
    return eggCount;
}

public void layTrailOfEggs(int n) {
    for (int i = 0; i < n; i++) {
        move();
        layEgg();
    }
}

public int countEggsInWorld() {
    int totalEggCount = 0;
    int row = 0;
    int height = getWorld().getHeight();
    while(row < height) {
    goToLocation(0,row);
    faceDirection(1);
    int eggCount = countEggsInRow();
    totalEggCount = totalEggCount + eggCount;
    row++;
}
    goToLocation(0, 0);
    faceDirection(1);
    return totalEggCount;
}

public void searchRowWithMostEggs() {
    int height = getWorld().getHeight();
    int row = 0;
    int bestRow = 0;
    int bestCount = 0;
    boolean equalEggCount = false;
    while (row < height) {
        goToLocation(0, row);
        faceDirection(1);
        int eggCount = countEggsInRow();
        row++;
        if (eggCount > bestCount) {
            bestCount = eggCount;
            bestRow = row;
            equalEggCount = false;
        } else if (eggCount == bestCount) {
            equalEggCount = true;
        }
    }
    goToLocation(0, 0);
    faceDirection(1);
        if (equalEggCount) {
        System.out.println("There are multiple rows with " + bestCount + " eggs.");
    } else {
        System.out.println("Row with most eggs: " + bestRow + " (" + bestCount + ")");
    }
}

public void eggMonument() {
    int x = getX();
    int y = getY();
    int row = 0;
    while (y + row < getWorld().getHeight() && x + row < getWorld().getWidth()) {
        goToLocation(x, y + row);
        faceDirection(1);
        for (int i = 0; i <= row; i++) {
            layEgg();
            if (i < row) { 
                move();
            }
        }
        row++;
    }
}

public void solidMonument() {
    int x = getX();
    int y = getY();
    int eggs = 1;
    int row = 0;
    while (y + row < getWorld().getHeight() && x + row <= getWorld().getWidth()) {
        goToLocation(x, y + row);
        faceDirection(1);
        for (int i = 0; i < eggs; i++) {
            layEgg();
            if (i < eggs - 1) { 
                move(); 
            }
        }
        row++;
        eggs = eggs * 2;
    }
}

public void eggPyramid() {
    int x = getX();
    int y = getY();
    int aantalRijen = getWorld().getHeight() - y;

    for (int rij = 0; rij < aantalRijen; rij++) {
        for (int kolom = -rij; kolom <= rij; kolom++) {
            goToLocation(x + kolom, y + rij);
            if (canLayEgg()) {
                layEgg();
            }
        }
    }

    goToLocation(x, y);
 }
 
 public double averageEggAmount() {
    int rowAmount = getWorld().getHeight();
    int totalEggs = 0;

    for (int row = 0; row < rowAmount; row++) {
        goToLocation(0, row);
        faceDirection(1);
        totalEggs += countEggsInRow();
    }

    double average = (double) totalEggs / rowAmount;
    System.out.println("Average amount of eggs per row: " + average);

    goToLocation(0, 0);
    faceDirection(1);
    return average;
}

public void parityBitAlgorithm () {
        int worldHeight = getWorld().getHeight();
        int worldWidth = getWorld().getWidth();
        int savedX = -1;
        int savedY = -1;
        for(int i = 0; i < worldHeight; i++) {
            goToLocation(0, i);
            faceDirection(EAST);
            if(countEggsInRow() % 2 != 0) {
                savedY = i;
                System.out.println(savedY);
            }
        }
        
        for(int i = 0; i < worldWidth; i++) {
            goToLocation(i, 0);
            faceDirection(SOUTH);
            if(countEggsInRow() % 2 != 0) {
                savedX = i;
                System.out.println(savedX);
            }
        }
        
        if (savedX != -1 && savedY != -1) {
            goToLocation(savedX, savedY);
            if(canLayEgg()) {
                layEgg();
            }
        }
    } 
    
public int direction() {
        int previousX = getX();
        int previousY = getY();
        int direction = -1;
        
        if(borderAhead()) {
            stepOneCellBackwards();
            previousX = getX();
            previousY = getY();
            move();
            if(previousX < getX()) {
                direction = 1;
            } else if(previousX > getX()) {
                direction = 3;
            } else if(previousY < getY()) {
                direction = 2;
            } else if(previousY > getY()){
                direction = 0;
            }
        } else {
            move();
            if(previousX < getX()) {
                direction = 1;
            } else if(previousX > getX()) {
                direction = 3;
            } else if(previousY < getY()) {
                direction = 2;
            } else if(previousY > getY()){
                direction = 0;
            }
            stepOneCellBackwards();
        }
        return direction;
    }
    
public void parityBitAlgorithmWithoutDirection() {
        int worldHeight = getWorld().getHeight();
        int worldWidth = getWorld().getWidth();
        int savedX = -1;
        int savedY = -1;
        
        for(int i = 0; i < worldHeight; i++) {
            goToLocation(0,i);
            faceDirection(1);
            if(countEggsInRow() % 2 != 0) {
                savedY = i;
                System.out.println(savedY);
            }
        }
        
        for(int i = 0; i < worldWidth; i++) {
            goToLocation(i,0);
            faceDirection(2);
            if(countEggsInRow() % 2 != 0) {
                savedX = i;
                System.out.println(savedX);
            }
        }
        
        if (savedX != -1 && savedY != -1) {
            goToLocation(savedX, savedY);
            if(canLayEgg()) {
                layEgg();
            }
        }
    }
        /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(0) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEgss = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    
    public void makeListOfSurpriseEgg() {
        
    }
}

