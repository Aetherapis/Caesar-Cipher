package CryptoTool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadAndWrite {
    public static String read(Path filePath) {
        String content = "";
        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("An Error occurred " + e.getMessage());
        }
        return content;
    }
    public static void write(Path filePath, String content, String prefix){
        String newFileName = prefix + filePath.getFileName().toString();
        Path editedFilePath = (filePath.getParent() != null) ? filePath.getParent().resolve(newFileName) : Path.of(newFileName);
        try {
            Files.writeString(editedFilePath, content);
        } catch (IOException e) {
            System.out.println("An Error occurred " + e.getMessage());
        }
    }
    public static Path fileValidation(Scanner scanner) {
        while (true) {
            System.out.print("Enter the file name (make sure it is not empty)\n>>> ");
            String input = scanner.nextLine().trim();
            try {
                Path filePath = Path.of(input);
                if (Files.exists(filePath) && !Files.isDirectory(filePath) && !read(filePath).isBlank())
                    return filePath.toAbsolutePath();
            } catch (InvalidPathException e) {
                System.out.println("That's not a valid path: " + e.getMessage());
            }
        }
    }
}

