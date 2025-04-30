package com.individualproject.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends FileProcessor 
// This class extends FileProcessor, which is a class that handles reading and writing to a CSV file.
{
    // RadioButtons were used as they gave an either/or option for the user to select from.
    private JLabel tempLabel, humidityLabel, timeLabel, dayLabel;
    private JRadioButton temperature1, temperature2;
    private JRadioButton humidity1, humidity2;
    private JRadioButton time1, time2;
    private JRadioButton day1, day2;
    private JRadioButton yes, no;
    private JButton Calculate, NewEntry;
    private ButtonGroup tempGroup, humidityGroup, timeGroup, dayGroup, yesNo;
    // ButtonGroups were used to group related RadioButtons together, this made it so only one of a pair could be selected at a time.


    public GUI() 
    {
        super("IsHouseOccupied.csv"); // This is how you determine what file is being read/written to.
    {
        JFrame frame = new JFrame("Predictive Model");
        frame.setSize(400, 700);
        frame.setLayout(new GridLayout(0, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding first row of buttons (Temperature)
        tempLabel = new JLabel("Temperature: ");
        temperature1 = new JRadioButton("High");
        temperature2 = new JRadioButton("Low");
        frame.add(tempLabel);
        frame.add(temperature1);
        frame.add(temperature2);
        tempGroup = new ButtonGroup();
        tempGroup.add(temperature1);
        tempGroup.add(temperature2);

        // Adding second row of buttons (Humidity)
        humidityLabel = new JLabel("Humidity: ");
        humidity1 = new JRadioButton("High");
        humidity2 = new JRadioButton("Low");
        frame.add(humidityLabel);
        frame.add(humidity1);
        frame.add(humidity2);
        humidityGroup = new ButtonGroup();
        humidityGroup.add(humidity1);
        humidityGroup.add(humidity2);

        // Adding third row of buttons (Time of Day)
        timeLabel = new JLabel("Time of Day: ");
        time1 = new JRadioButton("Morning");
        time2 = new JRadioButton("Evening");
        frame.add(timeLabel);
        frame.add(time1);
        frame.add(time2);
        timeGroup = new ButtonGroup();
        timeGroup.add(time1);
        timeGroup.add(time2);

        // Adding fourth row of buttons (Day of Week)
        dayLabel = new JLabel("Day of Week: ");
        day1 = new JRadioButton("Weekday");
        day2 = new JRadioButton("Weekend");
        frame.add(dayLabel);
        frame.add(day1);
        frame.add(day2);
        dayGroup = new ButtonGroup();
        dayGroup.add(day1);
        dayGroup.add(day2);

        // Adding fifth row of buttons (Yes/No)
        JLabel yesNoLabel = new JLabel("Yes/No: ");
        yes = new JRadioButton("Yes");
        no = new JRadioButton("No");
        frame.add(yesNoLabel);
        frame.add(yes);
        frame.add(no);
        yesNo = new ButtonGroup();
        yesNo.add(yes);
        yesNo.add(no);

        // Adding sixth row of buttons (Functions)
        Calculate = new JButton("Calculate Probability");
        Calculate.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                calculateProbability();
            }
        });

        NewEntry = new JButton("Add New Entry");
        NewEntry.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                addEntry();
            }
        });

        frame.add(Calculate); 
        frame.add(NewEntry);


        // Making the GUI visible
        frame.setVisible(true);
    }
}

    // This function takes user input from the GUI and then calculates the probability of the selected combination of values.
    // It reads the data from the CSV file and counts how many times the selected combination appears in the data.
    void calculateProbability()
    {
        // Get the selected values from the GUI and store them in variables
        String SelectedTemp = temperature1.isSelected() ? "High" : "Low";
        String SelectedHumidity = humidity1.isSelected() ? "High" : "Low";
        String SelectedTime = time1.isSelected() ? "Morning" : "Evening";
        String SelectedDay = day1.isSelected() ? "Weekday" : "Weekend";
        String SelectedYesNo = yes.isSelected() ? "Yes" : "No";

        // Read data from CSV file
        java.util.List<Value> Values = new java.util.ArrayList<>();

        java.util.ArrayList<String[]> rows = readFile();

        for (String[] row : rows) {
            if (row.length == 5) {
                Value v = new Value();
                v.temp = row[0].trim();
                v.humidity = row[1].trim();
                v.time = row[2].trim();
                v.day = row[3].trim();
                v.yesNo = row[4].trim();
                Values.add(v);
                // Adds each cell in a row to the list of values
                // The trim() method is used to remove any leading or trailing whitespace from the string.
            }
        }

        // Calculate matches
        int Matches = 0;
        for (Value value : Values) {
            if (value.temp.equals(SelectedTemp) &&
                value.humidity.equals(SelectedHumidity) &&
                value.time.equals(SelectedTime) &&
                value.day.equals(SelectedDay) &&
                value.yesNo.equals(SelectedYesNo))
                // Checks if all of the cells in a row match the selected user parameters
            {
                Matches++;
            }
        }

        double probability = Values.size() > 0 ? (double) Matches / Values.size() * 100 : 0; 
        // Checks if the size of the list is greater than 0, if it is not, then the probability is set to 0.
        JOptionPane.showMessageDialog(null, "Probability of selected combination: " + probability + "%");
    }

    class Value 
    {
        String temp;
        String humidity;
        String time;
        String day;
        String yesNo;
    }

    void addEntry() //function to add a new entry to the CSV file
    {
        // Get the selected values from the GUI
        String SelectedTemp = temperature1.isSelected() ? "High" : "Low";
        String SelectedHumidity = humidity1.isSelected() ? "High" : "Low";
        String SelectedTime = time1.isSelected() ? "Morning" : "Evening";
        String SelectedDay = day1.isSelected() ? "Weekday" : "Weekend";
        String SelectedYesNo = yes.isSelected() ? "Yes" : "No";

        // Write the new entry to the CSV file
        java.util.ArrayList<String> newLines = new java.util.ArrayList<>();
        newLines.add(SelectedTemp + "," + SelectedHumidity + "," + SelectedTime + "," + SelectedDay + "," + SelectedYesNo);
        writeFile(newLines);
    }

    public static void main(String[] args)
    {
        @SuppressWarnings("unused") // This was just done to prevent errors from showing where there is no runtime error
        GUI GUI = new GUI();
    }
}
