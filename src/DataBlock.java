public class DataBlock {
    private final int inOrder;
    private boolean inUse;
    private String data;

    // TODO Constructor
    public DataBlock(int inOrder, boolean inUse, String data) {
        this.inOrder = inOrder;
        this.inUse = inUse;
        this.data = data;
    }

    // TODO Setters & Getters
    public int getInOrder() {
        return inOrder;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // to string method
    @Override
    public String toString() {
        return "DataBlock{" +
                "inOrder=" + inOrder +
                ", inUse=" + inUse +
                ", data='" + data + '\'' +
                '}';
    }
}
