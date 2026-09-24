package codewars;

public class Task2 {
    /*
    Complete the solution so that it reverses the string passed into it.
    'world'  =>  'dlrow'
    'word'   =>  'drow'
    */

    public static String solution(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(Task2.solution("world"));

    }
}
