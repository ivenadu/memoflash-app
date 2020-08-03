package ui;

import java.io.IOException;

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
