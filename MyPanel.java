import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
class MyPanel extends JPanel {
        int xVelocity = 1;
        double yVelocity = 1;
        private BufferedImage image;
        private BufferedImage moon;
        private BufferedImage sun;

        Image img1;
        int panelWidth = 500;
        int panelHeight = 500;
        int diameter = 300;
        int widthHalf = 200;
        int ballSize = 20;
        int x = 0;
        double y = panelWidth/2;
        int yInt = (int) y;
        double przyprostokatnaOne;
        double przeciwprostokatna;
        double przyprostokatnaTwo;

        int moonDiameter = 100;
        int xMoon;
        double yMoon;
        int xVelocityMoon = 0;

        int wychylenie=1;
        
        public MyPanel () {        	
                setPreferredSize(new Dimension(panelWidth, panelHeight));   
        }
        
        public void run() throws InterruptedException, IOException {
          File imageFile = new File("ziemia.jpg");
          File imageMoon = new File("moon.jpg");
          File imageSun = new File("sun.jpg");
          
          

          for (int i = 0; i <= diameter; i++) {
            panelWidth=getWidth();
          panelHeight=getHeight();
            
            x=i;
            x=x+xVelocity;
            x=x+(panelWidth/2)-(ballSize/2)-(diameter/2);
            y=panelHeight/2-przyprostokatnaTwo;
            y=y-ballSize/2;

            xMoon();
            yMoon();
            image = ImageIO.read(imageFile);
            moon = ImageIO.read(imageMoon);
            sun = ImageIO.read(imageSun);
            Thread.sleep(15);
            repaint();

            przyprostokatnaOne= ((diameter/2-i)) * (diameter/2-i);
            przeciwprostokatna =  (diameter/2) * (diameter/2);
            przyprostokatnaTwo=Math.sqrt(przeciwprostokatna-przyprostokatnaOne);
            widthHalf--;
            if(i==diameter)
            {
              xVelocity = xVelocity*-1;
              while(i>0)
              {
              i=i-1;
              x=i+(panelWidth/2)-(ballSize/2)-(diameter/2);
              y=panelHeight/2+przyprostokatnaTwo;
              y=y-ballSize/2;
             
              xMoon();
              yMoon();
              image = ImageIO.read(imageFile);
              repaint(); 
              przyprostokatnaOne= (((diameter/2)-(diameter-i))) * (((diameter/2)-(diameter-i)));
              przeciwprostokatna =  (diameter/2) * (diameter/2);
              Thread.sleep(15);
              przyprostokatnaTwo=Math.sqrt(przeciwprostokatna-przyprostokatnaOne);     
              }
            }  
          }
        repaint();
        }
        boolean xSwitch = false;
        
        int counterMoon=1;
        int wychylenieChanger=1;
        
        public void xMoon ()
        {
            wychylenie=wychylenie+wychylenieChanger;

            if(wychylenie==moonDiameter/2)
            {
              wychylenieChanger=-1;
            }
            if(wychylenie==0)
            {
              wychylenieChanger=1;
            }
            xMoon=x-moonDiameter/2;
            xMoon=xMoon+xVelocityMoon;
            xVelocityMoon=xVelocityMoon+counterMoon;
            if(xVelocityMoon==moonDiameter)
            {
              counterMoon=-1;
              }
            if(xVelocityMoon==0)
            {
              counterMoon=1;
            } 
        }

        int moonPrzeciwprostokatnka = (moonDiameter/2) * (moonDiameter/2);
        
        public void yMoon()
        {
          double moonPrzyprostokatna = ((moonDiameter/2-wychylenie)) * (moonDiameter/2-wychylenie);
          double moonprzyprostokatnaTwo= Math.sqrt(moonPrzeciwprostokatnka-moonPrzyprostokatna);
          yMoon=y-moonprzyprostokatnaTwo;
          if(counterMoon==-1)
          {
            yMoon=y+moonprzyprostokatnaTwo;
          }
        }
 
        public void paintComponent (Graphics g) {
        	super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.fill(new Ellipse2D.Double(xMoon, yMoon, ballSize, ballSize));
                g2.drawImage(moon, xMoon, (int)yMoon,this);
                
                g2.fill(new Ellipse2D.Double(x, y, ballSize, ballSize));
                g2.drawImage(image, x, (int)y, this);

                g2.fill(new Ellipse2D.Double((panelWidth/2)-(ballSize/2), (panelHeight/2)-(ballSize/2), ballSize, ballSize));
                g2.drawImage(sun, (panelWidth/2)-(ballSize/2), (panelHeight/2)-(ballSize/2), this);
                

                
        }
 
}