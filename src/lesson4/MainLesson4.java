package lesson4;

public class MainLesson4 {
    public static void main(String[] args) {
        //Домашнее задание
        //1) Создать массив 6 на 6 сразу с числами (сразу с числами)
        //2) Перезаписать во все ячейки нули (0)
        //3) Вывести диагональ снизу вверх слева направо
        //4) Вывести в шахматном порядке
        int[][] arrayOfNumbers = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {6, 5, 4, 3, 2, 1},
                {0, 9, 8, 7, 6, 5},
                {5, 6, 7, 8, 9, 0},
                {4, 5, 6, 7, 8, 9},
                {5, 3, 4, 6, 8, 12}
        };

        System.out.println("1) Создать массив 6 на 6 сразу с числами");
        for (int rowIndex = 0; rowIndex < arrayOfNumbers.length; rowIndex++) {
            for (int colIndex = 0; colIndex < arrayOfNumbers[rowIndex].length; colIndex++) {
                System.out.print(arrayOfNumbers[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }

        System.out.println("\n2) Перезаписать во все ячейки нули (0)");
        for (int rowIndex = 0; rowIndex < arrayOfNumbers.length; rowIndex++) {
            for (int colIndex = 0; colIndex < arrayOfNumbers[rowIndex].length; colIndex++) {
                arrayOfNumbers[rowIndex][colIndex] = 0;
                System.out.print(arrayOfNumbers[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }

        System.out.println("\n3) Диагональ снизу вверх слева направо или сверху вниз справа налево");
        for (int rowIndex = 0; rowIndex < arrayOfNumbers.length; rowIndex++) {
            for (int colIndex = 0; colIndex < arrayOfNumbers[rowIndex].length; colIndex++) {
                if ((colIndex + rowIndex) == arrayOfNumbers[rowIndex].length - 1) {
                    System.out.print(arrayOfNumbers[rowIndex][colIndex] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }

        System.out.println("\n4) Вывести в шахматном порядке");
        for (int rowIndex = 0; rowIndex < arrayOfNumbers.length; rowIndex++) {
            for (int colIndex = 0; colIndex < arrayOfNumbers[rowIndex].length; colIndex++) {
                //2 вариант условия - выбираем либо четные клетки, либо нечетные
                //if((colIndex + rowIndex) % 2 == 0)
                if (rowIndex % 2 == 0) {
                    if (colIndex % 2 == 0) {
                        System.out.print(arrayOfNumbers[rowIndex][colIndex] + " ");
                    } else {
                        System.out.print(". ");
                    }
                } else {
                    if (colIndex % 2 != 0) {
                        System.out.print(arrayOfNumbers[rowIndex][colIndex] + " ");
                    } else {
                        System.out.print(". ");
                    }
                }
            }
            System.out.println();
        }
    }
}
