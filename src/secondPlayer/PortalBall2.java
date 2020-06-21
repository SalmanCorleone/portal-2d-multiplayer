package secondPlayer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;





import javax.imageio.ImageIO;
 



import State.*;

import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class PortalBall2 {
	private static final int DIAMETER = 20;
	private BufferedImage blue;
	private BufferedImage orange;
	double x=200;
	double y=200;
	double tempx, tempy;

	double xa;
	double ya;
	private MPlayer2 guy;
	MLevel1 m;
	private int PaintFlag=0;
	private int ColorFlag;


	public PortalBall2(MPlayer2 person,MLevel1 mp) {
		guy= person;
		this.m=mp;
		try {
			blue=ImageIO.read(new File("./images/blueBall.png"));
		} catch (IOException e) {e.printStackTrace();}
		try {
			orange=ImageIO.read(new File("./images/orangeball.png"));
		} catch (IOException e) {System.out.println("load hoy nai orange!");;}

	}

	public void move()
	{
		if(!collision() && y<650)
		{
			x+=xa;
			y+=ya;
		}
		if(collision()){
//			System.out.println("hoise collision");
//			System.out.println(x+" "+y);
			
			x-=xa;y-=ya;
			xa=ya=0;
			
			
//			System.out.println(x+" "+y);
			PaintFlag=0;
			/*
			 * Now, 11,12 are Horizontal
			 * 
			 * And, 21,22 are vertical
			 * 
			 */
			
			if(collision(9,0))
			{ 

				System.out.println("verti right");
				System.out.println("colorFlag"+ ColorFlag);
				m.port2.set(x+20, y-15, ColorFlag,22);
			}
			else if(collision(-9,0))
			{ 

				System.out.println("verti left");
				System.out.println("colorFlag"+ ColorFlag);
				m.port2.set(x-5, y-15, ColorFlag,21);
			}
			else if(collision(0,-9)) 
			{
				System.out.println("colorFlag"+ ColorFlag);
				System.out.println("hor up");
				m.port2.set(x-20, y-10, ColorFlag,11);
			}
			else if(collision(0,9)) 
			{
				System.out.println("colorFlag"+ ColorFlag);
				System.out.println("hor down");
				
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				m.port2.set(x-20, y+10, ColorFlag,12);
			}
		
		}
		
	}
	public void go(int mx, int my, int e)
	{
		x=guy.x+10;
		y=guy.y+5;
//		r= Math.pow(Math.pow(mx-x,2)+Math.pow(my-y,2),0.5);
		PaintFlag=1;
//		if(e.getButton()==MouseEvent.BUTTON1) ColorFlag=1;
//		else if(e.getButton()==MouseEvent.BUTTON3) ColorFlag=2;
		ColorFlag=e;
		double total= Math.abs(mx-x)+Math.abs(my-y);

		xa= (((double)(mx-x)/total) *10);
		ya= (((double)(my-y)/total) *10);

//		System.out.println(xa+" "+ya+" "+total);
	}
	
	
	public void p(Graphics2D g) {
		if(PaintFlag==1)
		{
			if(ColorFlag==1) g.drawImage(blue,(int)x,(int)y,null);
			if(ColorFlag==2) g.drawImage(orange,(int) x,(int)y,null);
		}
	}

	private boolean collision() {
		return m.roof.getBounds().intersects(getBounds())|| m.pillar.getBounds().intersects(getBounds()) || m.pillar2.getBounds().intersects(getBounds()) || m.pillar3.getBounds().intersects(getBounds()) ||m.Ground.getBounds().intersects(getBounds());
	}


	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, DIAMETER, DIAMETER);
	}
	public Rectangle TempgetBounds() {
		return new Rectangle((int)tempx, (int)tempy, DIAMETER, DIAMETER);
	}
	
	private boolean collision(int ax, int ay) {
		tempx=x+ax;
		tempy=y+ay;

	    if (m.roof.getBounds().intersects(TempgetBounds())  || m.pillar.getBounds().intersects(TempgetBounds()) || m.pillar3.getBounds().intersects(TempgetBounds()) || m.pillar2.getBounds().intersects(TempgetBounds()) ||m.Ground.getBounds().intersects(TempgetBounds()))
	    {

	        return true;
	    }


	    return false;
	     
	}

}