package com.example.recruiter.observers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.example.recruiter.R;

public class ButtonObserver implements TextWatcher {
    ImageView button;

    public ButtonObserver(ImageView button) {
        this.button = button;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().trim() != null) {
            button.setEnabled(true);
            button.setImageResource(R.drawable.outline_send_24);
        } else {
            button.setEnabled(false);
            button.setImageResource(R.drawable.ic_baseline_send_24);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}

