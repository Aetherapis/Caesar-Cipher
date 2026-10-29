package CryptoTool;

public class Caesar {
    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Main.chars.indexOf(text.charAt(i)) == -1)
                encryptedText.append(text.charAt(i));
            else {
                encryptedText.append(Main.chars.charAt((Main.chars.indexOf(text.charAt(i)) + shift) % Main.chars.length()));
                shift++;
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String content, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            if (Main.chars.indexOf(content.charAt(i)) == -1)
                decryptedText.append(content.charAt(i));
            else {
                decryptedText.append(Main.chars.charAt(((Main.chars.indexOf(content.charAt(i)) - shift) % Main.chars.length() + Main.chars.length()) % Main.chars.length()));
                shift++;
            }
        }
        return decryptedText.toString();
    }
}