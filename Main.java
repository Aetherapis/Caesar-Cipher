package CryptoTool;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final String chars = "R=$jHU-kN6@XQGz5fI(dl|D4J&n<bcyh9K`%? {/1\\Mt>m}\"7W!S*_Tu8ixg3vOVL#.:w0\n2C['A;YZ+],rBFeP)aEopqs~^";
    static final String letters = "abcdefghijklmnopqrstuvwxyz";
    static final double[] EnglishFreq = {
            8.167, 1.492, 2.782, 4.253, 12.702, 2.228, 2.015, 6.094, 6.966, 0.153,
            0.772, 4.025, 2.406, 6.749, 7.507, 1.929, 0.095, 5.987, 6.327, 9.056,
            2.758, 0.978, 2.360, 0.150, 1.974, 0.074};
    void main() {
        Scanner scanner = new Scanner(System.in);
        boolean startAgain = true;
        System.out.println("Hello to Crypto Tool");
        while (startAgain) {
            System.out.print("Do you want to\n[1] Encrypt\n[2] Decrypt\n[3] Crack\n>>> ");
            int choice = choiceValidation(scanner, scanner.nextLine().trim(), 3);
            Path filePath = ReadAndWrite.fileValidation(scanner);
            String fileContent = ReadAndWrite.read(filePath);
            int shift;
            switch (choice) {
                case 1:
                    String encryptedContent;
                    System.out.print("Enter the shift value\n>>> ");
                    shift = choiceValidation(scanner, scanner.nextLine().trim(), chars.length() - 1);
                    encryptedContent = Caesar.encrypt(fileContent, shift);
                    ReadAndWrite.write(filePath, encryptedContent, "Encrypted_");
                    System.out.println("Encryption Done.");
                    break;
                case 2:
                    String decryptedContent;
                    System.out.print("Enter the shift value\n>>> ");
                    shift = choiceValidation(scanner, scanner.nextLine().trim(), chars.length() - 1);
                    decryptedContent = Caesar.decrypt(fileContent, shift);
                    ReadAndWrite.write(filePath, decryptedContent, "Decrypted_");
                    System.out.println("Decryption Done.");
                    break;
                case 3:
                    double[] chiSqrValues = new double[chars.length()];
                    for (int i = 0; i < chiSqrValues.length; i++) {
                        chiSqrValues[i] = Crack.crack(Caesar.decrypt(fileContent, i));
                    }
                    int minNumIndex = Crack.minNumber(chiSqrValues);
                    ReadAndWrite.write(filePath, Caesar.decrypt(fileContent, minNumIndex), "Cracked_");
                    System.out.println("Cracking Done.");
            }
            System.out.print("Would you like to do another operation?\n[1] Yes\n[2] No\n>>> ");
            startAgain = choiceValidation(scanner, scanner.nextLine().trim(), 2) == 1;

        }
    }
    public static int choiceValidation(Scanner scanner, String choice, int numOfChoices) {
        String[] range = new String[numOfChoices];
        for (int i = 0; i < numOfChoices; i++)
            range[i] = String.valueOf(i + 1);
        while (!Arrays.asList(range).contains(choice)) {
            System.out.printf("Invalid choice, Please choose between [1 - %s]\n>>> ", range[range.length - 1]);
            choice = scanner.nextLine().trim();
        }
        return Integer.parseInt(choice);
    }

}