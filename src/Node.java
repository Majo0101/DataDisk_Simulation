import java.util.ArrayList;

public class Node {

    private boolean inUse;
    private final int inOrder;
    private String name;
    private boolean inBin;
    private final ArrayList<Integer> address;

    Node(boolean inUse, int inOrder, String name, boolean inBin){
        this.inUse = inUse;
        this.inOrder = inOrder;
        this.name = name;
        this.inBin = inBin;
        this.address = new ArrayList<Integer>();
    }

    public void setInUse(boolean inUse){
        this.inUse = inUse;
    }

    public boolean isInUse(){
        return inUse;
    }

    public int getInOrder(){
        return inOrder;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setInBin(boolean inBin){
        this.inBin = inBin;
    }

    public boolean isInBin(){
        return inBin;
    }

    public void addAddress(int address){
        this.address.add(address);
    }

    public int sizeAddress(){
        return address.size();
    }

    public int indexOfAddress(int index){
        return address.indexOf(index);
    }

    public void flushAddress(){
        address.clear();
    }

}