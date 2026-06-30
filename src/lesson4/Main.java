package lesson4;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[10];

        for (int index = 0; index < numbers.length; index++) {
            System.out.println(numbers[index]);
        }

        int[] numbers2 = {3, 5, 8, 3, 55, 4, 1, 65, 36, 98};

        for (int index = numbers2.length - 1; index >= 0; index--) {
            System.out.println(numbers2[index]);
        }
        System.out.println("Содержат символ 5");

        for (int index = 0; index < numbers2.length; index++) {
            if (String.valueOf(numbers2[index]).contains("5")) {
                System.out.println(numbers2[index]);
            }
        }

        int[][] table = new int[8][10];

        int[][] arrayOfNumbers = {
                {1, 6, 3, 4, 5, 7},
                {12, 54, 3, 12, 4, 3},
                {1, 5, 2, 53, 5, 3},
                {7, 54, 23, 54, 23},
                {25, 4534, 4, 3, 23, 32},
                {234, 5, 3, 1, 6, 3}
        };
        for (int rowIndex = 0; rowIndex < arrayOfNumbers.length; rowIndex++) {
            for (int colIndex = 0; colIndex < arrayOfNumbers[rowIndex].length; colIndex++) {
                if (rowIndex == colIndex) {
                    System.out.print(arrayOfNumbers[rowIndex][colIndex]);
                }
                if (arrayOfNumbers[rowIndex][colIndex] >= 10 && arrayOfNumbers[rowIndex][colIndex] <= 99) {
                    System.out.print("- ");
                }
                if (arrayOfNumbers[rowIndex][colIndex] >= 0 && arrayOfNumbers[rowIndex][colIndex] <= 9) {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
