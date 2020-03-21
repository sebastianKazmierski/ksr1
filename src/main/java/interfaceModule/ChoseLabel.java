package interfaceModule;

import java.util.List;
import java.util.Scanner;

public class ChoseLabel {
    Scanner in;
    List<? extends Enum> labels;

    public ChoseLabel(Scanner in, List<? extends Enum> labels) {
        this.in = in;
        this.labels = labels;
    }

    public ChoseLabel(Scanner in) {
        this.in = in;
    }

    public String getLabel()  {
        String userChoice = getUserChoice().toLowerCase();
        while (!userChoice.equals("p") && !userChoice.equals("i"))
        {
            userChoice = getCorrectUserChoice().toLowerCase();
        }
        return userChoice;
    }

    private String getUserChoice() {
        this.labels.forEach(System.out::println);
        System.out.println("Wybierz etykietę według której chcesz wykonać grupowania: (P - places, I - inne )");
        System.out.print("> ");
        return this.in.nextLine().trim();
    }

    private String getCorrectUserChoice() {
        System.out.println();
        System.out.println(":( Podana wartość jest nie prawidłowa. Spróbuj jeszcze raz");
        System.out.println();
        return getUserChoice();
    }
}
