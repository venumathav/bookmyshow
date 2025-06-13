import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class BookMyShow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookMyShow().createUI());
    }

    public void createUI() {
        JFrame frame = new JFrame("BookMyShow - Ticket Booking");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 2, 10, 10));

        // Components
        JTextField nameField = new JTextField();

        String[] movies = {"KGF 3", "SALAR 2", "SARDAR 2", "KAITHI 2", "Kalki 2898 AD 2"};
        JComboBox<String> movieBox = new JComboBox<>(movies);

        JTextField dateField = new JTextField("yyyy-MM-dd");
        JLabel dayLabel = new JLabel("Day: ");

        JSpinner ticketSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));

        JLabel amountLabel = new JLabel("Total: ₹0");

        JButton checkDateBtn = new JButton("Check Day");
        JButton bookBtn = new JButton("Book Now");

        // Add components
        frame.add(new JLabel("Name:"));
        frame.add(nameField);

        frame.add(new JLabel("Movie:"));
        frame.add(movieBox);

        frame.add(new JLabel("Date (yyyy-MM-dd):"));
        frame.add(dateField);

        frame.add(checkDateBtn);
        frame.add(dayLabel);

        frame.add(new JLabel("Tickets:"));
        frame.add(ticketSpinner);

        frame.add(new JLabel("Amount:"));
        frame.add(amountLabel);

        frame.add(new JLabel());
        frame.add(bookBtn);

        // Action: Check Day
        checkDateBtn.addActionListener(e -> {
            try {
                LocalDate date = LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                DayOfWeek day = date.getDayOfWeek();
                dayLabel.setText("Day: " + day);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format. Use yyyy-MM-dd.");
            }
        });

        // Action: Book Ticket
        bookBtn.addActionListener(e -> {
            String name = nameField.getText();
            String movie = (String) movieBox.getSelectedItem();
            String dateInput = dateField.getText();
            int tickets = (Integer) ticketSpinner.getValue();
            int pricePerTicket = 200;

            try {
                LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                DayOfWeek day = date.getDayOfWeek();
                int totalAmount = pricePerTicket * tickets;
                amountLabel.setText("Total: ₹" + totalAmount);

                // Show summary
                String summary = "Booking Confirmed!\n\n"
                        + "Name: " + name + "\n"
                        + "Movie: " + movie + "\n"
                        + "Date: " + date + " (" + day + ")\n"
                        + "Tickets: " + tickets + "\n"
                        + "Amount: ₹" + totalAmount;

                JOptionPane.showMessageDialog(frame, summary);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid date format or missing fields.");
            }
        });

        // ✅ Show the frame
        frame.setVisible(true);
    }
}
