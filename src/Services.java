import java.util.Arrays;

public class Services {

    private final static String EMPTY = "";
    private final static int OK = 0;
    private final static int ERROR = -1;
    private final static int NAME_EXIST = 1;
    private final static int LONG_NAME = 2;

    Node[] node = new Node[5];
    DataBlock[] data = new DataBlock[10];

    Services(){
        for (int i = 0;i < node.length; i++){
            node[i] = new Node(false, i, "--------", false);
        }

        for (int i = 0; i < data.length; i++){
            data[i] = new DataBlock(i, false,  "----------------");
        }
    }

    public int checkInputName(String name){
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

    public void writeData(String fileName, String inputData){
        String emptyName = "--------";
        String emptyData = "----------------";
        String tmpString = EMPTY;
        int tmpNode = getFreeNode();
        int tmpData = getFreeData();

        if (tmpNode != ERROR && tmpData != ERROR){
            fileName = fileName + emptyName.substring(fileName.length());
            node[tmpNode].setName(fileName);
            node[tmpNode].setInUse(true);

            if (inputData.length() <= 16){
                inputData = inputData + emptyData.substring(inputData.length());
                data[tmpData].setData(inputData);
                data[tmpData].setInUse(true);
                node[tmpNode].addAddress(tmpData);
            }else{

            }
        }



    }

    private int getFreeNode(){
        for (Node value : node) {
            if (!value.isInUse() && !value.isInBin()){
                return value.getInOrder();
            }
        }
        return ERROR;
    }

    private int getFreeData(){
        for (DataBlock value : data) {
            if (!value.isInUse()){
                return value.getInOrder();
            }
        }
        return ERROR;
    }

    public void dump(){
        String addresses = EMPTY;

        for (Node value : node) {
            System.out.println("\nNODE_NAME: " + value.getName());
            System.out.println("NODE_USED: " + value.isInUse());
            System.out.println("NODE_BIN : " + value.isInBin());
            for (int i = 0; i < value.sizeAddress(); i++){
                addresses += value.indexOfAddress(i) + " ";
            }
            System.out.println("NODE_ADDR: " + addresses);
            addresses = EMPTY;
        }

        for (DataBlock value: data) {
            System.out.println("\nDATA_BLOCK_USE: " + value.isInUse());
            System.out.println("DATA_BLOCK_NUM: " + value.getInOrder());
            System.out.println("DATA_BLOCK_VAL: [" + value.getData() + "]");
        }
    }

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

    public int freeData(){
        int free = 0;
        for (DataBlock datum : data) {
            if (!datum.isInUse()) {
                free++;
            }
        }
        return free;
    }

    public int freeNodes(){
        int free = 0;
        for (Node value : node) {
            if (!value.isInUse() && !value.isInBin()) {
                free++;
            }
        }
        return free;
    }

    // to string function
    @Override
    public String toString() {
        return "Services{}";
    }
}