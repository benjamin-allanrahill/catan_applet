// LabelsCanvas:  an object that displays the dynamic labels
//
// CS 201 Exam 2

import java.awt.*;
import java.util.*;

@SuppressWarnings("serial") // to avoid Eclipse warning
public class LabelsCanvas extends Canvas {

    // instance vars
    protected CatanApplet parent;  // access to main applet class

    static final Font textFont = new Font("Arial", Font.PLAIN, 14);
    static final Font nodeFont = new Font("Arial", Font.PLAIN, 10);
    static final Font nodeFontBold = new Font("Arial", Font.BOLD, 11);
    static final Font colorFont = new Font("Arial", Font.BOLD, 13);

    Image bricks, ore, sheep, wheat, wood, buildCard;

    //constructor
    public LabelsCanvas(CatanApplet app, Image b, Image o, Image wo, Image sh,
                        Image wh, Image bc) {
        parent = app;
        bricks = b;
        ore = o;
        sheep = sh;
        wheat = wh;
        wood = wo;
        buildCard = bc;
    }

    // repaint this canvas
    public void paint (Graphics g) {

        // turn on anti-aliasing for smoother lines
        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Dimension d = getSize();        // size of canvas



        // print info about tree at the top
        // COUNT RESOURCES
        Player p = parent.currentPlayer;
        String brickCount = Integer.toString(p.getBrick());
        String oreCount = Integer.toString(p.getRock());
        String sheepCount = Integer.toString(p.getSheep());
        String wheatCount = Integer.toString(p.getWheat());
        String woodCount = Integer.toString(p.getWood());

        String s1 = "Resources:";
        g.setColor(Color.black);
        g.setFont(textFont);
        centerString(g, s1, d.width/2, 12);

        // draw images
        g.drawImage(bricks, d.width/7, 28, this);
        g.drawImage(ore, 2* d.width/7, 28, this);
        g.drawImage(sheep, 3* d.width/7, 28, this);
        g.drawImage(wheat, 4* d.width/7, 28, this);
        g.drawImage(wood, 5* d.width/7, 28, this);


        // assign resource labels
        centerString(g, brickCount, d.width/7 , 80);
        centerString(g, oreCount, 2* d.width/7 , 80);
        centerString(g, sheepCount, 3* d.width/7 , 80);
        centerString(g, wheatCount, 4* d.width/7 , 80);
        centerString(g, woodCount, 5*d.width/7, 80);
        if (parent.whichButton == 4){
            collectResources(); // collect resources for each player
        }

        //Color highlight = new Color(64,130,109);
        int r = 35;
        g.drawRect((d.width/2)-(r*2),200-(r/2),r*4,r);
        //g.setColor(Color.black);
        g.drawRect((d.width/2)-((r*5)/2),150-(r/2),r*5,r);
        String player = "";
        if(p.getPlayerColor() == 0) {
            player = "Current Player = Red";
            g.setColor(Color.red); }
        if(p.getPlayerColor() == 1) {
            player = "Current Player = Green";
            g.setColor(Color.green); }
        if(p.getPlayerColor() == 2) {
            player = "Current Player = Yellow";
            g.setColor(Color.yellow); }
        if(p.getPlayerColor() == 3) {
            player = "Current Player = Blue";
            g.setColor(Color.blue); }
        g.setFont(colorFont);
        centerString(g, player, d.width/2, 150);

        g.setFont(textFont);
        g.setColor(Color.black);
        String vp = "Victory points: " + Integer.toString(p.getVP());
        centerString(g, vp, d.width/2, 200);

        g.drawImage(buildCard, 50, 250, this);



    }

    //helper methods
    // runs everytime the label canvas is upddated
    // runs through each player
    // then runs through each hex
    // if the hex dice roll is the same as the current dice roll
    public void collectResources(){
        for (int p = 0; p< parent.players.length ; p++ ) { // run through each player
            System.out.print("PLAYER: " + p);
            for (int i= 0; i < parent.hexes.length ; i++) {
                for (int j =0; j <parent.hexes[i].length; j++) {
                    Hex hex = parent.hexes[i][j];
                    if (!hex.getGhost()) {
                        if (hex.getDiceRoll() == parent.diceRoll) {
                            int color = parent.players[p].getPlayerColor();
                            System.out.println(parent.players[p].getPlayerColor());
                            if(hex.getType()==0) {
                                parent.players[p].setBrick(hex.getOwed(color));
                            }
                            if(hex.getType()==1) {
                                parent.players[p].setSheep(hex.getOwed(color));
                            }
                            if(hex.getType()==2) {
                                parent.players[p].setWheat(hex.getOwed(color));
                            }
                            if(hex.getType()==3) {
                                parent.players[p].setWood(hex.getOwed(color));
                            }
                            if(hex.getType()==4) {
                                parent.players[p].setRock(hex.getOwed(color));
                            }
                        }
                    }
                }
            }
        }
    }

    // set node's text color (black or white) so that better contrast
    public static void setTextColor(Graphics g, int level, boolean isX) {
        if (isX && level < 10 || level < 4)
            g.setColor(Color.white);
        else
            g.setColor(Color.black);
    }

    // draw a String centered at x, y
    public static void centerString(Graphics g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics(g.getFont());
        int xs = x - fm.stringWidth(s)/2;
        int ys = y + fm.getAscent()/3;
        g.drawString(s, xs, ys);
    }



}
