import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class Player {
    private String name;
    private Board board;
    private ArrayList<Ship> ships ;
    //contractor
    public Player(){
        this.ships = new ArrayList<>();
        this.board = new Board();
        ships = new ArrayList<>();
    }
    //setter
    public void setName(String name){
        this.name=name;
    }
    //getter
    public Board getBoard(){
        return board;
    }
    public String getName(){
        return name;
    }
    //functions
    public void placeShips() throws IOException {
        Scanner sc = new Scanner(System.in);

        ArrayList<Ship> remains = new ArrayList<Ship>();
        remains.add(new Ship("Patrol Boat", 2));
        remains.add(new Ship("Patrol Boat", 2));
        remains.add(new Ship("Destroyer Boat", 4));
        remains.add(new Ship("Submarine", 3));
        remains.add(new Ship("Battle Ship", 5));
        while(!remains.isEmpty()){
            board.showBoard("owner");
            System.out.println(name + ", Place your ship(s): ");
            System.out.println("Available ships to place:");
            for(int i=0; i<remains.size(); i++){
                System.out.println((i + 1) + ". " + remains.get(i).getShipName() + ": Size = " + remains.get(i).getShipLength());
            }
            System.out.print("Select ship number: ");
            int num = sc.nextInt() - 1;
            if(num<0 || num>=remains.size()){
                System.out.println("Invalid! Choose again!");
                continue;
            }
            System.out.print("Enter coordinates of the ship's head (Ex:A 1): ");
            char tmp = sc.next().toUpperCase().charAt(0);
            int r = tmp - 'A';
            int c = sc.nextInt() - 1;
            System.out.print("Enter direction of the ship (H for horizontal, V for vertical): ");
            char direction = sc.next().toUpperCase().charAt(0);
            if(board.placeShip(remains.get(num),r,c,direction)){
                ships.add(remains.get(num));
                remains.remove(remains.get(num));
            }
            else{
                System.out.println("Invalid! Try again!");
                System.out.println("<<press Enter to Try again>>");
                System.in.read();
            }
            GameManager.clearScreen();
        }
    }

    public boolean hasLost() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }
}