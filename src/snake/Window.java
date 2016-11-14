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

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.util.ArrayList;


public class Window extends JPanel implements KeyListener{
    
    Window(){
        super();
        setBackground(Color.BLACK);
    }
    
    @Override
    public void paint(Graphics graphics){
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        for(int i = 0; i<Snake.snakeBlocks.size(); i++){
            graphics.fillRect(Snake.snakeBlocks.get(i).xPos, Snake.snakeBlocks.get(i).yPos, 30, 30);
            
        }
        for(int i = 0; i<Snake.pickUps.size(); i++){
            graphics.setColor(Color.ORANGE);
            graphics.fillRect(Snake.pickUps.get(i).xPos, Snake.pickUps.get(i).yPos, 30, 30);
        }
        
    }        
    
    @Override
    public void keyReleased(KeyEvent e){
            
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==37){ //37 is Left
            Snake.currentDirection=0;
        }
        
        if(e.getKeyCode()==39){ //39 is Right
            Snake.currentDirection=1;
        }
        
        if(e.getKeyCode()==38){ //38 is Up
            Snake.currentDirection=2;
        }
        
        if(e.getKeyCode()==40){ //40 is Down
            Snake.currentDirection=3;
        }
               
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    
   
}
