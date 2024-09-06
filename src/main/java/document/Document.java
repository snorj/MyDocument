package document;

import java.util.ArrayDeque;
import java.util.Deque;

public class Document {
    private String text = "";
    private final double margin;
    private final String author;
    private Deque<DocumentMemento> stackForUndo = new ArrayDeque<>();
    private Deque<DocumentMemento> stackForRedo = new ArrayDeque<>();

    public Document(double margin, String author) {
        this.margin = margin;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public void append(String text) {
        stackForUndo.push(createMemento());
        this.text += text + "\n";
        stackForRedo.clear(); // Clear redo stack on new change
    }

    public void undo() {
        if (!stackForUndo.isEmpty()) {
            stackForRedo.push(createMemento());
            changeState(stackForUndo.pop());
        }
    }

    public void redo() {
        if (!stackForRedo.isEmpty()) {
            stackForUndo.push(createMemento());
            changeState(stackForRedo.pop());
        }
    }

    private DocumentMemento createMemento() {
        return new DocumentMemento(this.text);
    }

    private void changeState(DocumentMemento documentMemento) {
        this.text = documentMemento.getText();
    }
}