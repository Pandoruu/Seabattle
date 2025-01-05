//import java.util.*;

public class Board {

    public Coordinates[][] board = new Coordinates[10][10];
    public Board(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                board[i][j] = new Coordinates(i, j);
            }
        }
    }

    public boolean isCanPlaceShip(Ship ship, int r, int c, char direction){
        if(r<0 || r>=10 || c<0 || c>=10) return false;
        if(direction=='V' && r+ship.getShipLength()>10) return false;
        if(direction=='H' && c+ship.getShipLength()>10) return false;
        for(int i=0; i<ship.getShipLength(); i++){
            if(direction=='V' && board[r+i][c].isUsed()) return false;
            if(direction=='H' && board[r][c+i].isUsed()) return false;
        }
        return true;
    }

    public boolean placeShip(Ship ship, int r, int c, char direction){
        if(!isCanPlaceShip(ship,r,c,direction)) return false;

        for(int i=0; i<ship.getShipLength(); i++){
            if(direction=='V'){
                board[r+i][c].setUsed(true);
                ship.addCoordinates(board[r+i][c]);
            }
            else{
                board[r][c+i].setUsed(true);
                ship.addCoordinates(board[r][c+i]);
            }
        }
        return true;
    }

    public void showBoard(String for_who){
        System.out.print(' ');
        for(int i=1; i<=10; i++){
            System.out.print("  " + i);
        }
        System.out.println();
        for(int i=0; i<10; i++){
            System.out.printf("%c ",(char)('A'+i));
            for(int j=0; j<10; j++){
                System.out.print(board[i][j].getChar(for_who));
            }
            System.out.println();
        }
    }

    public void attackCoordinates(int r, int c){
        board[r][c].attack(r,c);

    }
    public boolean isUsed(int r, int c){
        return board[r][c].isUsed();
    }
    public boolean isHit(int r, int c){
        return board[r][c].isHit();
    }
}