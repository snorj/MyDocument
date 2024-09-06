package document;

public class DocumentMemento {
    private final String text;

    public DocumentMemento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
