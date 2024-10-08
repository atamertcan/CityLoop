import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static ListIterator<Town> iterator;

    public static void main(String[] args) {

        Town[] towns = {new Town("Sydney", 0),
                        new Town("Adelaide", 1374),
                        new Town("Alice Springs",2771),
                        new Town("Brisbane",917),
                        new Town("Darwin",3972),
                        new Town("Melbourne",877),
                        new Town("Perth",3923),
                        new Town("Adana",14298)};

        LinkedList<Town> locations = new LinkedList<Town>();

        iterator = locations.listIterator();

        for (Town town : towns){
            if (!locations.contains(town)){
                locations.add(town);
            }
        }

        int indexForwardBackward = 0;
        int index = 0;
        boolean flag = true;

        while(flag){

            printMenu();

            switch (sc.nextLine().toLowerCase()){
                case "f", "forward":
                    try{

                        forward(locations, indexForwardBackward);
                    }catch (IndexOutOfBoundsException | NoSuchElementException e){
                        System.out.println("You are out of loop.");
                        flag = false;
                    }

                    indexForwardBackward++;
                    break;


                case "b", "backward":
                    try{

                        backward(locations, indexForwardBackward);
                        indexForwardBackward--;
                    }catch (IndexOutOfBoundsException | NoSuchElementException e){
                        System.out.println("You are out of loop.");
                        flag = false;
                    }
                    break;

                case "l", "list places":
                    index = iterator.nextIndex();
                    listPlaces(locations);
                    iterator = locations.listIterator(index);
                    break;

                case "m", "menu":
                    break;

                case "q", "quit":
                    flag = false;
                    break;

                default:
                    System.out.println("Invalid Value");
                    break;
            }

        }

    }

    private static void printMenu(){

        System.out.println("""
                Available actions (Select word or letter):
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit
                """);

        System.out.println("-".repeat(30));

    }

    private static void forward(LinkedList<Town> list, int index){

        var forwardIterator = list.listIterator(index);

        if (forwardIterator.hasNext()){
            Town currentTown = forwardIterator.next();
            System.out.println("You are currently at " + currentTown.getName() +
                    ", distance is " + currentTown.getDistance() + "km.");

        }
        else{
            System.out.println("You are out of loop.");

        }

        System.out.println("-".repeat(30));
    }

    private static void backward(LinkedList<Town> list, int index){

        var backwardIterator = list.listIterator(index);

        if (backwardIterator.hasPrevious()){
            Town currentTown = backwardIterator.previous();
            System.out.println("You are currently at " + currentTown.getName() +
                    ", distance is " + currentTown.getDistance() + "km.");

        }
        else{

            System.out.println("You are out of loop.");
        }

        System.out.println("-".repeat(30));
    }

    private static void listPlaces(LinkedList<Town> list){
        iterator = list.listIterator();
        Town currentTown;

        System.out.println("-".repeat(30));

        System.out.print("City");
        System.out.print(" ".repeat(10));
        System.out.println("| Distance");
        System.out.println("#".repeat(30));

        while(iterator.hasNext()){

            currentTown = iterator.next();
            int space = 0;

            System.out.printf("%s", currentTown.getName());
            if(currentTown.getName().length() < 14){
                space = 14 - currentTown.getName().length();

                System.out.print(" ".repeat(space));
            }
            System.out.print("| ");
            System.out.printf("%skm", currentTown.getDistance());
            System.out.println(" ");
        }

        System.out.println("-".repeat(30));

    }

}
