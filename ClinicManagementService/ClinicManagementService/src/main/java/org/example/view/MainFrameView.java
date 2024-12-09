//
//package org.example.view;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.Locale;
//import java.util.ResourceBundle;
//
//public class MainFrameView {
//    public JFrame frame;
//    public Locale currentLocale;
//    public ResourceBundle bundle;
//    public JButton btnSignIn, btnAppointments, btnMedicalRecords, btnCreateMedicalRecord, btnEnglish, btnFrench;
//    public JTextField txtUserId, txtLastName;
//    public JRadioButton rbDoctor, rbPatient;
//    public ButtonGroup userRoleGroup;
//
//    public MainFrameView(Locale locale) {
//        setLocale(locale);
//        buildUI();
//    }
//
//    public void setLocale(Locale locale) {
//        bundle = ResourceBundle.getBundle("messages.messages", locale);
//    }
//
//    private void buildUI() {
//        frame = new JFrame(bundle.getString("title"));
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        JLabel lblSignIn = new JLabel(bundle.getString("signIn"));
//
//        txtUserId = new JTextField(20);
//        txtUserId.setToolTipText(bundle.getString("userID"));
//
//        txtLastName = new JTextField(20);
//        txtLastName.setToolTipText(bundle.getString("lastName"));
//
//        rbDoctor = new JRadioButton(bundle.getString("doctor"));
//        rbPatient = new JRadioButton(bundle.getString("patient"));
//        userRoleGroup = new ButtonGroup();
//        userRoleGroup.add(rbDoctor);
//        userRoleGroup.add(rbPatient);
//        rbDoctor.setSelected(true);
//
//        btnSignIn = new JButton(bundle.getString("signIn"));
//        btnAppointments = new JButton(bundle.getString("appointments"));
//        btnMedicalRecords = new JButton(bundle.getString("medicalRecords"));
//        btnCreateMedicalRecord = new JButton(bundle.getString("createMedicalRecord"));
//        btnEnglish = new JButton(bundle.getString("englishButton"));
//        btnFrench = new JButton(bundle.getString("frenchButton"));
//
//        JPanel languagePanel = new JPanel();
//        languagePanel.add(btnEnglish);
//        languagePanel.add(btnFrench);
//
//        JPanel optionsPanel = new JPanel();
//        optionsPanel.add(btnAppointments);
//        optionsPanel.add(btnMedicalRecords);
//        optionsPanel.add(btnCreateMedicalRecord);
//
//        panel.add(lblSignIn);
//        panel.add(txtUserId);
//        panel.add(txtLastName);
//        panel.add(rbDoctor);
//        panel.add(rbPatient);
//        panel.add(btnSignIn);
//        panel.add(languagePanel);
//        panel.add(optionsPanel);
//
//        frame.add(panel);
//        frame.setVisible(true);
//    }
//
//    public void updateView() {
//        frame.setTitle(bundle.getString("title"));
//        btnSignIn.setText(bundle.getString("signIn"));
//        btnAppointments.setText(bundle.getString("appointments"));
//        btnMedicalRecords.setText(bundle.getString("medicalRecords"));
//        btnCreateMedicalRecord.setText(bundle.getString("createMedicalRecord"));
//        btnEnglish.setText(bundle.getString("englishButton"));
//        btnFrench.setText(bundle.getString("frenchButton"));
//    }
//}
package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainFrameView {
    public JFrame frame;
    public Locale currentLocale;
    public ResourceBundle bundle;
    public JButton btnSignIn, btnAppointments, btnMedicalRecords, btnCreateMedicalRecord, btnEnglish, btnFrench;
    public JTextField txtUserId, txtLastName;
    public JButton btnDoctor, btnPatient;  // Separate buttons for Doctor and Patient

    public MainFrameView(Locale locale) {
        setLocale(locale);
        buildUI();
    }

    public void setLocale(Locale locale) {
        bundle = ResourceBundle.getBundle("messages.messages", locale);
    }

    private void buildUI() {
        frame = new JFrame(bundle.getString("title"));
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnEnglish = new JButton(bundle.getString("englishButton"));
        btnFrench = new JButton(bundle.getString("frenchButton"));
        topPanel.add(btnEnglish);
        topPanel.add(btnFrench);

        JLabel lblSignIn = new JLabel(bundle.getString("signIn"));

        // Smaller text fields for user ID and last name
        txtUserId = new JTextField(15);  // Reduced width
        txtUserId.setToolTipText(bundle.getString("userID"));

        txtLastName = new JTextField(15);  // Reduced width
        txtLastName.setToolTipText(bundle.getString("lastName"));

        btnDoctor = new JButton(bundle.getString("doctor"));
        btnPatient = new JButton(bundle.getString("patient"));

        btnSignIn = new JButton(bundle.getString("signIn"));
        btnAppointments = new JButton(bundle.getString("appointments"));
        btnMedicalRecords = new JButton(bundle.getString("medicalRecords"));
        btnCreateMedicalRecord = new JButton(bundle.getString("createMedicalRecord"));

        // Language and options panels
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(btnDoctor);
        optionsPanel.add(btnPatient);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(btnSignIn);
        actionPanel.add(btnAppointments);
        actionPanel.add(btnMedicalRecords);
        //actionPanel.add(btnCreateMedicalRecord);

        panel.add(topPanel);
        panel.add(lblSignIn);
        panel.add(txtUserId);
        panel.add(txtLastName);
        panel.add(optionsPanel);
        panel.add(actionPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void updateView() {
        frame.setTitle(bundle.getString("title"));
        btnSignIn.setText(bundle.getString("signIn"));
        btnAppointments.setText(bundle.getString("appointments"));
        btnMedicalRecords.setText(bundle.getString("medicalRecords"));
       // btnCreateMedicalRecord.setText(bundle.getString("createMedicalRecord"));
        btnEnglish.setText(bundle.getString("englishButton"));
        btnFrench.setText(bundle.getString("frenchButton"));
    }
}
