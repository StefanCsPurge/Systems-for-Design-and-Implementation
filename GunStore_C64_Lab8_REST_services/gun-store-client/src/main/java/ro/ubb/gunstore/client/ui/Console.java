package ro.ubb.gunstore.client.ui;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Console {

    private final Map<String,Command> commands;

    public Console(){
        commands = new HashMap<>();
    }

    public void addCommand(Command c){
        commands.put(c.getKey(),c);
    }

    private void printMenu(){
        ArrayList<String> sortedKeys = new ArrayList<>(commands.keySet());
        Collections.sort(sortedKeys);
        System.out.println("\nSelect program to run (or exit to exit)");
        sortedKeys.forEach(k -> {
            String line = String.format("%19s : %s", k, commands.get(k).getDescription());
            System.out.println(line);
        });
    }

    private void pressAnyKeyToContinue()
    {
        System.out.print("Done. Press Enter to continue...\n");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void run(){
        Scanner reader = new Scanner(System.in);

        while(true){
            printMenu();

            System.out.print("Input option: ");
            String option = reader.nextLine();
            option = option.trim();

            if(option.equals("exit"))
                break;

            Command cmd = commands.get(option);

            if (cmd == null) {
                System.out.println("Invalid option!");
                continue;
            }

            try {
                cmd.execute();
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            pressAnyKeyToContinue();
        }
    }
}


