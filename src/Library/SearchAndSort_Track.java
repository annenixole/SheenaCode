package Library;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SearchAndSort_Track {

    // Sort by Borrower No (assuming it's in column 0)
    public void sortByBorrowerNo(DefaultTableModel tableModel) {
        quickSort(tableModel, 0); // 0 is the index for "Borrower No"
    }

    // Sort by Date (assuming it's in column 2 for Borrowed Date or 3 for Expected Return Date)
    public void sortByDate(DefaultTableModel tableModel, int dateColumnIndex) {
        quickSort(tableModel, dateColumnIndex); // Pass the date column index (2 or 3)
    }

    private void quickSort(DefaultTableModel tableModel, int columnIndex) {
        int rowCount = tableModel.getRowCount();
        if (rowCount < 2) {
            return; // No need to sort if there's 0 or 1 row
        }
        Object[][] tableData = new Object[rowCount][tableModel.getColumnCount()];

        // Copy data from table model to array for sorting
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableData[i][j] = tableModel.getValueAt(i, j);
            }
        }

        quickSort(tableData, 0, rowCount - 1, columnIndex);

        // Update the table with sorted data
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                tableModel.setValueAt(tableData[i][j], i, j);
            }
        }
    }

    private void quickSort(Object[][] data, int low, int high, int columnIndex) {
        if (low < high) {
            int pivotIndex = partition(data, low, high, columnIndex);
            quickSort(data, low, pivotIndex - 1, columnIndex);
            quickSort(data, pivotIndex + 1, high, columnIndex);
        }
    }

    private int partition(Object[][] data, int low, int high, int columnIndex) {
        Comparable pivot = (Comparable) data[high][columnIndex]; // Use the last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            Comparable currentValue = (Comparable) data[j][columnIndex];
            if (currentValue.compareTo(pivot) <= 0) {
                i++;
                // Swap data[i] and data[j]
                Object[] temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        // Swap data[i + 1] and pivot (data[high])
        Object[] temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;

        return i + 1;
    }

    // Method to get rows from the table model as an ArrayList of Object arrays
    private ArrayList<Object[]> getRowsFromTableModel(DefaultTableModel tableModel) {
        ArrayList<Object[]> rows = new ArrayList<>();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            Object[] row = new Object[columnCount];
            for (int j = 0; j < columnCount; j++) {
                row[j] = tableModel.getValueAt(i, j);
            }
            rows.add(row);
        }
        return rows;
    }

    // Method to update the table model with sorted data
    private void updateTableModel(DefaultTableModel tableModel, ArrayList<Object[]> sortedRows) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Object[] row : sortedRows) {
            tableModel.addRow(row); // Add sorted rows
        }
    }

    // Linear search to find rows based on a query
    public void linearSearch(DefaultTableModel tableModel, String query) {
        ArrayList<Object[]> rows = getRowsFromTableModel(tableModel);
        ArrayList<Object[]> resultRows = new ArrayList<>();

        // Perform linear search on all columns
        for (Object[] row : rows) {
            for (Object value : row) {
                if (value != null && value.toString().toLowerCase().contains(query.toLowerCase())) {
                    resultRows.add(row);
                    break; // Stop checking the rest of the row if a match is found
                }
            }
        }

        // Update the table with the matching rows
        updateTableModel(tableModel, resultRows);
    }
}
