package yash.Projects.Calculator;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorAPI implements ActionListener{
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addition,subtract,multiply,divide,decimal,equals,delete,clear,negativeButton;
    JTextArea instructionField;
    JPanel panel;
    Font myFont = new Font("Verdana",Font.BOLD,28);
    Font operationFont = new Font(Font.DIALOG_INPUT,Font.BOLD,34);
    Font outputFont = new Font(Font.SANS_SERIF,Font.BOLD,32);
    double number1=0,number2=0,resultant=0;
    char operator;
    CalculatorAPI(){
        frame = new JFrame("CALCULATOR");
        frame.setSize(420,600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

//        Our value field
//        We are having delimiter to indicate the new Beginning
        textField = new JTextField("|");
        textField.setBounds(50,25,300,50);
//        textField.setSize(300,50);
        Border border = BorderFactory.createLineBorder(new Color(38, 47, 37, 255), 3);
        textField.setBorder(border);
        textField.setEditable(false);
        textField.setFont(outputFont);
//        textField.setHorizontalAlignment(4);

//        Operation Buttons
        functionButtons[0] = addition = new JButton("+");
        functionButtons[1] = subtract = new JButton("-");
        functionButtons[2] = multiply = new JButton("×");
        functionButtons[3] = divide = new JButton("÷");
        functionButtons[4] = decimal = new JButton(".");
        equals = new JButton("=");
        equals.setBackground(new Color(133, 231, 102));
        functionButtons[5] = equals;
        functionButtons[6] = delete = new JButton("DEL");
        functionButtons[7] = clear = new JButton("CLR");
        functionButtons[8] = negativeButton = new JButton("-ve");


        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(operationFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negativeButton.setBounds(50,480,100,50);
//        negativeButton.setBorder(BorderFactory.createLineBorder(new Color(134, 131, 131), 2));

        delete.setBounds(150,480,100,50);
        delete.setBackground(new Color(227, 145, 83));
//        delete.setBorder(BorderFactory.createLineBorder(new Color(84, 82, 82), 2));

        clear.setBounds(250,480,100,50);
        clear.setBackground(new Color(220, 100, 6));
//        clear.setBorder(BorderFactory.createLineBorder(new Color(70, 62, 62), 2));


//        Using the panel to Hold Numbers and Operation Buttons
        panel = new JPanel();
        panel.setBounds(50,100,300,240);
        panel.setLayout(new GridLayout(4,4,10,10));


        for (int i = 1; i < 4; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(addition);
        for (int i = 4; i < 7; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(subtract);
        for (int i = 7; i < 10; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(multiply);
        panel.add(decimal);
        panel.add(numberButtons[0]);
        panel.add(divide);
        panel.add(equals);

//        To Display in-case of Division by Zero
        instructionField = new JTextArea("""
                Only two numbers can be used.
                Use = (equals) after every
                operation performed. To carry
                forward the previous results.
                \s""");
        instructionField.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        instructionField.setForeground(Color.RED);
        instructionField.setBounds(50,350,300,110);

        frame.add(textField);
        frame.add(panel);
        frame.add(instructionField);
        frame.add(negativeButton);
        frame.add(delete);
        frame.add(clear);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]){
//          As we are having a Delimiter("|") to indicate beginning.
//          If we are inserting the first value then we have to skip delimiter and then continue.
                if(textField.getText().equals("|")){
                    textField.setText(String.valueOf(i));
                }else{
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
            }
        }

        if (e.getSource() == decimal){
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addition){
            if (!textField.getText().equals("|")) {
                number1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
        }

        if (e.getSource() == subtract){
            if (textField.getText().equals("|")){
                number1 = 0;
            }else {
                number1 = Double.parseDouble(textField.getText());
            }
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == multiply){
            if (textField.getText().equals("|")){
//                In case we are Starting with the Delimiter (i.e, From beginning)
//                We are ignoring this because Starting with multiplication("*" or "×") will give syntax error in Calculators.
            }else{
                number1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
        }

        if (e.getSource() == divide){
            if (textField.getText().equals("|")){
//                In case we are Starting with the Delimiter (i.e, From beginning)
//                We are ignoring this because Starting with division("/" or "÷") will give syntax error in Calculators.
            }else{
                number1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }

        }

        if (e.getSource() == equals){
            if (textField.getText().equals("|")){
//                In case we are Starting with the Delimiter (i.e, From beginning)
//                We are ignoring this because there will be no change at the beginning.
            }else {
                number2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+' -> resultant = number1 + number2;
                    case '-' -> resultant = number1 - number2;
                    case '*' -> resultant = number1 * number2;
                    case '/' -> resultant = number1 / number2;
                }

//                In case of Division by Zero  --> As it will give an error
                if(!String.valueOf(resultant).equals("Infinity")){
                    textField.setText(String.valueOf(resultant));
//                  Assigning the resultant to number1 so that we can continue the operations with the same number(resultant)
                    number1 = resultant;
                }else {
                    textField.setText("Error");
                    instructionField.setText("\nCLR to restart from Beginning");
                }

            }

        }
        if (e.getSource() == clear){
//      In case we are Clearing the values we are setting the text field with the Delimiter to indicate the new beginning.
            number1 = 0;
            textField.setText("|");
            instructionField.setText("""
                Only two numbers can be used.
                Use = (equals) after every
                operation performed. To carry
                forward the previous results.
                \s""");
        }
        if (e.getSource() == delete){
            String current = textField.getText();
//            Removing the last inserted value or operator --> (substring from zero to stringLength-1)
            current = current.substring(0,current.length()-1);
            textField.setText(current);
        }

        if (e.getSource() == negativeButton){
            double temp = Double.parseDouble(textField.getText());
            temp = temp * -1;
            textField.setText(String.valueOf(temp));
        }
    }

    public static void main(String[] args) {
        CalculatorAPI calc = new CalculatorAPI();
    }
}