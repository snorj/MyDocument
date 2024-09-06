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
    public String toString(){
        return this.text;
    }

    public void append(String text){
        this.text += text + "\n";
    }

//    We create a custom data type, a memento, with the variable that we care about
//    either undoing or redoing, text.
    public DocumentMemento createMemento(){
        return new DocumentMemento(this.text);
    }

    public void changeState(DocumentMemento documentMemento){
        this.text = documentMemento.getText();
    }
}
