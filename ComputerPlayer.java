public class ComputerPlayer extends Player{

  
  @Override
  public void takeGuess{
        //display grid being attacked
        boolean hitTarget = true;
        // get user coordinates for attack
        Random rand = new Random();
        
        int xCord = rand.nextInt(GRID_SIZE);
        int yCord = rand.nextInt(GRID_SIZE);

        while (hitTarget == true){

          //we will need to be able to discern which grid is player and comp
            if (grid[xCord][yCord].equals(SHIP_CODE){
                grid[xCord][yCord] = HIT_CODE;
                battleshipsLeft -=1;
                hitTarget = true;
                // helper method to decide whether to go right/left or up/down
            
            else if (grid[xCord][yCord].equals(HIT_CODE){
                grid[xCord][yCord] = HIT_CODE;
                // reiterate and let player select new cordinate
            else{
              hitTarget = false;  
}
