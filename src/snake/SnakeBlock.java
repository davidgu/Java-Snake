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
public class SnakeBlock {
    int xPos;
    int yPos;
    
    int xPos1;
    int yPos1;
    
    SnakeBlock(int x, int y){
        xPos = x;
        yPos = y;
        xPos1 = xPos;
        yPos1 = yPos;
    }
    
    public void move(SnakeBlock nextBlock){
        xPos1 = xPos;
        yPos1 = yPos;
        
        xPos = nextBlock.xPos1;
        yPos = nextBlock.yPos1;
    }
   
}

