import java.util.InputMismatchException;
import java.util.Scanner;

public class Terminal {

    private final Apps app = new Apps(); // Application layer
    private final static String RESET_INPUT = ""; // Constant

    // TODO Constructor
    public Terminal() {
        this.TerminalMenu(); // Starts with printing menu
        this.TerminalChoice(); // Starts with waiting for input after menu print
    }

    // TODO Print menu
    private void TerminalMenu(){
        System.out.println("> m - Manual");
        System.out.println("> r - Remove file");
        System.out.println("> d - Dump disk");
        System.out.println("> c - Copy file");
        System.out.println("> a - Average capacity");
        System.out.println("> w - Write file");
        System.out.println("> p - Print file");
        System.out.println("> b - Bin");
        System.out.println("> f - Format disk");
        System.out.println("> e - Exit");
    }

    // TODO Call App function by input in method
    private void TerminalChoice(){
        String userIn = ""; // Input string
        Scanner userInput = new Scanner(System.in); // Console input object

        // Loop for the menu
        while (true){
            // Scan main input for "elif"
            try{
                System.out.print("< ");
                userIn = userInput.nextLine();
            }catch (InputMismatchException e){
                System.out.println("> Wrong input");
            }

            // If M for Menu
            if (userIn.equals("M") || userIn.equals("m")){
                this.TerminalMenu();
                userIn = RESET_INPUT;

                // If R for Remove file
            }else if (userIn.equals("R") || userIn.equals("r")){
                System.out.println("> Enter file name");
                try{
                    System.out.print("< ");
                    userIn = userInput.nextLine();
                    app.removeFile(userIn);
                }catch (InputMismatchException e){
                    System.out.println("> Wrong input");
                }
                userIn = RESET_INPUT;

                // If D for Dump data
            }else if (userIn.equals("D") || userIn.equals("d")){
                app.dumpDisk();
                userIn = RESET_INPUT;

                // If C for Copy file
            }else if (userIn.equals("C") || userIn.equals("c")){
                String fileName = "";
                System.out.println("> Enter source file name");
                try{
                    System.out.print("< ");
                    fileName = userInput.nextLine();
                    System.out.println("> Enter target file name");
                    userIn = userInput.nextLine();
                    app.copyFile(fileName, userIn);
                }catch (InputMismatchException e){
                    System.out.println("> Wrong input");
                }
                userIn = RESET_INPUT;

                // If A for Average capacity
            }else if (userIn.equals("A") || userIn.equals("a")){
                app.averageCapacity();
                userIn = RESET_INPUT;

                // If W for write file
            }else if (userIn.equals("W") || userIn.equals("w")){
                String fileName = "";
                System.out.println("> Enter file name");
                try{
                    System.out.print("< ");
                    fileName = userInput.nextLine();
                    System.out.println("> Enter " + fileName + " data");
                    userIn = userInput.nextLine();
                    app.writeFile(fileName, userIn);
                }catch (InputMismatchException e){
                    System.out.println("> Wrong input");
                }
                userIn = RESET_INPUT;

                // If P for print data
            }else if (userIn.equals("P") || userIn.equals("p")){
                System.out.println("> Enter file name");
                try{
                    System.out.print("< ");
                    userIn = userInput.nextLine();
                    app.print(userIn);
                }catch (InputMismatchException e){
                    System.out.println("> Wrong input");
                }
                userIn = RESET_INPUT;

                // If B for bin
            }else if (userIn.equals("B") || userIn.equals("b")){
                app.bin();
                userIn = RESET_INPUT;

                // If F for format disk
            }else if (userIn.equals("F") || userIn.equals("f")){
                app.formatDisk();
                userIn = RESET_INPUT;

                // If E break the while loop and exit
            }else if (userIn.equals("E") || userIn.equals("e")){
                System.out.println("> Thank you, bye");
                break;

                // Mismatch with "elif"
            }else{
                System.out.println("> Wrong input");
            }
        }
    }

    // to string function
    @Override
    public String toString() {
        return "Terminal{}";
    }
}