package com.aditya.humbleparking.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.aditya.humbleparking.R;

public class fragment_custom_dialog extends DialogFragment {
    private TextView mActionOk, mActionCancel;
    private EditText editTextNewContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_custom_dialog, container, false);

        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        editTextNewContent = view.findViewById(R.id.editTextNewContent);

        if (getArguments() != null) {
            if (getArguments().containsKey("content")) {
                editTextNewContent.setText(getArguments().getString("content"));
            }
        }

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
