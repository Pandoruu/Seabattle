import java.util.ArrayList;

public class Ship {
    private String name;
    private int length;
    private ArrayList<Coordinates> location;
    //contractor
    public Ship(){};
    public Ship(String name, int length){
        this.name = name;
        this.length = length;
        location = new ArrayList<>();
    }
    public String getShipName(){return name;}

    public void addCoordinates(Coordinates coordinates){
        this.location.add(coordinates);
    }

    public int getShipLength(){ return length; }

    public boolean isSunk(){
        int hits=0;
        for(Coordinates i:location){
            if(i.isHit()) hits++;
        }
        return hits==length;
    }
}