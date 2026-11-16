package lesson34.example;

public class Test {
    public static void main(String[] args) {
        int[] testArr = {1, 2, 3};
        System.out.println(sum(testArr));
        System.out.println(sum());
        System.out.println(sum(1, 2));
        System.out.println(sum(1, 2, 3, 4));
        System.out.println(sum(1, 2, 3, 4, 55, 7, 8));

    }

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }


        return total;
    }



}
