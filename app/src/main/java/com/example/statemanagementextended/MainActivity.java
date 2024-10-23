package com.example.statemanagementextended;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counterValue = 0;
    private TextView counterTextView, optionTextView;
    private Button increaseButton;
    private EditText editText;
    private CheckBox checkBox;
    private Switch darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        counterTextView = findViewById(R.id.counterTextView);
        increaseButton = findViewById(R.id.increaseButton);
        editText = findViewById(R.id.inputEditText);
        checkBox = findViewById(R.id.optionCheckBox);
        optionTextView = findViewById(R.id.optionTextView);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        // Restore saved state (if any)
        if (savedInstanceState != null) {
            counterValue = savedInstanceState.getInt("counter");
            counterTextView.setText(String.valueOf(counterValue));
            editText.setText(savedInstanceState.getString("editTextValue"));
            checkBox.setChecked(savedInstanceState.getBoolean("checkBoxState"));
            optionTextView.setVisibility(checkBox.isChecked() ? View.VISIBLE : View.GONE);
            darkModeSwitch.setChecked(savedInstanceState.getBoolean("switchState"));

            // Set the dark mode based on switch state
            AppCompatDelegate.setDefaultNightMode(darkModeSwitch.isChecked() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Button functionality to increase counter
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterValue++;
                counterTextView.setText(String.valueOf(counterValue));
            }
        });

        // CheckBox logic
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                optionTextView.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            }
        });

        // Switch logic
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    // Save instance state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counterValue);
        outState.putString("editTextValue", editText.getText().toString());
        outState.putBoolean("checkBoxState", checkBox.isChecked());
        outState.putBoolean("switchState", darkModeSwitch.isChecked());
    }
}
