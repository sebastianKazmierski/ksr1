package interfaceModule;

import java.util.Scanner;

public class ChoseNumberOfNeighbours {
    Scanner in;

    public ChoseNumberOfNeighbours(Scanner in) {
        this.in = in;
    }

    public int getNumberOfNeighbours()  {
        String userChoice = getUserChoice();
        int selectedNumbers;
        while (true) {
            try {
                selectedNumbers = parseStringToListOfInteger(userChoice);
                break;
            } catch (NumberFormatException e) {
                userChoice = getCorrectUserChoice();
            }
        }

        return selectedNumbers;
    }

    private String getUserChoice() {
        System.out.println("Podaj liczbę sąsiadów jaka ma być brana pod uwagę w algorytmie Knn: (podaj tylko jedną liczbę)");
        System.out.print("> ");
        return in.nextLine().trim();
    }

    private String getCorrectUserChoice() {
        System.out.println();
        System.out.println(":( Podana wartość jest nie prawidłowa. Spróbuj jeszcze raz");
        System.out.println();
        return getUserChoice();
    }

    public int parseStringToListOfInteger(String userChoice) throws NumberFormatException {
            int number = Integer.parseInt(userChoice);
            if (number <= 0) {
                throw new NumberFormatException();
            }
        return number;
    }
}
