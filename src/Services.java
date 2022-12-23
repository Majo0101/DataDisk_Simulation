import java.util.Arrays;

public class Services {

    private final static String EMPTY = ""; // Constant
    private final static String EMPTY_NAME = "--------"; // Constant
    private final static String EMPTY_DATA = "----------------"; // Constant
    private final static int OK = 0; // Constant
    private final static int ERROR = -1; // Constant
    private final static int NAME_EXIST = 1; // Constant
    private final static int LONG_NAME = 2; // Constant

    Node[] node = new Node[5];
    DataBlock[] data = new DataBlock[10];

    // TODO Constructor
    Services(){
        for (int i = 0;i < node.length; i++){
            node[i] = new Node(false, i, EMPTY_NAME, false);
        }

        for (int i = 0; i < data.length; i++){
            data[i] = new DataBlock(i, false,  EMPTY_DATA);
        }
    }

    // TODO Remove file
    public int remove(String name){
        name = name + EMPTY_NAME.substring(name.length());
        for (Node value : node) {
            if (value.getName().equals(name)){
                value.setInBin(true);
                return OK;
            }
        }
        return ERROR;
    }

    // TODO Check input filename
    public int checkInputName(String name){
        name = name + EMPTY_NAME.substring(name.length());

        if (name.length() > 8){
            return LONG_NAME;
        } else {
            for (Node value : node) {
                if (value.getName().equals(name)) {
                    return NAME_EXIST;
                }
            }
        }
        return OK;
    }

    // TODO Write data - parse data to blocks && parse name to node
    public void writeData(String fileName, String inputData){
        int tmpNode = getFreeNode();
        int tmpData = getFreeData();

        if (tmpNode != ERROR && tmpData != ERROR){
            fileName = fileName + EMPTY_NAME.substring(fileName.length());
            node[tmpNode].setName(fileName);
            node[tmpNode].setInUse(true);

            while (fileName.length() != 0){
                if (inputData.length() <= 16){
                    inputData = inputData + EMPTY_DATA.substring(inputData.length());
                    data[tmpData].setData(inputData);
                    data[tmpData].setInUse(true);
                    node[tmpNode].addAddress(tmpData);
                    fileName = EMPTY;
                }else{
                    data[tmpData].setData(inputData.substring(0,16));
                    data[tmpData].setInUse(true);
                    node[tmpNode].addAddress(tmpData);
                    inputData = inputData.substring(16);
                    tmpData = getFreeData();
                }
            }
        }
    }

    // TODO Return free node
    private int getFreeNode(){
        for (Node value : node) {
            if (!value.isInUse() && !value.isInBin()){
                return value.getInOrder();
            }
        }
        return ERROR;
    }

    // TODO Return free data block
    private int getFreeData(){
        for (DataBlock value : data) {
            if (!value.isInUse()){
                return value.getInOrder();
            }
        }
        return ERROR;
    }

    // TODO Dump disk service
    public void dump(){
        StringBuilder addresses = new StringBuilder(EMPTY);

        for (Node value : node) {
            System.out.println("\nNODE_NAME: " + value.getName());
            System.out.println("NODE_USED: " + value.isInUse());
            System.out.println("NODE_BIN : " + value.isInBin());

            for (int i = 0; i < value.sizeAddress(); i++){
                addresses.append(value.indexOfAddress(i)).append(" ");
            }

            System.out.println("NODE_ADDR: " + addresses);
            addresses = new StringBuilder(EMPTY);
        }

        for (DataBlock value: data) {
            System.out.println("\nDATA_BLOCK_USE: " + value.isInUse());
            System.out.println("DATA_BLOCK_NUM: " + value.getInOrder());
            System.out.println("DATA_BLOCK_VAL: [" + value.getData() + "]");
        }
    }

    // TODO Format disk service
    public void format(){
        for (Node value : node) {
            value.setName("--------");
            value.setInUse(false);
            value.setInBin(false);
            value.flushAddress();
        }

        for (DataBlock datum : data) {
            datum.setData("----------------");
            datum.setInUse(false);
        }
    }

    // TODO Return num of free bytes
    public int freeBytes(){
        int free = 0;
        for (DataBlock datum : data) {
            for (int j = 0; j < datum.getData().length(); j++) {
                if (datum.getData().charAt(j) == '-') {
                    free++;
                }
            }
        }
        return free;
    }

    // TODO Return num of free data blocks
    public int freeData(){
        int free = 0;
        for (DataBlock datum : data) {
            if (!datum.isInUse()) {
                free++;
            }
        }
        return free;
    }

    // TODO Return num of free nodes
    public int freeNodes(){
        int free = 0;
        for (Node value : node) {
            if (!value.isInUse() && !value.isInBin()) {
                free++;
            }
        }
        return free;
    }

    // TODO Return Data from Node by index - overload
    public String getData(int index){
        StringBuilder tmp = new StringBuilder(EMPTY);

        for (Node value : node) {
            if (value.getInOrder() == index) {
                for (int i = 0; i < value.sizeAddress(); i++){
                    for (DataBlock valueData: data){
                        if (valueData.getInOrder() == value.indexOfAddress(i)){
                            tmp.append(valueData.getData());
                        }
                    }
                }
            }
        }
        return tmp.toString().replaceAll("-","");
    }

    // TODO Return Data from Node by name - overload
    public String getData(String name){
        StringBuilder tmp = new StringBuilder(EMPTY);
        name = name + EMPTY_NAME.substring(name.length());

        for (Node value : node) {
            if (value.getName().equals(name)) {
                for (int i = 0; i < value.sizeAddress(); i++){
                    for (DataBlock valueData: data){
                        if (valueData.getInOrder() == value.indexOfAddress(i)){
                            tmp.append(valueData.getData());
                        }
                    }
                }
            }
        }
        return tmp.toString().replaceAll("-","");
    }

    // to string function
    @Override
    public String toString() {
        return "Services{}";
    }
}