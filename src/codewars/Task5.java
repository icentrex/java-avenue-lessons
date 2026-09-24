package codewars;

public class Task5 {
    /*
    Given a string of digits,
    you should replace any digit below 5 with '0'
    and any digit 5 and above with '1'. Return the resulting string.

    Note: input will never be an empty string
     */

    public static String fakeBin(String numberString) {
        StringBuilder newString = new StringBuilder(numberString);

        for (int charIndex = 0; charIndex < newString.length(); charIndex++) {
            if ((Integer.parseInt(String.valueOf(newString.charAt(charIndex)))) < 5) {
                newString.setCharAt(charIndex, '0');
            } else {
                newString.setCharAt(charIndex, '1');
            }
        }
        return newString.toString();
        //или
        //return numberString.replaceAll("[0-4]", "0").replaceAll("[5-9]", "1");
        //или
        //final char c[] = numberString.toCharArray();
        //        for (int i = 0; i < c.length; i++)
        //          c[i] = c[i] < '5' ? '0' : '1';
        //        return new String(c);
    }

    public static void main(String[] args) {
        System.out.println(Task5.fakeBin("509321967506747"));
    }
}
