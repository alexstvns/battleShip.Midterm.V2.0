 /*
Alex Stevens
 */
package battleship;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import java.awt.Color;
import java.awt.Desktop.Action;
import javafx.geometry.Insets;
import java.util.Random;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import static javafx.scene.paint.Color.*;

/**
 *
 * @author gstev
 */
public class BattleShip extends Application  {
    
    private static final int MAXSHIPS = 14;
    private static final int GRIDSIZE  = 16;
    private GridPane pnlPlayer = new GridPane();
    private Label[][] lblPlayer = new Label[GRIDSIZE][GRIDSIZE];
    private Image[] imgShips = new Image[10];
    private Ship[] shipInfo = new Ship[8];
    private char[][] ocean = new char[16][16];    
    private GridPane controlPane = new GridPane();
    private Button reset = new Button("Reset");
    private Button showShips = new Button("Show Ships");
    private int missCount = 0;
    private Label infoLabel = new Label();
    private String miss = "Missed Shots: ";
    
    private Label[][] cover = new Label[GRIDSIZE][GRIDSIZE];
    GridPane Hidden = new GridPane();
    StackPane Board = new StackPane();
    
    
    @Override
    public void start(Stage primaryStage) {
                
        BorderPane root = new BorderPane();
                
        Scene scene = new Scene(root, 290, 315);
        
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.newGame();   // new game method handles the new game procedures
        //this.initOcean();
        //this.createPlayerPanel();
        this.createOptionPane();
        Board.getChildren().addAll(pnlPlayer,Hidden);
        //createShips();
        root.setCenter(Board);
        root.setTop(controlPane);
        //placeShips();
      
        
        this.reset.setOnAction(new EventHandler<ActionEvent>() {   // button event for reset button that will start a new game.
            @Override
            public void handle(ActionEvent event) {
               revealShips();
               newGame();
            }
        }
        
        );
        
        
        this.showShips.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
                revealShips();
            }
            
        });
        
        
        
    }
    
    private void newGame(){ 
        // call this to reset everything and start a new game or at beginning of game launch.
        this.createPlayerPanel();
        
        this.initOcean();
        this.createShips();
        this.placeShips();
        this.missCount=0;
        infoLabel.setText(miss+""+missCount);
        
    }
      
    private void createOptionPane()     // creates the panel to house reset,show boats, and # missed shots 
    {
      controlPane.setStyle("-fx-background-color:BLACK;");
       
       controlPane.setHgap(10);
       
       infoLabel.setText(miss+""+missCount);
       controlPane.add(infoLabel,0,0);
        infoLabel.setTextFill(LIGHTGREEN);
        
      
       controlPane.add(reset,1,0);
   
            reset.setTextFill(YELLOW);
            
            reset.setStyle("-fx-border-width:1;-fx-border-color:YELLOW;-fx-background-color:BLACK;");
            
       controlPane.add(showShips,2,0);
           showShips.setTextFill(YELLOW);
          
           showShips.setStyle("-fx-border-width:1;-fx-border-color:YELLOW;-fx-background-color:BLACK;");
       
      
        
    }
    private void createPlayerPanel()
    {
       pnlPlayer.setStyle("-fx-background-color:#0000FF;");
       for(int row = 0; row < GRIDSIZE; row++)
       {
           for(int col = 0; col < GRIDSIZE; col++)
           {
               lblPlayer[row][col] = new Label();
               
               Image imgShip = new Image("file:Images\\batt100.gif");
               lblPlayer[row][col].setGraphic(new ImageView(imgShip));
               lblPlayer[row][col].setMaxSize(16.0, 16.0);
               lblPlayer[row][col].setStyle("-fx-border-width:1;-fx-border-color:black;");
               
               cover[row][col] = new Label();
               Image coverImg = new Image("file:Images\\batt101.gif");
               cover[row][col].setGraphic(new ImageView(coverImg));
               cover[row][col].setMaxSize(16.0,16.0);
               cover[row][col].setStyle("-fx-border-width:1;-fx-border-color:black;");
               
               cover[row][col].setOnMouseClicked(new EventHandler<MouseEvent>()
               {
                   @Override
                   public void handle(MouseEvent t) {
                       
                       for(int row=0;row<GRIDSIZE;row++){
                           for(int col=0;col<GRIDSIZE;col++){
                               
                               Label clicked = (Label)t.getSource();
                               
                               
                               if(clicked == cover[row][col]){
                                   
                                   char Finder = Character.toUpperCase(ocean[row][col]);
                                   
                                   switch(Finder){
                                       
                                       case 'O':
                                       lblPlayer[row][col].setGraphic(new ImageView("file:Images\\batt102.gif"));
                                        missCount++;
                                        infoLabel.setText(miss+""+missCount); 
                                        cover[row][col].setVisible(false);
                                        break;
                                        
                                       case 'F':
                                          hit(row,col);
                                          break;
                                        
                                       case 'B':
                                          hit(row,col);
                                       break;
                                       
                                       case 'M':
                                          hit(row,col); 
                                          break;
                                       case 'C':
                                        
                                       hit(row,col);
                                        break;
                                           
                                       
                                   }
                                  
                               }
                               
                           }
                           
                           
                       }
                       
                       
                       
                   }
                   
               });
               
               pnlPlayer.add(lblPlayer[row][col], col, row);      
               Hidden.add(cover[row][col],col,row);
           
           }
       }
      
    }
    private void revealShips(){
        
        for(int row=0;row<GRIDSIZE;row++){
         for(int col=0;col<GRIDSIZE;col++){
             
             cover[row][col].setVisible(false);
        }
        }
    }
  
    
    private void createShips()
    {
        //this.loadShipImages();   // don't need since img's are loaded in ship.java
        this.createShipInfo();
    }
    /*
    private void loadShipImages()   // moved similar method to Ship.java
    {
        for(int i = 0; i < 10 ; i++)
        {
            imgShips[i] = new Image("file:Images\\batt" + (i + 1) + ".gif");
        }
    }
    */
    private void createShipInfo()
    {
        //Start with the frigate, we create 2 of them here but will place 3 total randomly it as two images
		//int[] frigateH = {0,1,4};
		shipInfo[0] = new Frigate('H');
		//int[] frigateV = {5,6,9};
		shipInfo[1] = new Frigate('V');
		
        // Create the mine Sweep it has 3 pieces
		//int[] mineSweepH = {0,4};
		shipInfo[2] = new MineSweep('H');
		//int[] mineSweepV = {5,9};
		shipInfo[3] = new MineSweep('V');
		
		//int[] cruiserH = {0,1,2,4};
		shipInfo[4] = new Cruiser('H');
		//int[] cruiserV = {5,6,7,9};
		shipInfo[5] = new Cruiser('V');
		
		//int[] battleShipH = {0,1,2,3,4};
		shipInfo[6] = new BattShip('H');
		//int[] battleShipV = {5,6,7,8,9};
		shipInfo[7] = new BattShip('V'); 
                
               
    }
    private void initOcean()
    {
        for(int row = 0; row < 16; row++)
        {
            for(int col = 0; col < 16; col++)
            {
                    ocean[row][col] = 'O';
            }
        }
    }
    private void placeShips()
    {
       // Create a Random object to select ships
        Random r = new Random();

        // Create random objects to place the ship at a row and a column
        Random randCol = new Random();
        Random randRow = new Random();

        //Place the ships, typically there are 14
        for(int ships = 0; ships < MAXSHIPS; ships++)
        {
                //Get a random ship
                Ship si = shipInfo[r.nextInt(8)];

                int row = randRow.nextInt(16);
                int col = randCol.nextInt(16);
                int direction = checkDirection(si, row, col);
                while(direction == 0) // 0 direction says that we can not place the ship
                {
                        row = randRow.nextInt(16);
                        col = randCol.nextInt(16);
                        direction = checkDirection(si, row, col);
                }
                // got a clear path, let put the ship on the ocean
               // int shipPieces[] = si.getShipPieces();
               Image[] hBoat= si.retShipH();
               Image[] vBoat= si.retShipV();
             
                if(si.Direction == 'H')  // place horizontal
                {
                        if(direction == 1)
                        {
                            for(int i = col, j = 0; i < col + si.length(); i++, j++)
                            {                                                          
                                lblPlayer[row][i].setGraphic(new ImageView(hBoat[j]));
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                        else
                        {
                            for(int i = col + si.length(), j = 0 ; i > col; i--, j++)
                            {
                                lblPlayer[row][i].setGraphic(new ImageView(hBoat[j]));	
                                String name = si.getName();
                                ocean[row][i] = name.charAt(0);
                            }
                        }
                }
                else // Must be vertical direction
                {
                        if(direction == 1) // place pieces in positive direction
                        {
                            for(int i = row, j = 0; i < row + si.length(); i++, j++)
                            {
                                lblPlayer[i][col].setGraphic(new ImageView(vBoat[j]));	
                                String name = si.getName();
                                ocean[i][col] = name.charAt(0);
                            }
                        }
                        else
                        {
                                for(int i = row + si.length(), j = 0; i > row; i--, j++)
                                {
                                    lblPlayer[i][col].setGraphic(new ImageView(vBoat[j]));	
                                    String name = si.getName();
                                    ocean[i][col] = name.charAt(0);
                                }
                        }
                }			
        } 
    }
    private int checkDirection(Ship si, int row, int col)
    {
        if(si.Direction == 'H')
            return checkHorizontal(si, row, col);
        else
            return checkVertical(si, row, col);
    }
    int checkHorizontal(Ship si,int row, int col)
    {
            boolean clearPath = true;

            int len = si.length();
            System.out.println(len);
            for(int i = col; i < (col + si.length()); i++)
            {
                    if(i >= GRIDSIZE) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Chec the negative direction
            for(int i = col; i > (col - si.length()); i--)
            {
                    if(i < 0) //This would put us outside the ocean
                    {
                            clearPath = false;
                            break;
                    }
                    if(ocean[row][i] != 'O') // Ship already exists in this spot
                    {
                            clearPath = false;
                            break;
                    }			

            }
            if(clearPath == true) //Ok to move in negative direction
                    return -1;
            else
                    return 0;   // No place to move			

    }
	
    int checkVertical(Ship si,int row, int col)
    {
            boolean clearPath = true;
            int len = si.length();
            System.out.println(len);
          
            

            for(int i = row; i < (row + si.length()); i++)
            {
                if(i >= GRIDSIZE) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }
            }
            if(clearPath == true) // ok to move in the positive direction
                    return 1; 

            //Next Check the negative direction
            for(int i = row; i > (row - si.length() ); i--)
            {
                if(i < 0) //This would put us outside the ocean
                {
                        clearPath = false;
                        break;
                }
                if(ocean[i][col] != 'O') // Ship already exists in this spot
                {
                        clearPath = false;
                        break;
                }			

            }
            if(clearPath == true) //Ok to move in negative direction
                return -1;
            else
                return 0;   // No place to move			

    }
    
 void hit(int row,int col){
    
    
      lblPlayer[row][col].setGraphic(new ImageView("file:Images\\batt103.gif"));
                                        cover[row][col].setVisible(false);
    
}
 

    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
