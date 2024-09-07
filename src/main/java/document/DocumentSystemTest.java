package document;

public class DocumentSystemTest {

    public static void main(String[] args) {
        testDocumentSystem();
    }

    public static void testDocumentSystem() {
        DocumentSystem documentSystem = new DocumentSystem();
        Document doc = documentSystem.createDocument();

        System.out.println("1. Initial document:");
        printDocumentState(doc);
        System.out.println("Expected: \"\" (empty string)\n");

        // Append some text
        documentSystem.append(doc, "First line");
        System.out.println("2. After appending 'First line':");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\n\"\n");

        documentSystem.append(doc, "Second line");
        System.out.println("3. After appending 'Second line':");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\n\"\n");

        documentSystem.append(doc, "Third line");
        System.out.println("4. After appending 'Third line':");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\nThird line\\n\"\n");

        // Undo operations
        documentSystem.undo(doc);
        System.out.println("5. After first undo:");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\n\"\n");

        documentSystem.undo(doc);
        System.out.println("6. After second undo:");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\n\"\n");

        // Redo operations
        documentSystem.redo(doc);
        System.out.println("7. After first redo:");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\n\"\n");

        // Append after undo
        documentSystem.append(doc, "New third line");
        System.out.println("8. After appending 'New third line':");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\nNew third line\\n\"\n");

        // Try to redo (should not change the document)
        documentSystem.redo(doc);
        System.out.println("9. After attempting redo (should be unchanged):");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\nNew third line\\n\"\n");

        // Final undo
        documentSystem.undo(doc);
        System.out.println("10. After final undo:");
        printDocumentState(doc);
        System.out.println("Expected: \"First line\\nSecond line\\n\"\n");
    }

    private static void printDocumentState(Document doc) {
        System.out.println("Document content:");
        System.out.println("\"" + doc.toString().replace("\n", "\\n") + "\"");
        System.out.println("--------------------");
    }
}