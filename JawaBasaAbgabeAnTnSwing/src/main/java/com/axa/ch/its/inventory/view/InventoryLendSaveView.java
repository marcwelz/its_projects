package com.axa.ch.its.inventory.view;

import javax.swing.JFrame;

 

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

 

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class InventoryLendSaveView {

    private JFrame frame;
    private JButton btnAbbrechen;
    private JButton btnOk;
    private JSpinner spinner1;
    private JSpinner spinner;

    public InventoryLendSaveView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 452, 196);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

 

        btnAbbrechen = new JButton("Abbrechen");
        btnAbbrechen.setBounds(111, 123, 115, 23);
        frame.getContentPane().add(btnAbbrechen);

        btnOk = new JButton("OK");
        btnOk.setBounds(10, 123, 89, 23);
        frame.getContentPane().add(btnOk);

        JLabel lblBitteAngabenVerfolgstndigen = new JLabel("Bitte Angaben verfolgständigen");
        lblBitteAngabenVerfolgstndigen.setBounds(10, 11, 243, 14);
        frame.getContentPane().add(lblBitteAngabenVerfolgstndigen);

        spinner = new JSpinner();
        spinner.setModel(new SpinnerDateModel(new Date(), new Date(1589448744586L), null, Calendar.DAY_OF_MONTH));
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd-MM-yyyy"));
        spinner.setBounds(111, 44, 106, 20);
        frame.getContentPane().add(spinner);

        int noOfDays = 7; // i.e two weeks
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date7 = calendar.getTime();

        spinner1 = new JSpinner();
        spinner1.setModel(new SpinnerDateModel(date7, new Date(1589448744586L), null, Calendar.DAY_OF_MONTH));
        spinner1.setEditor(new JSpinner.DateEditor(spinner1, "dd-MM-yyyy"));
        spinner1.setBounds(111, 75, 106, 20);
        frame.getContentPane().add(spinner1);

        JLabel lblNewLabel = new JLabel("Start Ausleihe");
        lblNewLabel.setBounds(31, 47, 68, 14);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblEndeAusleihe = new JLabel("Ende Ausleihe");
        lblEndeAusleihe.setBounds(31, 78, 68, 14);
        frame.getContentPane().add(lblEndeAusleihe);

 

        frame.setVisible(true);
    }

 

    public JFrame getFrame() {
        return frame;

    }
    

    public JButton getBtnAbbrechen() {
        return btnAbbrechen;
    }

 

    public JButton getBtnOk() {
        return btnOk;
    }

 

    public JSpinner getSpin1() {
        return spinner;
    }

 

    public JSpinner getSpin2() {
        return spinner1;
    }
    
    public LocalDate getStartDate() {
    	Date date = (Date) spinner.getValue();
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public LocalDate getPlannedEndDate() {
    	Date date = (Date) spinner1.getValue();
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}