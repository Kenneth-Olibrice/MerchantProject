import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static ArrayList<Merchant> merchants = new ArrayList<>();
    private static int startCoin;
    private static ArrayList<Object> playerInventory = new ArrayList<>();
    private static Scanner userInput;

    public static void main(String[] args) throws FileNotFoundException {
        userInput = new Scanner(System.in);
        parseInfoFromFile(new File("Merchants.txt"));
        commerceLoop();
    }

    private static void parseInfoFromFile(File in) throws FileNotFoundException {
        if (!in.exists()) {
            System.out.println("Error at: parseInfoFromFile in class main. Error: File in does not exist!");
            System.exit(1);
        }

        Scanner reader = new Scanner(new FileInputStream(in));


        while (reader.hasNext()) {
            ArrayList<String>[] inventories = new ArrayList[2];
            inventories[0] = new ArrayList<>();
            inventories[1] = new ArrayList<>();
            int readState = -1;
            String[] buff = reader.nextLine().split(",");
            for (int i = 0; i < buff.length; i++) {
                buff[i] = buff[i].substring(1);

            }
            for (int i = 0; i < buff.length; i++) {
                if (buff[i].equalsIgnoreCase("M")) {
                    readState = 1;
                } else if (buff[i].equalsIgnoreCase("W")) {
                    readState = 0;
                } else if (readState != -1) {
                    inventories[readState].add(buff[i]);
                }
            }

            merchants.add(new Merchant(buff[0], buff[1], Short.parseShort(buff[2]), inventories[0], inventories[1]));

        }
        for (int i = 0; i < merchants.size(); i++) {
            System.out.println(merchants.get(i).getWeapons().get(0));
        }
    }

    private static void commerceLoop() {
        while (!merchants.isEmpty()) {
            int numMerchants = merchants.size();
            System.out.println("List of currently available merchants:");
            for (int i = 0; i < numMerchants; i++) {
                System.out.println(Integer.toString(i + 1) + ": " + merchants.get(i).getName() + " " + merchants.get(i).getPicture());
            }

            System.out.println("Which one would you like to do business with? Enter a number.");
            int choice = userInput.nextInt() - 1;
            if (choice > numMerchants - 1 || choice < 0) {
                System.out.println("You shouldn't have done that.");
                // Do something creepy here.
                System.exit(-666);
            }

            System.out.println("Here's what I got for you:");
            System.out.print("Weapons: ");
            for (int i = 0; i < merchants.get(choice).getWeapons().size(); i++) {
                System.out.print(merchants.get(choice).getWeapons().get(i) + ", ");
            }
            System.out.print("\n");
            System.out.print("Magic weapons: ");
            for (int i = 0; i < merchants.get(choice).getMagicWeapons().size(); i++) {
                System.out.print(merchants.get(choice).getMagicWeapons().get(i) + ", ");
            }
            System.out.print("\n");
            userInput.nextLine();
            while (!merchants.get(choice).getWeapons().isEmpty() || !merchants.get(choice).getMagicWeapons().isEmpty()) {

                if (!merchants.get(choice).getWeapons().isEmpty()) {
                    System.out.println("Would you like to buy a weapon? if so, which weapon?");
                    String weaponChoice = userInput.nextLine();
                    for (int i = 0; i < merchants.get(choice).getWeapons().size(); i++) {
                        if (merchants.get(choice).getWeapons().get(i).equalsIgnoreCase(weaponChoice)) {
                            System.out.println("That weapon is now yours, sir!");
                            merchants.get(choice).getWeapons().remove(merchants.get(choice).getWeapons().get(i));
                            break;
                        }
                    }
                }
                if(!merchants.get(choice).getMagicWeapons().isEmpty()) {
                    System.out.println("Would you like to buy a weapon? if so, which weapon?");
                    String weaponChoice = userInput.nextLine();
                    for (int i = 0; i < merchants.get(choice).getMagicWeapons().size(); i++) {
                        if (merchants.get(choice).getMagicWeapons().get(i).equalsIgnoreCase(weaponChoice)) {
                            System.out.println("That weapon is now yours, sir!");
                            merchants.get(choice).getMagicWeapons().remove(merchants.get(choice).getMagicWeapons().get(i));
                            break;
                        }
                    }
                }

            }
        }
    }
}

