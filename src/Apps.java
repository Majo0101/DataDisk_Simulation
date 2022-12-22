public class Apps {

    public Apps() {
    }

    void removeFile(String fileName){
        System.out.println("remove" + fileName);
    }

    void dumpDisk(){
        System.out.println("dump");
    }

    void copyFile(String source, String target){
        System.out.println("copy" + source + target);
    }

    void averageCapacity(){
        System.out.println("free");
    }

    void writeFile(String name, String data){
        System.out.println("write" + name + data);
    }

    void bin(){
        System.out.println("Bin");
    }

    void formatDisk(){
        System.out.println("format");
    }
}
