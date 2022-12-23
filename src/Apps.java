import java.util.InputMismatchException;
import java.util.Scanner;

public class Apps {

    private final static String RESET_INPUT = ""; // Constant
    private final Services service = new Services(); // Services layer

    // TODO Constructor
    public Apps() {
    }

    // TODO App remove file
    void removeFile(String fileName){
        System.out.println("remove" + fileName);
        System.out.println(service.checkInputName(fileName));
    }

    // TODO App dump disk
    void dumpDisk(){
        System.out.print("-------------------DISK---------------------");
        service.dump();
        System.out.println("-------------------END----------------------");
    }

    // TODO App Copy file
    void copyFile(String source, String target){
        System.out.println("copy" + source + target);
    }

    // TODO App average capacity
    void averageCapacity(){
        System.out.println("> FREE BYTES: " + service.freeBytes() + " / 160"); // Call freeBytes Service
        System.out.println("> FREE DATA: " + service.freeData() + " / 10"); // Call freeData Service
        System.out.println("> FREE NODES: " + service.freeNodes() + " / 5"); // Call freeNodes Service
    }

    // TODO App write file
    void writeFile(String name, String data){
        System.out.println("write" + name + data);
    }

    // TODO App bin
    void bin(){
        System.out.println("Bin");
    }

    // TODO App format disk
    void formatDisk(){
        String input = RESET_INPUT;
        Scanner userInput = new Scanner(System.in); // Console input object

        System.out.println(">-> Disk will be formated. Continue? y/n");
        try{
            System.out.print("<-< ");
            input = userInput.nextLine();
        }catch (InputMismatchException e){
            System.out.println(">-> Wrong input");
        }

        if (input.equals("Y") || input.equals("y")){
            service.format(); // Call format Service
            System.out.println(">-> FORMAT...");
            System.out.println(">-> OK! You will be returned to the main menu.");
        }else{
            System.out.println(">-> You will be returned to the main menu.");
        }
    }

    // to string function
    @Override
    public String toString() {
        return "Apps{}";
    }
}