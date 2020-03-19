package interfaceModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChoseElementInterface<T extends ElementSelectedByUser> {
    private final static String QUESTION_FOR_MULTIPLE_CHOICE = " na podstawie któych chcesz dokonać grupowania: (podaj numery wybranych cech oddzielone spacją)";
    private final static String QUESTION_FOR_SINGLE_CHOICE = " na podstawie której/którego chcesz dokonać grupowania: (podaj tylko jeden numer)";

    Scanner in;
    TypeOfChoice typeOfChoice;


    public ChoseElementInterface(Scanner in, TypeOfChoice typeOfChoice) {
        this.in = in;
        this.typeOfChoice = typeOfChoice;
    }

    public List<T> getFeatureExtractors(List<T> featureExtractors)  {
        String[] userChoice = getUserChoiceFeatureExtractors(featureExtractors);
        List<Integer> selectedNumbers;
        while (true) {
            try {
                selectedNumbers = parseStringToListOfInteger(userChoice, featureExtractors.size() - 1);
                break;
            } catch (NumberFormatException e) {
                userChoice = getCorrectUserChoiceFeatureExtractors(featureExtractors);
            }
        }

        List<T> selectedFeatureExtractors = new ArrayList<>();
        for (Integer selectedNumber : selectedNumbers) {
            selectedFeatureExtractors.add(featureExtractors.get(selectedNumber-1));
        }
        return selectedFeatureExtractors;
    }

    public List<Integer> parseStringToListOfInteger(String[] userChoice, int maxNumber) throws NumberFormatException {
        if (typeOfChoice == TypeOfChoice.SINGLE) {
            if (userChoice.length > 1) {
                throw new NumberFormatException();
            }
        }
        List<Integer> selectedNumbers = new ArrayList<>();
        for (String numberInString : userChoice) {
            int number = Integer.parseInt(numberInString);
            if (number > maxNumber || number <= 0) {
                throw new NumberFormatException();
            }

            selectedNumbers.add(number);
        }
        return selectedNumbers;
    }

    private String[] getCorrectUserChoiceFeatureExtractors(List<T> featureExtractors) {
        System.out.println();
        System.out.println(":( Podana wartość jest nie prawidłowa. Spróbuj jeszcze raz");
        System.out.println();
        return getUserChoiceFeatureExtractors(featureExtractors);
    }

    public String[] getUserChoiceFeatureExtractors(List<T> featureExtractors) {
        for (int i = 0; i < featureExtractors.size(); i++) {
            System.out.println((i + 1) + ". " + featureExtractors.get(i).description());
        }
        //TODO  Improve this
        T elementJustToInvokeFunction = featureExtractors.get(0);
        if (typeOfChoice == TypeOfChoice.SINGLE) {
            System.out.println("Wybierz " + elementJustToInvokeFunction.wordToInsertIntoQuestionAboutThisObject() + QUESTION_FOR_SINGLE_CHOICE);
        } else {
            System.out.println("Wybierz " + elementJustToInvokeFunction.wordToInsertIntoQuestionAboutThisObject() + QUESTION_FOR_MULTIPLE_CHOICE);
        }
        System.out.print("> ");
        return in.nextLine().trim().split(" ");
    }

}
