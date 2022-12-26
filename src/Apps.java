import java.util.InputMismatchException;
import java.util.Scanner;

public class Apps {

    private final static String RESET_INPUT = ""; // Constant
    private final static int OK = 0; // Constant
    private final static int ERROR = -1; // Constant
    private final static int NAME_EXIST = 1; // Constant
    private final static int LONG_NAME = 2; // Constant
    private final Services service = new Services(); // Services layer

    // TODO Constructor
    public Apps() {
    }

    // TODO App remove file
    public void removeFile(String name){
        if (service.checkInputName(name) == NAME_EXIST){
            if (service.remove(name) == OK){
                System.out.println("> File was removed");
            }else{
                System.out.println("> Somethings went wrong");
            }
        }else{
            System.out.println("> File not found");
        }
    }

    // TODO App dump disk
    public void dumpDisk(){
        System.out.print("-------------------DISK---------------------");
        service.dump();
        System.out.println("-------------------END----------------------");
    }

    // TODO App Copy file
    public void copyFile(String source, String target){
        if (service.checkInputName(source) == NAME_EXIST){
            if (service.getData(source).length() <= (service.freeData() * 16)){
                service.writeData(target, service.getData(source));
                System.out.println("> The file was copied");
            }else{
                System.out.println("> The file is larger than the available memory!");
            }
        }else{
            System.out.println("> File not found");
        }
    }

    // TODO App average capacity
    public void averageCapacity(){
        System.out.println("> FREE BYTES: " + service.freeBytes() + " / 160"); // Call freeBytes Service
        System.out.println("> FREE DATA: " + service.freeData() + " / 10"); // Call freeData Service
        System.out.println("> FREE NODES: " + service.freeNodes() + " / 5"); // Call freeNodes Service
    }

    // TODO App write file
    public void writeFile(String name, String data){
        if (service.checkInputName(name) == OK){
            if (service.freeNodes() != 0){
                if (data.length() <= (service.freeData() * 16)){
                    service.writeData(name, data);
                }else{
                    System.out.println("> The file is larger than the available memory!");
                }
            }else{
                System.out.println("> No space for the new file!");
            }
        }else{
            System.out.println("> Name exist!");
        }
    }

    // TODO App Print file
    public void print(String name){
        if (service.checkInputName(name) == NAME_EXIST){
            System.out.println("> NAME: " + name);
            System.out.println("> DATA: " + service.getData(name));
        }else{
            System.out.println("> File not found");
        }
    }

    // TODO App bin
    void bin(){
        String input = RESET_INPUT;
        Scanner userInput = new Scanner(System.in); // Console input object

        System.out.println(">-> s - Show files in bin");
        System.out.println(">-> r - Restore file from bin");
        System.out.println(">-> d - Remove file from bin");
        System.out.println(">-> e - Exit");
        try{
            System.out.print("<-< ");
            input = userInput.nextLine();
        }catch (InputMismatchException e){
            System.out.println(">-> Wrong input");
        }

        if (input.equals("s") || input.equals("S")){
            System.out.println(">-> -------------BIN--------------");
            service.inBin();
            System.out.println(">-> -------------BIN--------------");
            System.out.println("> Main Menu");
        }else if(input.equals("r") || input.equals("R")){
            input = RESET_INPUT;
            System.out.println(">-> Enter file name for restore file");

            try{
                System.out.print("<-< ");
                input = userInput.nextLine();
            }catch (InputMismatchException e){
                System.out.println(">-> Wrong input");
            }

            if (service.checkInputName(input) == NAME_EXIST){
                service.restoreFile(input);
                System.out.println(">-> File was restored...");
                System.out.println("> Main menu");
            }else{
                System.out.println(">-> File not found");
            }

        }else if(input.equals("d") || input.equals("D")){
            input = RESET_INPUT;
            System.out.println(">-> Enter file name for remove file");

            try{
                System.out.print("<-< ");
                input = userInput.nextLine();
            }catch (InputMismatchException e){
                System.out.println(">-> Wrong input");
            }

            if (service.checkInputName(input) == NAME_EXIST){
                service.removeFile(input);
                System.out.println(">-> File was removed...");
                System.out.println("> Main menu");
            }else{
                System.out.println(">-> File not found");
            }

        }else{
            System.out.println(">-> You will be returned to the main menu.");
        }
    }

    // TODO App format disk
    public void formatDisk(){
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