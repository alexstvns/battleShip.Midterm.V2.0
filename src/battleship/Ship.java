/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author gstev
 */
public class Ship
{
	private String shipName;
	private int[] shipPieces; 
	char Direction;
	
        protected Image[] shipImageV;
        protected Image[] shipImageH;
        
      
        
	Ship(String name,int[]V,int[]H)
	{
            this.shipName = name;
            
            
            this.shipImageV = new Image[V.length];
            this.shipImageH = new Image[H.length];

            
            
            this.shipBuilder(V,H);
              
            /*
                shipPieces = new int[V.length];
		for(int i = 0; i < V.length; i++)
			shipPieces[i] = pieces[i];
                
              */  
            }
            
           
		
	

    private void shipBuilder(int[] V, int[] H) {
        
        for(int i=0;i<V.length;i++){
            
        
            
            shipImageV[i] =new Image("file:Images\\batt" + (V[i]+1) + ".gif");
            shipImageH[i] = new Image("file:Images\\batt" + (H[i]+1) + ".gif");
           
        
            
           
        }
        
    }
    
 
	
	public String getName()
	{
		return this.shipName;
	}
	public int[] getShipPieces()
	{
		return shipPieces;
	}
	public int getDirection()
	{
		return this.Direction;
	}
	public int length()
	{
		return shipImageV.length;
	}


        
        public Image[] retShipV(){
            
         
            return this.shipImageV;
        }
        
        public Image[] retShipH(){
            
         
            
          
           return this.shipImageH;
            
        }
}




class Frigate extends Ship{
   
    static int[] frigateH = {0,1,4};
   static int[] frigateV = {5,6,9};
    public Frigate(char Direction) {
        super("Frigate",frigateV,frigateH);
        super.Direction=Direction;
    }
   
    
    
}
class MineSweep extends Ship{
    
   static int[] mineSweepH = {0,4};
   static int[] mineSweepV = {5,9};
    
    public MineSweep(char Direction) {
        super("MineSweep",mineSweepV,mineSweepH);
        super.Direction=Direction;
    }
    
    
    
}
class Cruiser extends Ship{
    
   static int[] cruiserH = {0,1,2,4};
   static int[] cruiserV = {5,6,7,9};
    
    
    public Cruiser(char Direction) {
        super("Cruiser",cruiserV,cruiserH);
        super.Direction = Direction;
    }
    
    
    
}

class BattShip extends Ship{
   static int[] battShipV ={5,6,7,8,9};
  static int[] battShipH ={0,1,2,3,4};
    
    public BattShip(char Direction) {
       super("BattleShip",battShipV,battShipH);
       super.Direction = Direction; 
    }
    
    
    
}