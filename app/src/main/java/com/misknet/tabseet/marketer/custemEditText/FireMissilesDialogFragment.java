package com.misknet.tabseet.marketer.custemEditText;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.misknet.tabseet.marketer.R;

import java.util.Objects;

public class FireMissilesDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog builder = new Dialog(Objects.requireNonNull(getContext()));
        // Get the layout inflater
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View root = requireActivity().getLayoutInflater().inflate(R.layout.custem_dilaog, null);

        Button btn_ok = root.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(v ->{
            builder.dismiss();

        } );

        builder.setContentView(root);

        builder.create();
        return builder;
    }
}