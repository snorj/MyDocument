package document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class DocumentSystem {
    private static final String FOLDER_NAME = "output";
    private Deque<DocumentMemento> undoStack = new ArrayDeque<>();
    private Deque<DocumentMemento> redoStack = new ArrayDeque<>();

    public Document createDocument() {
        return new Document(12, "Pete");
    }

    public void append(Document document, String text) {
        undoStack.push(document.createMemento());
        document.append(text);
        redoStack.clear();
    }

    public void undo(Document document) {
        if (!undoStack.isEmpty()) {
            redoStack.push(document.createMemento());
            document.restoreFromMemento(undoStack.pop());
        }
    }

    public void redo(Document document) {
        if (!redoStack.isEmpty()) {
            undoStack.push(document.createMemento());
            document.restoreFromMemento(redoStack.pop());
        }
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        DocumentSystem system = new DocumentSystem();
        Document document = system.createDocument();
        File folder = new File(FOLDER_NAME);
        folder.mkdir();

        int i = 0;
        while (i < 3) {
            system.append(document, String.format("Line %d", i));
            i += 1;
        }

        writeDocumentToFile(document, "original.txt");

        system.undo(document);
        writeDocumentToFile(document, "1-undo.txt");
        system.undo(document);
        writeDocumentToFile(document, "2-undo.txt");

        system.redo(document);
        writeDocumentToFile(document, "3-redo.txt");
        system.redo(document);
        writeDocumentToFile(document, "4-redo.txt");
    }

    private static void writeDocumentToFile(Document document, String filename) {
        try (FileWriter writer = new FileWriter(FOLDER_NAME + "/" + filename)) {
            writer.write(document.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}