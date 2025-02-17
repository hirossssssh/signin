package com.example.girlycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private RelativeLayout rootLayout;
    private boolean isDarkMode = false;
    private double num1 = 0;
    private String operator = "";
    private boolean isDecimal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        rootLayout = findViewById(R.id.rootLayout);
        Button buttonDarkMode = findViewById(R.id.buttonDarkMode);

        // Set click listener for Dark Mode button
        buttonDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDarkMode();
            }
        });

        // Initialize other button listeners (same as before)
        setupNumberButtons();
        setupOperatorButtons();
        setupEqualsButton();
        setupClearButton();
        setupSquareRootButton();
    }

    // Toggle between light and dark mode
    private void toggleDarkMode() {
        if (isDarkMode) {
            // Switch to Light Mode
            rootLayout.setBackgroundColor(getResources().getColor(R.color.pastel_pink));
            display.setBackgroundColor(getResources().getColor(R.color.white));
            display.setTextColor(getResources().getColor(R.color.black));
            updateButtonColors(R.color.light_pink, R.color.white);
        } else {
            // Switch to Dark Mode
            rootLayout.setBackgroundColor(getResources().getColor(R.color.dark_background));
            display.setBackgroundColor(getResources().getColor(R.color.dark_display_background));
            display.setTextColor(getResources().getColor(R.color.dark_display_text));
            updateButtonColors(R.color.dark_button_background, R.color.dark_button_text);
        }
        isDarkMode = !isDarkMode; // Toggle the mode
    }

    // Update button colors
    private void updateButtonColors(int backgroundColorRes, int textColorRes) {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDecimal,
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply,
                R.id.buttonDivide, R.id.buttonSquareRoot, R.id.buttonEquals,
                R.id.buttonClear, R.id.buttonDarkMode // Include Dark Mode button
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            if (button != null) { // check if the button is not null
                button.setBackgroundTintList(getResources().getColorStateList(backgroundColorRes));
                button.setTextColor(getResources().getColor(textColorRes));
            }
        }
    }

    // Number button setup
    private void setupNumberButtons() {
        int[] numberButtonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String number = ((Button) v).getText().toString();
                        String currentText = display.getText().toString();

                        if (currentText.equals("0") && !number.equals(".")) {
                            display.setText(number);
                        } else {
                            display.setText(currentText + number);
                        }
                    }
                });
            }
        }

        Button buttonDecimal = findViewById(R.id.buttonDecimal);
        if (buttonDecimal != null) {
            buttonDecimal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentText = display.getText().toString();
                    if (!currentText.contains(".")) {
                        display.setText(currentText + ".");
                    }
                }
            });
        }
    }

    // Operator button setup
    private void setupOperatorButtons() {
        int[] operatorButtonIds = {R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide};
        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            if (button != null) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String operation = ((Button) v).getText().toString();
                        String currentText = display.getText().toString();
                        if (!currentText.isEmpty()) {
                            try {
                                num1 = Double.parseDouble(currentText);
                                operator = operation;
                                display.setText(""); // Clear display for the next number
                            } catch (NumberFormatException e) {
                                Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        }
    }

    // Equals button setup
    private void setupEqualsButton() {
        Button buttonEquals = findViewById(R.id.buttonEquals);
        if (buttonEquals != null) {
            buttonEquals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentText = display.getText().toString();
                    if (!currentText.isEmpty() && !operator.isEmpty()) {
                        try {
                            double num2 = Double.parseDouble(currentText);
                            double result = 0;
                            switch (operator) {
                                case "+":
                                    result = num1 + num2;
                                    break;
                                case "-":
                                    result = num1 - num2;
                                    break;
                                case "*":
                                    result = num1 * num2;
                                    break;
                                case "/":
                                    if (num2 == 0) {
                                        Toast.makeText(MainActivity.this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                                        return; // Avoid further calculation
                                    }
                                    result = num1 / num2;
                                    break;
                            }
                            display.setText(String.valueOf(result));
                            num1 = result; // For chaining operations
                            operator = ""; // Reset operator
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    // Clear button setup
    private void setupClearButton() {
        Button buttonClear = findViewById(R.id.buttonClear);
        if (buttonClear != null) {
            buttonClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    display.setText("0");
                    num1 = 0;
                    operator = "";
                }
            });
        }
    }

    // Square root button setup
    private void setupSquareRootButton() {
        Button buttonSquareRoot = findViewById(R.id.buttonSquareRoot);
        if (buttonSquareRoot != null) {
            buttonSquareRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentText = display.getText().toString();
                    if (!currentText.isEmpty()) {
                        try {
                            double num = Double.parseDouble(currentText);
                            if (num < 0) {
                                Toast.makeText(MainActivity.this, "Cannot square root a negative number", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            double result = Math.sqrt(num);
                            display.setText(String.valueOf(result));
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}