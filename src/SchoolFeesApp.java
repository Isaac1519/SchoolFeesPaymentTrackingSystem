import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Application entry point. Sets a native-looking Look and Feel, seeds the
 * shared FeeManager with sample data, and launches the main GUI window
 * on the Event Dispatch Thread (as required for all Swing applications).
 */
public class SchoolFeesApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Falls back to the default cross-platform Look and Feel.
        }

        FeeManager feeManager = new FeeManager();
        feeManager.loadSampleData();

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(feeManager);
            frame.setVisible(true);
        });
    }
}
