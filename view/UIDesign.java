package view;

import controller.TaskController;
import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UIDesign extends JFrame {
    private JTextField taskField;
    private JButton addBtn;
    private JTable table;
    private DefaultTableModel model;
    private TaskController controller;

    public UIDesign() {
        controller = new TaskController();

        setTitle("To-Do List App");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        taskField = new JTextField();
        taskField.setBounds(20, 20, 350, 30);
        add(taskField);

        addBtn = new JButton("Add");
        addBtn.setBounds(390, 20, 80, 30);
        add(addBtn);

        model = new DefaultTableModel(new Object[]{"ID", "Task", "Update", "Delete"}, 0);
        table = new JTable(model);

        // Use the inner ButtonRenderer for Update and Delete columns
        table.getColumn("Update").setCellRenderer(new ButtonRenderer());
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer());

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 70, 540, 260);
        add(pane);

        loadTasks();

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = taskField.getText().trim();
                if (!name.isEmpty()) {
                    controller.addTask(name);
                    taskField.setText("");
                    loadTasks();
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row < 0) return;

                int col = table.columnAtPoint(e.getPoint());
                int id = (int) model.getValueAt(row, 0);

                if (col == 2) { // Update button clicked
                    String currentName = (String) model.getValueAt(row, 1);
                    String newName = JOptionPane.showInputDialog(null, "Update task:", currentName);
                    if (newName != null && !newName.trim().isEmpty()) {
                        controller.updateTask(id, newName.trim());
                        loadTasks();
                    }
                } else if (col == 3) { // Delete button clicked
                    int confirm = JOptionPane.showConfirmDialog(null, "Delete this task?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.deleteTask(id);
                        loadTasks();
                    }
                }
            }
        });

        setVisible(true);
    }

    private void loadTasks() {
        model.setRowCount(0);
        List<Task> tasks = controller.getTasks();
        for (Task t : tasks) {
            model.addRow(new Object[]{t.getId(), t.getName(), "Update", "Delete"});
        }
    }

    // Inner class for button rendering in table
    private class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
}
