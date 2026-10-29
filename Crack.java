package CryptoTool;

public class Crack {
    static double crack(String text) {
        int[] charCount = charCounter(text);
        int letterCount = 0;
        for (int i : charCount) letterCount += i;
        double[] expectedFreq = new double[26];
        for (int i = 0; i < Main.EnglishFreq.length; i++) {
            expectedFreq[i] = (Main.EnglishFreq[i] / 100) * letterCount;
        }
        return chiSqr(charCount, expectedFreq);
    }
    static int[] charCounter(String text) {
        text = text.toLowerCase();
        int[] charCount = new int[26];
        for (int i = 0; i < text.length(); i++) {
            if (!(Main.letters.indexOf(text.charAt(i)) == -1 ))
                charCount[text.charAt(i) - 'a']++;
        }
        return charCount;
    }

    static double chiSqr(int[] charCount, double[] expectedFreq) {
        double chiSqr = 0;
        for (int i = 0; i < charCount.length; i++)
            chiSqr += Math.pow(charCount[i] - expectedFreq[i], 2) / expectedFreq[i];
        return chiSqr;
    }

    static int minNumber(double[] numbers) {
        double minNum = numbers[0];
        int minNumIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < minNum) {
                minNum = numbers[i];
                minNumIndex = i;
            }
        }
        return minNumIndex;
    }
}