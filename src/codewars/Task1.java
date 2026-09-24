package codewars;

public class Task1 {
    public static void main(String[] args) {
        //Дана строка, верни количество гласных (a, e, i, o, u) в ней.
        String stringTest = "abracadabra";
        char[] checkVowelArray = {'a', 'e', 'i', 'o', 'u'};
        int charInStringCount = 0;


        for (char c : checkVowelArray) {
            for (int charIndex = 0; charIndex < stringTest.length(); charIndex++) {
                if (stringTest.charAt(charIndex) == c) {
                    charInStringCount++;
                }
            }
            System.out.println("Cимвол '" + c + "' встречается в строке " +
                    charInStringCount + " раз");
            charInStringCount = 0;
        }
    }
}
