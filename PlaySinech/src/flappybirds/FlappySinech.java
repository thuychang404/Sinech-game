package flappybirds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;
import static pkg2dgamesframework.GameScreen.KEY_PRESSED;

public class FlappySinech extends GameScreen{
    private BufferedImage birds;
    private Animation bird_anim;
    private BufferedImage bg;
    private BufferedImage beginImage;
    private BufferedImage gameoverImage;
    
    public static float g = 0.1f;
    
    private Bird bird;
    private Ground ground;
    
    private ChimneyGroup chimneyGroup;
    
    private int Point = 0;
    
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public FlappySinech(){
        super(432,768);
        
        try {
            birds = ImageIO.read(new File("Assets/sinech.jpg"));
        } catch (IOException ex) {}
        
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        
        bird = new Bird(201, 270, 50, 50);
        ground = new Ground();
        
        chimneyGroup = new ChimneyGroup();
        
        BeginGame();
    }
        
    private void resetGame(){
        bird.setPos(100, 270);
        bird.setVt(0);
        bird.setLive(true);
        Point = 0;
        chimneyGroup.resetChimneys();
    }
    
    @Override
    public void GAME_UPDATE(long deltaTime) {
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
            
            if(bird.getLive()) bird_anim.Update_Me(deltaTime);
            bird.update(deltaTime);
            ground.Update();
            
            chimneyGroup.update();
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())){
                    bird.setLive(false);
                }
            }
            
            for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBehindBird()
                        && i%2==0){
                    Point ++;
                    chimneyGroup.getChimney(i).setIsBehindBird(true);
                }
                    
            }
            
            if(bird.getLive() == false) {
                bird.setIsFlying(false);
            }
            if(bird.getPosY() + bird.getH() > ground.getYGround()) CurrentScreen = GAMEOVER_SCREEN;
            
        }else{
            
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        try {
            bg = ImageIO.read(new File("Assets/background.png"));
        } catch (IOException ex) {}
        try {
            beginImage = ImageIO.read(new File("Assets/playbutton.png"));
        } catch (IOException ex) {}
        try {
            gameoverImage = ImageIO.read(new File("Assets/gameover.png"));
        } catch (IOException ex) {}
        
        //set background
        g2.drawImage(bg, 0, 0, null);
        g2.drawImage(bg, 0, 0, 432, 768, null, null);
        
//        g2.setColor(Color.decode("#b8daef"));
//        g2.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);
        
        chimneyGroup.paint(g2);
        
        ground.Paint(g2);
        
        
        
        if(bird.getIsFlying())
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, (float) -0.5);
        else 
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        if(CurrentScreen == BEGIN_SCREEN){
            
//            g2.drawImage(flappyImage, -90, 150, 600, 134, null, null);
            g2.drawImage(beginImage, 432/2 - 156/2, 768/2 - 87/2, null);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            g2.drawImage(gameoverImage, 432/2 - 288/2, 768/2 - 63/2, null);
        }
        
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        
        String s = Integer.toString(Point);
        int textWidth = g2.getFontMetrics().stringWidth(s);
        g2.drawString(Integer.toString(Point), 432/2 - textWidth/2, 100);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(Event == KEY_PRESSED){
            
            if(CurrentScreen == BEGIN_SCREEN){
                
                CurrentScreen = GAMEPLAY_SCREEN;
                
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(bird.getLive()) bird.fly();
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Khong thuc hien duoc.");
    }
}
