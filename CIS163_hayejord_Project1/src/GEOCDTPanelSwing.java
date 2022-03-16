import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**********************************************************************
 * CIS163 Project 1: Count Down Timer
 * Class GEOCDTPanelSwing, extends JPanel
 * @author Jordan Hayes
 * @version 1
 **********************************************************************/

public class GEOCDTPanelSwing extends JPanel {
//do formatting
    private GeoCountDownTimer watch;
    private Timer javaTimer;

    private JButton startButton, stopButton, continueButton;

    private JTextField yearsField, monthsField, daysField;
    private JTextField  daysParamField, dateParamField;

    private JButton loadButton, saveButton;
    private JButton daysToGoButton, futureDateButton, newDateButton;
    private JButton addButton, subtractButton;

    private JLabel daysToGoLabel, futureDateLabel, futureDate;

    private JLabel lblTime;

    public GEOCDTPanelSwing() {

        javaTimer = new Timer(1000, new TimerListener());

        setLayout(new GridLayout(0, 2));   //not specifying number of rows
        setBackground(Color.lightGray);

//////////////////////////////// idk what these do
        add (new JLabel("Original Date"));
        add (new JLabel());

        add(new JLabel("Year:"));
        yearsField = new JTextField("2022", 3);
        add(yearsField);

        add(new JLabel("Month:"));
        monthsField = new JTextField("10", 3);
        add(monthsField);

        add(new JLabel("Day:"));
        daysField = new JTextField("10", 3);
        add(daysField);


        watch = new GeoCountDownTimer(Integer.parseInt(yearsField.getText()),
                Integer.parseInt(monthsField.getText()),
                Integer.parseInt(daysField.getText()));

        startButton = new JButton("Start");
        add(startButton);

        stopButton = new JButton("Stop");
        add(stopButton);

        continueButton = new JButton("Continue");
        add(continueButton);


        //to fill in grid, add a line space
        add(new JLabel(""));


        daysToGoButton = new JButton("Days To Go");
        add(daysToGoButton);

        futureDateButton = new JButton("Future Date");
        add(futureDateButton);


        add (new JLabel("Enter new date:"));
        dateParamField = new JTextField("mm/dd/yyyy",3);
        add(dateParamField);

        newDateButton  = new JButton("Create New Date");
        add(newDateButton);

        add(new JLabel(""));

        add (new JLabel("Enter days:"));
        daysParamField = new JTextField("0",3);
        add(daysParamField);

        addButton = new JButton("Inc");
        add(addButton);

        subtractButton = new JButton("Dec");
        add(subtractButton);


        loadButton = new JButton("Load");
        add(loadButton);

        saveButton = new JButton("Save");
        add(saveButton);

        add(new JLabel("Results"));
        add(new JLabel(""));

        futureDate = (new JLabel("Future Date"));
        add(futureDate);
        futureDateLabel = new JLabel("");
        add(futureDateLabel);

        add(new JLabel("Days To Go"));
        daysToGoLabel = new JLabel("");
        add(daysToGoLabel);

        add(new JLabel("Current Date"));
        lblTime = new JLabel("");
        add(lblTime);

        stopButton.addActionListener(new ButtonListener());
        startButton.addActionListener(new ButtonListener());
        continueButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        subtractButton.addActionListener(new ButtonListener());
        daysToGoButton.addActionListener(new ButtonListener());
        futureDateButton.addActionListener(new ButtonListener());
        newDateButton.addActionListener(new ButtonListener());


    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            int mins, sec, milli, p;

            if (event.getSource() == stopButton) {
                javaTimer.stop();
            }

            if (event.getSource() == startButton) {
                try {
                    mins = Integer.parseInt(yearsField.getText());
                    sec = Integer.parseInt(monthsField.getText());
                    milli = Integer.parseInt(daysField.getText());
                    watch = new GeoCountDownTimer(mins, sec, milli);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == continueButton) {
                javaTimer.start();
            }

            if (event.getSource() == saveButton) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showSaveDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();
                    watch.save(filename);
                }
            }

            if (event.getSource() == loadButton) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showSaveDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getAbsolutePath();
                    watch.load(filename);
                }
            }



////////////////////////////////////////
            if (event.getSource() == daysToGoButton) {
                try {
                    daysToGoLabel.setText("" + watch.daysToGo(dateParamField.getText()));
                }
                catch (IllegalArgumentException io) {
                    JOptionPane.showMessageDialog(null, "Error in New Date data: Must be a valid date");
                }
            }

            if (event.getSource() == futureDateButton) {
                try {
                        futureDateLabel.setText("" + watch.daysInFuture(Integer.parseInt(daysParamField.getText())));
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in days");
                }
            }

            if (event.getSource() == newDateButton) {
                try {
                    watch = new GeoCountDownTimer(dateParamField.getText());

                    daysField.setText("" + watch.getDay());
                    monthsField.setText("" + watch.getMonth());
                    yearsField.setText("" + watch.getYear());

                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in New Date data: Must be a valid date");
                }
            }

            if (event.getSource() == addButton) {
                try {
                        watch.inc(Integer.parseInt(daysParamField.getText()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in days greater than 0");
                }
            }

            if (event.getSource() == subtractButton) {
                try {
                    watch.dec(Integer.parseInt(daysParamField.getText()));
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in days greater than 0");
                }
            }

            lblTime.setText(watch.toString());
        }
    }

    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                watch.dec(1);
                lblTime.setText(watch.toString());
            }
            catch (Exception exception) {

            }
        }
    }
}