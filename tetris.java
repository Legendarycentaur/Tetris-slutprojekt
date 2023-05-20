import java.awt.Color;

import java.awt.Graphics;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.lang.invoke.ClassSpecializer.SpeciesData;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class tetris extends JPanel implements KeyListener {
    private static int x = 200;
    public static int y = -40;
    static int amounthaslanded=-1;
    static int sizeofoneblock = 40;
    static int heightinrows = 19; // number of rows in width
    static int widthincolumns = 10; // number of columns ontop of eachother
    static int screen_width = ((sizeofoneblock * widthincolumns) + 16);
    static int screen_height = ((sizeofoneblock * heightinrows));
    static int positionxled = 5;
    static int positionyled = 1;
    static ArrayList<Integer> blockxled = new ArrayList<Integer>();
    static ArrayList<Integer> blockyled = new ArrayList<Integer>();
    static int count=0;
    static ArrayList<Boolean> xled = new ArrayList<Boolean>(10);
    static ArrayList<Boolean> yled = new ArrayList<Boolean>(20);

    
    
    int objekt[];
    static Boolean[][] spelplansarray2= new Boolean[11][21]; 
    static int[][] spelplansarrayGraphicsX = new int[10][20];
    static int[][] spelplansarrayGraphicsY = new int[10][20];
    static int counter = 0;
    static boolean blockHaslanded = false;
    static boolean hashadheightset = false;
    static boolean firstround = true;
    static int counterformovingdownallblocks=-2;
    static int firsttimerender=1;
    static int blockChooser=1;

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

        /*if (blockHaslanded == true) {
            g.setColor(Color.red);
            for(int i=0; i<=amounthaslanded; i++){
            g.fillRect(blockxled.get(i), blockyled.get(i), sizeofoneblock, sizeofoneblock);
            }
            
        }*/
        for(int i=0; i<spelplansarray2.length;i++){
            for(int j=0; j<spelplansarray2[i].length; j++){
                if(spelplansarray2[i][j]==true){
                    g.setColor(Color.red);
                    g.fillRect(i*sizeofoneblock, j*sizeofoneblock, sizeofoneblock, sizeofoneblock);
                }
            }
        }


        if(blockHaslanded==true || firsttimerender==1){
        blockChooser = getRandom();
        firsttimerender=0;
        blockHaslanded=false;
        System.out.println(blockChooser);
        }
        
        //den här är blocket som faller 
        g.setColor(Color.red);
        
        switch(blockChooser){
            case 0: g.setColor(Color.blue);
            g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock); 
                    g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x+40, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x+80, y, sizeofoneblock, sizeofoneblock);
                                                                        break;
            case 1: 
            g.setColor(Color.yellow);
            g.fillRect(x-40, y-40, sizeofoneblock, sizeofoneblock); 
                    g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x+40, y, sizeofoneblock, sizeofoneblock);
                    break;

            case 2: g.setColor(Color.pink);
            g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock); 
                    g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x+40, y, sizeofoneblock, sizeofoneblock);
                    g.fillRect(x+40, y-40, sizeofoneblock, sizeofoneblock);
            break;

            case 3: 
            g.fillRect(x-40, y-40, sizeofoneblock, sizeofoneblock); 
            g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock);
            g.fillRect(x, y-40, sizeofoneblock, sizeofoneblock);
            g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
            break;

            case 4: g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock); 
            g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
            g.fillRect(x, y-40, sizeofoneblock, sizeofoneblock);
            g.fillRect(x+40, y-40, sizeofoneblock, sizeofoneblock);
            break;

            case 5: g.fillRect(x-40, y, sizeofoneblock, sizeofoneblock); 
            g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
            g.fillRect(x, y-40, sizeofoneblock, sizeofoneblock);
            g.fillRect(x+40, y, sizeofoneblock, sizeofoneblock);
            break;

            case 6: g.fillRect(x+40, y, sizeofoneblock, sizeofoneblock); 
            g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
            g.fillRect(x-40, y-40, sizeofoneblock, sizeofoneblock);
            g.fillRect(x, y-40, sizeofoneblock, sizeofoneblock);
            break;
        }
        
        //g.fillRect(x, y, sizeofoneblock, sizeofoneblock);
        repaint();

    }
    private int getValueAt(int[][] spelplansarray2, int x, int y) {
        return spelplansarray2[x][y];
    }

    public static void chooseBlock(){
        
        
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
        if(positionxled < 10 && (spelplansarray2[positionxled+2][positionyled-1]==false && blockChooser==1||blockChooser==2||blockChooser==5)
        || (spelplansarray2[positionxled+2][positionyled-2]==false && blockChooser==4) 
        || (spelplansarray2[positionxled+1][positionyled-1]==false && blockChooser==3)
        || (spelplansarray2[positionxled+3][positionyled-1]==false && blockChooser==0)
        || (spelplansarray2[positionxled+2][positionyled-1]==false && blockChooser==5)
        || (spelplansarray2[positionxled+2][positionyled-2]==false && blockChooser==6)
        
        ){
            positionxled++;
            x += sizeofoneblock;
            repaint();
        }
        }
        catch(Exception e){
            System.out.println("något är fel");
        }

    }

    public static void moveDown() {
        System.out.print("Position yled "+positionyled+" Position xled "+positionxled+" är "+spelplansarray2[positionxled][positionyled]+"\n");
        try{

        if(positionyled==heightinrows || spelplansarray2[positionxled][positionyled]==true 
        || (spelplansarray2[positionxled+1][positionyled]==true && (blockChooser==1||blockChooser==2||blockChooser==5)) 
        || (spelplansarray2[positionxled-1][positionyled]==true && (blockChooser==1||blockChooser==2||blockChooser==5)) 

        || (spelplansarray2[positionxled-1][positionyled]==true && blockChooser==4)
        || (spelplansarray2[positionxled+1][positionyled-1]==true && blockChooser==4)
        || (spelplansarray2[positionxled-1][positionyled-1]==true && (blockChooser==6))
        || (spelplansarray2[positionxled+1][positionyled]==true && blockChooser==6)
        || (spelplansarray2[positionxled-1][positionyled]==true && blockChooser==0)
        || (spelplansarray2[positionxled+1][positionyled]==true && blockChooser==0)
        || (spelplansarray2[positionxled-1][positionyled]==true && blockChooser==3)
        
        
        
        /*||
        ((spelplansarray2[positionxled-1][positionyled]==true || spelplansarray2[positionxled][positionyled]==true || spelplansarray2[positionxled+1][positionyled]==true||spelplansarray2[positionxled+2][positionyled]==true) && blockChooser==0)
        ||
        ((spelplansarray2[positionxled-1][positionyled]==true||spelplansarray2[positionxled][positionyled]==true||spelplansarray2[positionxled+1][positionyled]==true) && (blockChooser==1||blockChooser==2||blockChooser==4)
        ||
        ((spelplansarray2[positionxled+1][positionyled]==true||spelplansarray2[positionxled][positionyled]==true) && blockChooser==3)
        ||
        ((spelplansarray2[positionxled-1][positionyled-1]==true||spelplansarray2[positionxled][positionyled]==true||spelplansarray2[positionxled+1][positionyled]==true) && blockChooser==4)
        ||
        ((spelplansarray2[positionxled-1][positionyled-1]==true||spelplansarray2[positionxled][positionyled]==true||spelplansarray2[positionxled+1][positionyled]==true) && blockChooser==5)
        
        )*/){

            //spelplansarray2[positionxled][positionyled-1]=true;
            switch(blockChooser){
                case 0: spelplansarray2[positionxled-1][positionyled-1]=true;
                spelplansarray2[positionxled][positionyled-1]=true;
                spelplansarray2[positionxled+1][positionyled-1]=true;
                spelplansarray2[positionxled+2][positionyled-1]=true; 

                break;

                case 1: spelplansarray2[positionxled-1][positionyled-2]=true; 
                        spelplansarray2[positionxled-1][positionyled-1]=true;
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled+1][positionyled-1]=true;
                
                break;

                case 2: spelplansarray2[positionxled-1][positionyled-1]=true; 
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled+1][positionyled-1]=true;
                        spelplansarray2[positionxled+1][positionyled-2]=true;
                
                break;

                case 3: spelplansarray2[positionxled-1][positionyled-2]=true; 
                        spelplansarray2[positionxled-1][positionyled-1]=true;
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled][positionyled-2]=true;
                
                break;
                case 4: spelplansarray2[positionxled-1][positionyled-1]=true; 
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled][positionyled-2]=true;
                        spelplansarray2[positionxled+1][positionyled-2]=true;
                
                break;
                case 5: spelplansarray2[positionxled-1][positionyled-1]=true; 
                        spelplansarray2[positionxled][positionyled-2]=true;
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled+1][positionyled-1]=true;
                
                break;
                case 6: spelplansarray2[positionxled-1][positionyled-2]=true; 
                        spelplansarray2[positionxled][positionyled-2]=true;
                        spelplansarray2[positionxled][positionyled-1]=true;
                        spelplansarray2[positionxled+1][positionyled-1]=true;
                
                break;
                
            }
            int istrue=0;
            
            for (int j = 0; j < spelplansarray2.length; j++) {
                if(spelplansarray2[j][positionyled-1]==true){
                    System.out.print("*");
                    istrue++;
                    blockHaslanded=true;
                    if(istrue==10){
                        
                        
                        System.out.print("Det är nu en full rad");
                        for(int i=0; i<spelplansarray2.length;i++){
                            spelplansarray2[i][positionyled-1]=false;
                            if(spelplansarray2[i][positionyled-2]==true){
                                    spelplansarray2[i][positionyled-1]=true;
                                    spelplansarray2[i][positionyled-2]=false;
                                    if(spelplansarray2[i][positionyled-3]==true){
                                        spelplansarray2[i][positionyled-2]=true;
                                        spelplansarray2[i][positionyled-3]=false;
                                        if(spelplansarray2[i][positionyled-4]==true){
                                            spelplansarray2[i][positionyled-3]=true;
                                            spelplansarray2[i][positionyled-4]=false;
                                            if(spelplansarray2[i][positionyled-5]==true){
                                                spelplansarray2[i][positionyled-4]=true;
                                                spelplansarray2[i][positionyled-5]=false;
                                                if(spelplansarray2[i][positionyled-6]==true){
                                                    spelplansarray2[i][positionyled-5]=true;
                                                    spelplansarray2[i][positionyled-6]=false;
                                                    if(spelplansarray2[i][positionyled-7]==true){
                                                        spelplansarray2[i][positionyled-6]=true;
                                                        spelplansarray2[i][positionyled-7]=false;
                                                        if(spelplansarray2[i][positionyled-8]==true){
                                                            spelplansarray2[i][positionyled-7]=true;
                                                            spelplansarray2[i][positionyled-8]=false;
                                                            if(spelplansarray2[i][positionyled-9]==true){
                                                                spelplansarray2[i][positionyled-8]=true;
                                                                spelplansarray2[i][positionyled-9]=false;
                                                                if(spelplansarray2[i][positionyled-10]==true){
                                                                    spelplansarray2[i][positionyled-9]=true;
                                                                    spelplansarray2[i][positionyled-10]=false;
                                                                    if(spelplansarray2[i][positionyled-11]==true){
                                                                        spelplansarray2[i][positionyled-10]=true;
                                                                        spelplansarray2[i][positionyled-11]=false;
                                                                        if(spelplansarray2[i][positionyled-12]==true){
                                                                            spelplansarray2[i][positionyled-11]=true;
                                                                            spelplansarray2[i][positionyled-12]=false;
                                                                            if(spelplansarray2[i][positionyled-13]==true){
                                                                                spelplansarray2[i][positionyled-12]=true;
                                                                                spelplansarray2[i][positionyled-13]=false;
                                                                                if(spelplansarray2[i][positionyled-14]==true){
                                                                                    spelplansarray2[i][positionyled-13]=true;
                                                                                    spelplansarray2[i][positionyled-14]=false;
                                                                                    if(spelplansarray2[i][positionyled-15]==true){
                                                                                        spelplansarray2[i][positionyled-14]=true;
                                                                                        spelplansarray2[i][positionyled-15]=false;
                                                                                        if(spelplansarray2[i][positionyled-16]==true){
                                                                                            spelplansarray2[i][positionyled-15]=true;
                                                                                            spelplansarray2[i][positionyled-16]=false;
                                                                                            if(spelplansarray2[i][positionyled-17]==true){
                                                                                                spelplansarray2[i][positionyled-16]=true;
                                                                                                spelplansarray2[i][positionyled-17]=false;
                                                                                                if(spelplansarray2[i][positionyled-18]==true){
                                                                                                    spelplansarray2[i][positionyled-17]=true;
                                                                                                    spelplansarray2[i][positionyled-18]=false;
                                                                                                    
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                  
                            
                            
                        }
                        for(int o=0; o<spelplansarray2.length;o++){
                            for(int p=0; p<spelplansarray2[o].length; p++){
                                spelplansarray2[o][p-1]=spelplansarray2[o][p-2];
                                spelplansarray2[o][p-2]=false;

                                /*i*/
                            }
                        }
                    }
                    
                }
                    
                }
            
            
            blockxled.add(x);
            blockyled.add(y);
            System.out.print("\n");
            
            positionyled=1;
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
    public int getRandom(){
        Random rand = new Random();
        int blockChooser = rand.nextInt(7);
        return blockChooser;
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        
        final int speed = 1000;
        int counter=0;
        for (int i = 0; i < spelplansarray2.length; i++) {
            for (int j = 0; j < spelplansarray2[i].length; j++) {
                spelplansarray2[i][j]=false;
                
            }
        }
        for (int i=0; i<widthincolumns;i++){
          spelplansarray2[i][heightinrows]=true;  
          
        }
        //spelplansarray2[5][7]=true;
        JFrame tetrisframe = new JFrame("Tetris");
        tetrisframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tetris tetris = new tetris();
        tetris.setBackground(Color.black);
        tetrisframe.add(tetris);
        tetrisframe.setVisible(true);
        tetrisframe.setBounds(0, 0, screen_width, 800);

        tetrisframe.setResizable(true);
        // Ticker som flyttar block i yled
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {  
                int istrue=0;
                moveDown();
                
            }
        };
        
        timer.schedule(task, 0, speed); // Schedule task to run every 1 second
        

    }
}
