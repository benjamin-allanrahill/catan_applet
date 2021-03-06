//Hex class for each hex on game board
//
//CS201 final project - Catan

public class Hex {

    //instance variables

    //resources numbers correspond as follows:
    //0 = brick, 1 = sheep, 2 = wheat, 3 = wood 4 = rock.
    public int type;

    //amount owed to each player
    public int owedR;
    public int owedG;
    public int owedY;
    public int owedB;

    // cartesian coordinates
    public int x;
    public int y;
    public int size;

    // coordinates
    public int row;
    public int col;

    // activation number
    public int diceRoll;

    //whether Hex is painted or not
    public boolean ghost;

    public Hex(int type, int x, int y, int size, int row, int col, int diceRoll, boolean ghost) {
        this.type = type;
        this.owedR = 0;
        this.owedG = 0;
        this.owedY = 0;
        this.owedB = 0;

        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.diceRoll = diceRoll;

        this.ghost = ghost;
    }

    // getters
    public int getOwedR() {
        return owedR;
    }

    public int getOwedG() {
        return owedG;
    }

    public int getOwedY() {
        return owedY;
    }

    public int getOwedB() {
        return owedB;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int getDiceRoll() {
        return diceRoll;
    }
    public boolean getGhost() {
        return ghost;
    }
    public int getOwed(int player){
        if (player == 0)
            return owedR;
        if (player == 1)
            return owedG;
        if (player == 2)
            return owedY;
        if (player == 3)
            return owedB;
        else return 0;
    }

    //setters
    public void setOwed(int player, int i){
        if (player == 0)
            owedR+= i;
        if (player == 1)
            owedG+=i;
        if (player == 2)
            owedY+=i;
        if (player == 3)
            owedB+=i;

    }
    public void setGhost(boolean setter) {
        this.ghost = setter;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setType(int type) {
        this.type = type;
    }
    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }

}
