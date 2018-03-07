package uniqueWords;

import java.util.Scanner;
import java.util.Arrays;

// Donovan Moini and Ian Lizarda

public class UniqueWords {

    public static int howManyUniqueWords(String[] sentence) {
        int arrayLength = sentence.length;
        boolean[] isUnique = new boolean[arrayLength];
        Arrays.fill(isUnique, true);

        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = i + 1; j < arrayLength; j++) {
                if (sentence[i].equalsIgnoreCase(sentence[j])) {
                    isUnique[i] = false;
                    isUnique[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < arrayLength; i++) {
            if (isUnique[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a sentence.");
        String[] sentence = keyboard.nextLine().split(" ");
        keyboard.close();
        System.out.println(howManyUniqueWords(sentence));
    }
}
