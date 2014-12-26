package main;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
/**
 * Created by Andrey on 26.12.2014.
 */
public class MyTableExample3 extends JFrame {

    private JScrollPane scrollPane;
    private JTable table;

    public MyTableExample3() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                            .getTableCellRendererComponent(this, this.getValueAt(
                                    row, col), false, false, row, col);
                } else {
                    return super.prepareRenderer(renderer, row, col);
                }
            }
        };
        table.setAutoCreateRowSorter(false);
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {"Row 1", "Data 2", "Data 3", "Data 4", "Data 5"},
                        {"Row 2", "Data 6", "Data 7", "Data 8", "Data 9"},
                        {"Row 3", "Data 10", "Data 11", "Data 12", "Data 13"}
                },
                new String[]{
                        "", "Col 1", "Col 2", "Col 3", "Col 4"
                }));
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MyTableExample3().setVisible(true);
            }
        });
    }

    private static class HeaderRenderer implements TableCellRenderer {

        TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }
    }
}
