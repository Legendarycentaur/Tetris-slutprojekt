import java.awt.Color;

import java.awt.Graphics;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class tetris extends JPanel implements KeyListener {
    private static int x = 200;
    public static int y = -40;
    static int amounthaslanded=-1;
    static int sizeofoneblock = 40;
    static int heightinrows = 19;// number of rows in width
    static int widthincolumns = 10;// number of columns ontop of eachother
    static int screen_width = ((sizeofoneblock * widthincolumns) + 16);
    static int screen_height = ((sizeofoneblock * heightinrows));
    static int positionxled = 5;
    static int positionyled = 0;
    static ArrayList<Integer> blockxled = new ArrayList<Integer>();
    static ArrayList<Integer> blockyled = new ArrayList<Integer>();

    static ArrayList<Boolean> xled = new ArrayList<Boolean>(10);
    static ArrayList<Boolean> yled = new ArrayList<Boolean>(20);

    int objekt[];
    static Boolean[][] spelplansarray2= new Boolean[10][20]; 

    static int counter = 0;
    static boolean blockHaslanded = false;
    static boolean hashadheightset = false;
    static boolean firstround = true;

    public tetris() {
        super();
        setFocusable(true);
        addKeyListener(this);
        setFocusTraversalKeysEnabled(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < heightinrows; row++) {
            for (int col = 0; col < widthincolumns; col++) {
                g.setColor(Color.DARK_GRAY);
                g.drawRect(col * sizeofoneblock, row * sizeofoneblock, sizeofoneblock, sizeofoneblock);
            }
        }

        if (blockHaslanded == true) {
            g.setColor(Color.red);
            for(int i=0; i<=amounthaslanded; i++){
            g.fillRect(blockxled.get(i), blockyled.get(i), sizeofoneblock, sizeofoneblock);
            }
            
        }

        g.setColor(Color.red);
        g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
        repaint();

    }

    

    public void moveLeft() {
        try{
        if(x>0 && spelplansarray2[positionxled-1][positionyled-1]==false){
            positionxled--;
            x -= sizeofoneblock;
            repaint();
            
            
        }}
        catch(Exception e){}
        
    }

    public void moveRight() {
        try{
        if(positionxled < 9 && spelplansarray2[positionxled+1][positionyled-1]==false){
            positionxled++;
            x += sizeofoneblock;
            repaint();
        }
        }
        catch(Exception e){

        }

    }

    public static void moveDown() {
        System.out.print("Position yled "+positionyled+" Position xled "+positionxled+" är "+spelplansarray2[positionxled][positionyled]+"\n");
        try{

        if(positionyled==heightinrows||spelplansarray2[positionxled][positionyled]==true){
            spelplansarray2[positionxled][positionyled-1]=true;
            int istrue=0;
            for (int j = 0; j < spelplansarray2.length; j++) {
                if(spelplansarray2[j][positionyled]==true){
                    System.out.print("*");
                    istrue++;
                    if(istrue==9){
                        for (int i = 0; i < spelplansarray2.length; i++) {
                        if(spelplansarray2[i][positionyled]==true){
                            spelplansarray2[i][positionyled]=false;
                            
                        }
                    }
                }
                    
                }
            }
            blockxled.add(x);
            blockyled.add(y);
            System.out.print("\n");
            positionyled=0;
            y=-40;
            blockHaslanded=true;
            amounthaslanded++;
            
            
        }
        else if(positionyled==heightinrows||spelplansarray2[positionxled][positionyled]==false){
        positionyled++;
        y+=sizeofoneblock;
        }
    }
    catch (Exception e){
        System.out.println("You Have lost!");
        
    }
        
        

    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT){
            
            moveLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            
            moveRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        for (int i = 0; i < spelplansarray2.length; i++) {
            for (int j = 0; j < spelplansarray2[i].length; j++) {
                spelplansarray2[i][j]=false;
                
            }
        }
        for (int i=0; i<widthincolumns;i++){
          spelplansarray2[i][heightinrows]=true;  
          
        }
        
        JFrame tetrisframe = new JFrame("Tetris");
        tetrisframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetris tetris = new tetris();
        tetris.setBackground(Color.black);
        System.out.println(yled);
        System.out.println(xled);
        tetrisframe.add(tetris);
        tetrisframe.setVisible(true);
        tetrisframe.setBounds(20, 10, screen_width, 800);

        tetrisframe.setResizable(true);
        // Ticker som flyttar block i yled
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {  
                
                moveDown();

            }
        };
        timer.schedule(task, 0, 1000); // Schedule task to run every 1 second
        

    }
}
