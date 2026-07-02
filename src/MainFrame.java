import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * The single top-level window of the application. Built around a JTabbedPane
 * with five panels: Dashboard, Register Student, Record Payment,
 * Payment History and Student Report/Search.
 *
 * The window owns one FeeManager instance which every panel shares, so data
 * entered in one tab is instantly visible in the others (e.g. a payment
 * recorded in "Record Payment" updates the balance shown in "Dashboard").
 */
public class MainFrame extends JFrame {

    private static final Color PRIMARY = new Color(21, 87, 61);
    private static final Color ACCENT = new Color(224, 168, 47);
    private static final Color LIGHT_BG = new Color(245, 247, 245);

    private final FeeManager feeManager;

    // Dashboard labels kept as fields so refreshDashboard() can update them
    private JLabel totalStudentsLbl;
    private JLabel totalCollectedLbl;
    private JLabel totalOutstandingLbl;
    private JLabel defaultersLbl;

    private DefaultTableModel studentTableModel;
    private DefaultTableModel historyTableModel;

    public MainFrame(FeeManager feeManager) {
        super("School Fees Payment Tracking System - St. John's School. Sekondi");
        this.feeManager = feeManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 640);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(880, 560));

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 13));
        tabs.addTab("Dashboard", buildDashboardPanel());
        tabs.addTab("Register Student", buildRegisterPanel());
        tabs.addTab("Record Payment", buildPaymentPanel());
        tabs.addTab("Payment History", buildHistoryPanel());
        tabs.addTab("Student Report", buildReportPanel());

        add(headerPanel(), BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        refreshDashboard();
        refreshStudentTable();
        refreshHistoryTable();
    }

    // ---------------------------------------------------------------- header
    private JPanel headerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("School Fees Payment Tracking System");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Accountability is Key");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(new Color(230, 230, 220));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(title);
        textPanel.add(subtitle);

        panel.add(textPanel, BorderLayout.WEST);
        return panel;
    }

    // ------------------------------------------------------------ dashboard
    private JPanel buildDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(LIGHT_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel cards = new JPanel(new GridLayout(1, 4, 15, 0));
        cards.setOpaque(false);

        totalStudentsLbl = new JLabel("0");
        totalCollectedLbl = new JLabel("GHS 0.00");
        totalOutstandingLbl = new JLabel("GHS 0.00");
        defaultersLbl = new JLabel("0");

        cards.add(statCard("Total Students", totalStudentsLbl));
        cards.add(statCard("Total Collected", totalCollectedLbl));
        cards.add(statCard("Total Outstanding", totalOutstandingLbl));
        cards.add(statCard("Students in Arrears", defaultersLbl));

        panel.add(cards, BorderLayout.NORTH);

        JButton refreshBtn = new JButton("Refresh Dashboard");
        styleButton(refreshBtn);
        refreshBtn.addActionListener((ActionEvent e) -> refreshDashboard());

        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        south.setOpaque(false);
        south.add(refreshBtn);
        panel.add(south, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel statCard(String label, JLabel valueLabel) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        valueLabel.setForeground(PRIMARY);
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel caption = new JLabel(label);
        caption.setFont(new Font("SansSerif", Font.PLAIN, 12));
        caption.setForeground(Color.GRAY);
        caption.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(valueLabel);
        card.add(Box.createRigidArea(new Dimension(0, 6)));
        card.add(caption);
        return card;
    }

    private void refreshDashboard() {
        totalStudentsLbl.setText(String.valueOf(feeManager.getAllStudents().size()));
        totalCollectedLbl.setText(String.format("GHS %.2f", feeManager.getTotalCollected()));
        totalOutstandingLbl.setText(String.format("GHS %.2f", feeManager.getTotalOutstanding()));
        defaultersLbl.setText(String.valueOf(feeManager.countDefaulters()));
    }

    // ------------------------------------------------------- register panel
    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField idField = new JTextField(15);
        JTextField nameField = new JTextField(15);
        JTextField contactField = new JTextField(15);
        JTextField programField = new JTextField(15);
        JTextField levelField = new JTextField(15);
        JTextField feeField = new JTextField(15);

        addFormRow(form, gc, 0, "Student ID:", idField);
        addFormRow(form, gc, 1, "Full Name:", nameField);
        addFormRow(form, gc, 2, "Contact Number:", contactField);
        addFormRow(form, gc, 3, "Programme:", programField);
        addFormRow(form, gc, 4, "Level / Year:", levelField);
        addFormRow(form, gc, 5, "Total Fees Due (GHS):", feeField);

        JButton registerBtn = new JButton("Register Student");
        styleButton(registerBtn);
        gc.gridx = 1;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.EAST;
        form.add(registerBtn, gc);

        registerBtn.addActionListener((ActionEvent e) -> {
            try {
                String id = requireNonEmpty(idField.getText(), "Student ID");
                String name = requireNonEmpty(nameField.getText(), "Full Name");
                String contact = requireNonEmpty(contactField.getText(), "Contact Number");
                String program = requireNonEmpty(programField.getText(), "Programme");
                String level = requireNonEmpty(levelField.getText(), "Level / Year");
                double fee = parsePositiveDouble(feeField.getText(), "Total Fees Due");

                feeManager.registerStudent(new Student(id, name, contact, program, level, fee));

                JOptionPane.showMessageDialog(this, "Student registered successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                idField.setText(""); nameField.setText(""); contactField.setText("");
                programField.setText(""); levelField.setText(""); feeField.setText("");

                refreshDashboard();
                refreshStudentTable();
            } catch (DuplicateStudentException | InvalidAmountException ex) {
                showError(ex.getMessage());
            } catch (NumberFormatException ex) {
                showError("Total Fees Due must be a valid number.");
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    // -------------------------------------------------------- payment panel
    private JPanel buildPaymentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField studentIdField = new JTextField(15);
        JTextField amountField = new JTextField(15);
        String[] methods = {"Cash", "Mobile Money", "Bank Transfer"};
        JComboBox<String> methodBox = new JComboBox<>(methods);

        // Extra fields whose visibility depends on the chosen method
        JTextField extra1 = new JTextField(15); // cashier / network / bank name
        JTextField extra2 = new JTextField(15); // (unused for cash) momo number / transfer reference
        JTextField extra3 = new JTextField(15); // (unused for cash/bank) transaction id

        JLabel extra1Lbl = new JLabel("Cashier Name:");
        JLabel extra2Lbl = new JLabel("Mobile Number:");
        JLabel extra3Lbl = new JLabel("Transaction ID:");

        addFormRow(form, gc, 0, "Student ID:", studentIdField);
        addFormRow(form, gc, 1, "Amount (GHS):", amountField);
        addFormRow(form, gc, 2, "Payment Method:", methodBox);
        addFormRow(form, gc, 3, extra1Lbl.getText(), extra1);
        addFormRow(form, gc, 4, extra2Lbl.getText(), extra2);
        addFormRow(form, gc, 5, extra3Lbl.getText(), extra3);

        methodBox.addActionListener(e -> {
            String selected = (String) methodBox.getSelectedItem();
            if ("Cash".equals(selected)) {
                extra1Lbl.setText("Cashier Name:");
                extra2.setEnabled(false);
                extra3.setEnabled(false);
            } else if ("Mobile Money".equals(selected)) {
                extra1Lbl.setText("Network (MTN/Telecel/AirtelTigo):");
                extra2Lbl.setText("Mobile Number:");
                extra3Lbl.setText("Transaction ID:");
                extra2.setEnabled(true);
                extra3.setEnabled(true);
            } else {
                extra1Lbl.setText("Bank Name:");
                extra2Lbl.setText("Transfer Reference:");
                extra2.setEnabled(true);
                extra3.setEnabled(false);
            }
        });

        JButton payBtn = new JButton("Record Payment");
        styleButton(payBtn);
        gc.gridx = 1;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.EAST;
        form.add(payBtn, gc);

        payBtn.addActionListener((ActionEvent e) -> {
            try {
                String studentId = requireNonEmpty(studentIdField.getText(), "Student ID");
                double amount = parsePositiveDouble(amountField.getText(), "Amount");
                String method = (String) methodBox.getSelectedItem();

                Payment payment;
                if ("Cash".equals(method)) {
                    String cashier = requireNonEmpty(extra1.getText(), "Cashier Name");
                    payment = new CashPayment(studentId, amount, cashier);
                } else if ("Mobile Money".equals(method)) {
                    String network = requireNonEmpty(extra1.getText(), "Network");
                    String number = requireNonEmpty(extra2.getText(), "Mobile Number");
                    String txnId = requireNonEmpty(extra3.getText(), "Transaction ID");
                    payment = new MobileMoneyPayment(studentId, amount, network, number, txnId);
                } else {
                    String bank = requireNonEmpty(extra1.getText(), "Bank Name");
                    String ref = requireNonEmpty(extra2.getText(), "Transfer Reference");
                    payment = new BankTransferPayment(studentId, amount, bank, ref);
                }

                feeManager.recordPayment(payment);

                JOptionPane.showMessageDialog(this,
                        "Payment recorded.\nReceipt No: " + payment.getReceiptNumber(),
                        "Payment Successful", JOptionPane.INFORMATION_MESSAGE);

                studentIdField.setText(""); amountField.setText("");
                extra1.setText(""); extra2.setText(""); extra3.setText("");

                refreshDashboard();
                refreshStudentTable();
                refreshHistoryTable();
            } catch (StudentNotFoundException | InvalidAmountException | OverpaymentException ex) {
                showError(ex.getMessage());
            } catch (NumberFormatException ex) {
                showError("Amount must be a valid number.");
            } catch (IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });

        panel.add(form, BorderLayout.NORTH);
        return panel;
    }

    // -------------------------------------------------------- history panel
    private JPanel buildHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);

        historyTableModel = new DefaultTableModel(
                new Object[]{"Receipt No", "Student ID", "Amount (GHS)", "Method", "Details", "Date/Time"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(historyTableModel);
        table.setRowHeight(24);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        styleButton(refreshBtn);
        refreshBtn.addActionListener(e -> refreshHistoryTable());
        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        south.setOpaque(false);
        south.add(refreshBtn);
        panel.add(south, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshHistoryTable() {
        historyTableModel.setRowCount(0);
        for (Payment p : feeManager.getAllPayments()) {
            historyTableModel.addRow(new Object[]{
                    p.getReceiptNumber(), p.getStudentId(),
                    String.format("%.2f", p.getAmount()),
                    p.getPaymentMethod(), p.getPaymentDetails(), p.getFormattedDate()
            });
        }
    }

    // --------------------------------------------------------- report panel
    private JPanel buildReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(LIGHT_BG);

        studentTableModel = new DefaultTableModel(
                new Object[]{"Student ID", "Name", "Programme", "Level", "Total Due", "Paid", "Balance", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(studentTableModel);
        table.setRowHeight(24);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search by Student ID");
        JButton showAllBtn = new JButton("Show All");
        styleButton(searchBtn);
        styleButton(showAllBtn);

        searchBtn.addActionListener(e -> {
            try {
                String id = requireNonEmpty(searchField.getText(), "Student ID");
                Student s = feeManager.findStudent(id);
                studentTableModel.setRowCount(0);
                addStudentRow(s);
            } catch (StudentNotFoundException | IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });
        showAllBtn.addActionListener(e -> refreshStudentTable());

        searchPanel.add(new JLabel("Student ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        searchPanel.add(showAllBtn);

        panel.add(searchPanel, BorderLayout.NORTH);
        return panel;
    }

    private void refreshStudentTable() {
        studentTableModel.setRowCount(0);
        List<Student> students = feeManager.getAllStudents();
        for (Student s : students) {
            addStudentRow(s);
        }
    }

    private void addStudentRow(Student s) {
        FeeInvoice inv = s.getInvoice();
        studentTableModel.addRow(new Object[]{
                s.getId(), s.getFullName(), s.getProgram(), s.getLevel(),
                String.format("%.2f", inv.getTotalFeesDue()),
                String.format("%.2f", inv.getAmountPaid()),
                String.format("%.2f", inv.getBalance()),
                inv.isFullyPaid() ? "Fully Paid" : "Outstanding"
        });
    }

    // ------------------------------------------------------------- helpers
    private void addFormRow(JPanel form, GridBagConstraints gc, int row, String label, JComponent field) {
        gc.gridx = 0;
        gc.gridy = row;
        gc.anchor = GridBagConstraints.WEST;
        form.add(new JLabel(label), gc);
        gc.gridx = 1;
        form.add(field, gc);
    }

    private void styleButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private String requireNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    private double parsePositiveDouble(String value, String fieldName) throws InvalidAmountException {
        double parsed;
        try {
            parsed = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException(fieldName + " must be a valid number.");
        }
        if (parsed <= 0) {
            throw new InvalidAmountException(fieldName + " must be greater than zero.");
        }
        return parsed;
    }
}
