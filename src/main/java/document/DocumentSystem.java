package document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DocumentSystem {

    // DO NOT MODIFY
    private static final String FOLDER_NAME = "output";

    public static Document createDocument() {
        return new Document(12, "Pete");
    }

    public static void append(Document document, String text) {
        document.append(text);
    }

    public static void undo(Document document) {
        document.undo();
    }

    public static void redo(Document document) {
        document.redo();
    }

    public static void main(String[] args) {
        // DO NOT MODIFY
        test();
    }

    public static void test() {
        // DO NOT MODIFY
        Document document = createDocument();
        File folder = new File(FOLDER_NAME);
        folder.mkdir();

        int i = 0;

        while (i < 3) {
            append(document, String.format("Line %d", i));
            i += 1;
        }

        writeDocumentToFile(document, String.format("original.txt", i));

        undo(document);
        writeDocumentToFile(document, "1-undo.txt");
        undo(document);
        writeDocumentToFile(document, "2-undo.txt");

        redo(document);
        writeDocumentToFile(document, "3-redo.txt");
        redo(document);
        writeDocumentToFile(document, "4-redo.txt");
    }

    public static void writeDocumentToFile(Document document, String filename) {
        // DO NOT MODIFY
        try (FileWriter writer = new FileWriter(FOLDER_NAME + "/" + filename)) {
            writer.write(document.toString());
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception
        }
    }
}