import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	
	private int[] snakeXlength = new int [750];
	private int[] snakeYlength = new int [750];
	
	private boolean gameOver = false;
	
	private boolean left= false;
	private boolean right= false;
	private boolean up= false;
	private boolean down= false;
	
    private ImageIcon titleImage;
    private ImageIcon headRight;
    private ImageIcon headLeft;
    private ImageIcon headDown;
    private ImageIcon headUp;
    
    private int lengthOfSnake = 3;
    
    private Timer timer;
    private int delay = 100;
    
    private ImageIcon tail;
    private int moves = 0;
    private int score = 0;
    //fruit params
    private int [] fruitXpos = {25,50,75,100,125,150,175,200,
    		225,250,275,300,325,350,375,400,425,450,475,500,525,
    		550,575,600,625,650,675,700,725,750,775,800,825,850};
    
    private int [] fruitYpos = {75,100,125,150,175,200,
    		225,250,275,300,325,350,375,400,425,450,475,500,525,
    		550,575,600,625};
    private ImageIcon fruitimage;
    
    private Random rendom = new Random();
    private int xpos = rendom.nextInt(34);
    private int ypos = rendom.nextInt(21);
    
    
    public GamePlay(){
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	
    	timer = new Timer(delay,this);
    	timer.start();

    }
    public void paint(Graphics graphics){
    	
    	if(moves == 0) {
    		//snake position 
    		snakeXlength[0] = 100;
    		snakeXlength[1] = 75;
    		snakeXlength[2] = 50;
    		
    		snakeYlength[0] =100;
    		snakeYlength[1] =100;
    		snakeYlength[2] =100;
    		
    	}
    	
    	
        //Display title
        titleImage = new ImageIcon("title.png");
        titleImage.paintIcon(this,graphics,25,5);

        //Display gameplay border
        graphics.setColor(Color.darkGray);
        graphics.drawRect(24,74,851,577);

        //Display gameplay background
        graphics.setColor(Color.black);
        graphics.fillRect(25,75,850,575);
        
        //Display score and length
        graphics.setColor(Color.black);
        graphics.setFont(new Font("areal",Font.PLAIN,14));
        graphics.drawString("Score:"+ score, 780, 30);
        
        graphics.setColor(Color.black);
        graphics.setFont(new Font("areal",Font.PLAIN,14));
        graphics.drawString("Length:"+ lengthOfSnake, 780, 50);
        
        //Display the snake - initial position of the head image 
        headRight = new ImageIcon("headRight.png");
        headRight.paintIcon(this, graphics, snakeXlength[0],snakeYlength[0]);
        
        for(int i=0; i<lengthOfSnake;i++) {
        	if(i==0 && right) 
        	{
        		headRight = new ImageIcon("headRight.png");
        		headRight.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
        	}
        	if(i==0 && left) 
        	{
        		headLeft = new ImageIcon("headLeft.png");
        		headLeft.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
        	}
        	if(i==0 && up) 
        	{
        		headUp = new ImageIcon("headUp.png");
        		headUp.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
        	}
        	if(i==0 && down) 
        	{
        		headDown = new ImageIcon("headDown.png");
        		headDown.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);
        	}
        	if(i!=0) {
        		tail = new ImageIcon("tail.png");
        		tail.paintIcon(this, graphics, snakeXlength[i], snakeYlength[i]);	
        	}
        	fruitimage = new ImageIcon("fruit.png");
        	
        	if(fruitXpos[xpos]==snakeXlength[0]&& fruitYpos[ypos]==snakeYlength[0]) {
        		score+=5;
        		lengthOfSnake++;
        	    xpos = rendom.nextInt(34);
        	    ypos = rendom.nextInt(23);
        	}
        	fruitimage.paintIcon(this, graphics, fruitXpos[xpos], fruitYpos[ypos]);
        	
        	//If the snake has eaten its tail then the game is over
        	for(int j=1; j<lengthOfSnake;j++) {
        		if(snakeXlength[j] == snakeXlength[0] && snakeYlength[j]==snakeYlength[0]) {
        			gameOver = true;
        			right = false;
        			left = false;
        			up= false;
        			down = false;
        			
        	        graphics.setColor(Color.red);
        	        graphics.setFont(new Font("areal",Font.BOLD,40));
        	        graphics.drawString("Game Over! Score:"+ score, 250, 300);
        	        
        	        graphics.setColor(Color.white);
        	        graphics.setFont(new Font("areal",Font.BOLD,20));
        	        graphics.drawString("Press Enter to Restart", 350, 340);
        			
        		}
        	}
        	
        }

        graphics.dispose();
    }
	@Override
	public void actionPerformed(ActionEvent e) {timer.restart();
	if(right) 
	{
		for(int n = lengthOfSnake-1; n>=0;n--)
		{
			snakeYlength[n+1] = snakeYlength[n];
		}
		for(int n = lengthOfSnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeXlength[n] = snakeXlength[n]+25;
			}
			else 
			{
				snakeXlength[n] = snakeXlength[n-1];
			}
			if(snakeXlength[n] >850)
			{
				snakeXlength[n] = 25;
			}
			
		}
		repaint();
	}
	
	if(left) 
	{
		for(int n = lengthOfSnake-1; n>=0;n--)
		{
			snakeYlength[n+1] = snakeYlength[n];
		}
		for(int n = lengthOfSnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeXlength[n] = snakeXlength[n]-25;
			}
			else 
			{
				snakeXlength[n] = snakeXlength[n-1];
			}
			if(snakeXlength[n] < 25)
			{
				snakeXlength[n] = 850;
			}
			
		}
		repaint();			
		
	}
	if(up) 
	{
		for(int n = lengthOfSnake-1; n>=0;n--)
		{
			snakeXlength[n+1] = snakeXlength[n];
		}
		for(int n = lengthOfSnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeYlength[n] = snakeYlength[n]-25;
			}
			else 
			{
				snakeYlength[n] = snakeYlength[n-1];
			}
			if(snakeYlength[n] < 75)
			{
				snakeYlength[n] = 625;
			}
			
		}
		repaint();
		
	}
	if(down) 
	{
		for(int n = lengthOfSnake-1; n>=0;n--)
		{
			snakeXlength[n+1] = snakeXlength[n];
		}
		for(int n = lengthOfSnake; n>=0; n--) 
		{
			if (n==0) 
			{
				snakeYlength[n] = snakeYlength[n]+25;
			}
			else 
			{
				snakeYlength[n] = snakeYlength[n-1];
			}
			if(snakeYlength[n] > 625)
			{
				snakeYlength[n] = 75;
			}
			
		}
		
		repaint();
	}
	
	
}
		
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(gameOver) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				moves = 0;
				score = 0;
				lengthOfSnake = 3;
				repaint();
				
			}
			return;
		}
		
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves ++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves ++;
			left = true;
			if(!right) {
				left = true;
			}
			else {
				right = true;
				left = false;
			}
			up = false;
			down = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves ++;
			up = true;
			if(!down) {
				up = true;
			}
			else {
				up = false;
				down = true;
			}
			left = false;
			right = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves ++;
			down = true;
			if(!up) {
				down = true;
			}
			else {
				up = true;
				down = false;
			}
			left = false;
			right = false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}