import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class EmployeeReview {
    

    public EmployeeReview(){
        
        JFrame frame = new JFrame("Review Employee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        String[] columns = {"Name", "Department", "Weekly Task Status", "Rating"};
        Object[][] data = {
            {"Matthew", "IT", "done early", "100%"},
            {"Rachel", "Marketing", "done early", "100%"},
            {"Vanessa", "Sales", "done early", "100%"},
            {"Jonalyn", "HR", "done early", "100%"},
            {"Enzo", "Finance", "done early", "100%"},
            {"Helaena", "Customer Service", "done early", "100%"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton reviewButton = new JButton("Review");
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    Object[] rowData = new Object[table.getColumnCount()];
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        rowData[i] = table.getValueAt(selectedRow, i);
                    }
                    openReviewFrame(model, selectedRow, rowData);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a row first.");
                }
            }
        });

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(reviewButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void openReviewFrame(DefaultTableModel model, int rowIndex, Object[] rowData) {
        JFrame reviewFrame = new JFrame("Review and Edit");
        reviewFrame.setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(rowData.length + 1, 2));
        JTextField[] textFields = new JTextField[rowData.length];

        for (int i = 0; i < rowData.length; i++) {
            panel.add(new JLabel(model.getColumnName(i)));
            textFields[i] = new JTextField(rowData[i].toString());
            panel.add(textFields[i]);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < textFields.length; i++) {
                    model.setValueAt(textFields[i].getText(), rowIndex, i);
                }
                reviewFrame.dispose();
            }
        });

        panel.add(submitButton);
        reviewFrame.add(panel);
        reviewFrame.setVisible(true);
    }
}
