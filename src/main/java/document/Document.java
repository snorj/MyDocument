package document;

import java.util.ArrayDeque;
import java.util.Deque;

public class Document {
    private String text = "";
    private final double margin;
    private final String author;
    private Deque<DocumentMemento> undoStack = new ArrayDeque<>();
    private Deque<DocumentMemento> redoStack = new ArrayDeque<>();

    public Document(double margin, String author) {
        this.margin = margin;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public void append(String text) {
        undoStack.push(createMemento());
        this.text += text + "\n";
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(createMemento());
            restoreFromMemento(undoStack.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(createMemento());
            restoreFromMemento(redoStack.pop());
        }
    }

    private DocumentMemento createMemento() {
        return new DocumentMemento(this.text);
    }

    private void restoreFromMemento(DocumentMemento memento) {
        this.text = memento.getText();
    }
}