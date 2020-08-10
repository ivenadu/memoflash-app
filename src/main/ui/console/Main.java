package ui.console;

public class Main {
    public static void main(String[] args) {
        try {
            MemoFlashApp app = new MemoFlashApp();
            app.runApp();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
