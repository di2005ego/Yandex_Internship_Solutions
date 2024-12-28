import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static int calculateDigitsSum(String num) {
        int sum = 0;
        for (char digit : num.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }
        return sum;
    }

    private static void addUniqueLetters(HashSet<Character> uniqueLetters, String word) {
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                uniqueLetters.add(c);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] cand = new String[n];
        for (int i = 0; i < n; i++) {
            cand[i] = scanner.nextLine();
        }
        scanner.close();

        String[][] candParts = new String[n][6];
        for (int i = 0; i < n; i++) {
            candParts[i] = cand[i].split(",");
        }

        int[] candSums = new int[n];
        HashSet<Character> uniqueLetters = new HashSet<>();
        int[] candUniqueLetters = new int[n];
        int[] candNums = new int[n];
        for (int i = 0; i < n; i++) {
            candSums[i] = calculateDigitsSum(candParts[i][3]) + calculateDigitsSum(candParts[i][4]);
            addUniqueLetters(uniqueLetters, candParts[i][0]);
            addUniqueLetters(uniqueLetters, candParts[i][1]);
            addUniqueLetters(uniqueLetters, candParts[i][2]);
            candUniqueLetters[i] = uniqueLetters.size();
            uniqueLetters.clear();
            char firstChar = Character.toLowerCase(candParts[i][0].charAt(0));
            candNums[i] = firstChar - 'a' + 1;
        }
        int[] ciphers = new int[n];
        for (int i = 0; i < n; i++) {
            ciphers[i] = candUniqueLetters[i] + 64 * candSums[i] + 256 * candNums[i];
        }
        String[] hexCiphers = new String[n];
        for (int i = 0; i < n; i++) {
            hexCiphers[i] = Integer.toHexString(ciphers[i]);
            hexCiphers[i] = hexCiphers[i].toUpperCase();
        }
        String[] finalCiphers = new String[n];
        for (int i = 0; i < n; i++) {
            if (hexCiphers[i].length() > 3) {
                finalCiphers[i] = hexCiphers[i].substring(hexCiphers[i].length() - 3);
            }
            else {
                finalCiphers[i] = hexCiphers[i].substring(0);
                if (hexCiphers[i].length() == 2) {
                    finalCiphers[i] = "0" + finalCiphers[i];
                }
                else if (hexCiphers[i].length() == 1) {
                    finalCiphers[i] = "00" + finalCiphers[i];
                }
            }
            System.out.print(finalCiphers[i] + " ");
        }
    }
}