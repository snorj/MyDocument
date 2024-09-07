package document;

public class Document {
    private String text = "";
    private final double margin;
    private final String author;

    public Document(double margin, String author) {
        this.margin = margin;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public void append(String text) {
        this.text += text + "\n";
    }

    public DocumentMemento createMemento() {
        return new DocumentMemento(this.text);
    }

    public void restoreFromMemento(DocumentMemento memento) {
        this.text = memento.getText();
    }
}