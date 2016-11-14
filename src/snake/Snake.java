/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

/**
 *
 * @author David
 */

import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

    /**
     * @param args the command line arguments
     */
    
    public static int rectangleX = 0;
    public static int rectangleY = 0;
    
    static Window window = new Window(); 
    static JFrame appWindow = new JFrame();
    
    static int currentDirection = 0;   //0 is right, 1, is down, 2 is up, 3 is left;
    static ArrayList<SnakeBlock> snakeBlocks = new ArrayList<>();
    static ArrayList<PickUp> pickUps = new ArrayList<>();
                                
    public static void main(String[] args) {
        // TODO code application logic here
        
        SnakeBlock firstBlock = new SnakeBlock(0,0);
        
        snakeBlocks.add(firstBlock);
        
        generatePickUp();
       
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.add(window);
        
        appWindow.setSize(646, 669);
        appWindow.setVisible(true);
        appWindow.setFocusable(true);
        
        window.addKeyListener(window);
        window.setFocusable(true);
        window.requestFocus();
        
        while(true){
            try{
                Thread.sleep(100);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            System.out.println(checkCollision());
            
            switch(checkCollision()){
                case 0: break;
                case 1: gameOver();
                        break;
                case 2:    //Make snake longer and generate new pickUp
                        break;
                default:break;
            }
            
            
            snakeBlocks.get(0).xPos1 = snakeBlocks.get(0).xPos;
            snakeBlocks.get(0).yPos1 = snakeBlocks.get(0).yPos;
            
            for(int i = 0; i<snakeBlocks.size()-1; i++){
                snakeBlocks.get(i+1).move(snakeBlocks.get(i));
            }
            
            switch(currentDirection){
                case 0: snakeBlocks.get(0).xPos-=30;
                        break;
                case 1: snakeBlocks.get(0).xPos+=30;
                        break;
                case 2: snakeBlocks.get(0).yPos-=30;
                        break;
                case 3: snakeBlocks.get(0).yPos+=30;
                        break;
                default:break;
                              
            }
            
            if(snakeBlocks.get(0).xPos<0){
                snakeBlocks.get(0).xPos=600;
            }
            
            if(snakeBlocks.get(0).xPos>600){
                snakeBlocks.get(0).xPos=0;
            }
            
            if(snakeBlocks.get(0).yPos<0){
                snakeBlocks.get(0).yPos=600;
            }
            
            if(snakeBlocks.get(0).yPos>600){
                snakeBlocks.get(0).yPos=0;
            }
            
            window.revalidate();
            window.repaint();
        }
          
    }
    
    static int checkCollision(){
        int xPosHead = snakeBlocks.get(0).xPos;
        int yPosHead = snakeBlocks.get(0).yPos;
        
        int xPosCheckSnake;
        int yPosCheckSnake;
        
        int xPosCheckPickUp;
        int yPosCheckPickUp;
        
        for(int i = 1; i<snakeBlocks.size(); i++){  //Begin checking at second snake block, head cannot collide with itself
            xPosCheckSnake = snakeBlocks.get(i).xPos;
            yPosCheckSnake = snakeBlocks.get(i).yPos;
            
            if(xPosHead == xPosCheckSnake&&yPosHead==yPosCheckSnake){
                //System.out.println("collided with self");
                return 1;   //return value of 1 means that player lost
            }
        }
        
        for(int i = 0; i<pickUps.size(); i++){      
            xPosCheckPickUp = pickUps.get(i).xPos;
            yPosCheckPickUp = pickUps.get(i).yPos;
            System.out.println(xPosHead+","+yPosHead+"  "+xPosCheckPickUp+","+yPosCheckPickUp);
            if(xPosHead == xPosCheckPickUp&&yPosHead==yPosCheckPickUp){
                pickUps.remove(i);
                System.out.println("collided with pickup");
                generatePickUp();
                addSnakeLength();
                return 2;   //return value of 2 means that pickup consumed
            }
        }
        
        return 0;
    }
    
    static void gameOver(){
        System.exit(0);
    }
    
    static void addSnakeLength(){
        int xPos;
        int yPos;
        
        xPos = snakeBlocks.get(snakeBlocks.size()-1).xPos+30;
        yPos = snakeBlocks.get(snakeBlocks.size()-1).yPos;
        
        SnakeBlock snakeBlock = new SnakeBlock(xPos, yPos);
        snakeBlocks.add(snakeBlock);
    }
    
    static void generatePickUp(){
        int xPos;
        int yPos;
        
        int[] coordinate = new int[2];
        coordinate = null;
        
        while(coordinate==null){   //loop until generateLocation creates valid coordinates     
            coordinate = generateLocation();
        }

        xPos = coordinate[0];
        yPos = coordinate[1];
        
        PickUp pickUp = new PickUp(xPos, yPos);
     
        pickUps.add(pickUp);
    }
    
    static int[] generateLocation(){
        int xPos;
        int yPos;
        
        Random rand = new Random();
        
        xPos = rand.nextInt((20 - 0) + 1) + 0;  //Random x and y for pickup locations
        yPos = rand.nextInt((20 - 0) + 1) + 0;
        
        xPos*=30;
        yPos*=30;
        
        for(int i = 0; i<snakeBlocks.size(); i++){
            int xPosSnakeBlock = snakeBlocks.get(i).xPos;
            int yPosSnakeBlock = snakeBlocks.get(i).yPos;
            
            if(xPos == xPosSnakeBlock && yPos == yPosSnakeBlock){
                return null;
            }
            
            
            else{
                int[] coordinates = new int[2];
                coordinates[0]=xPos;
                coordinates[1]=yPos;
                return coordinates;
            }
        }
        return null;
    }
    
    public abstract class Listener implements KeyListener {
        
    }
    
}
