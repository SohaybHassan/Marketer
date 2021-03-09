package com.misknet.tabseet.marketer.custemEditText;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.misknet.tabseet.marketer.R;

import java.util.Objects;

public class DialogFragmentError extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog builder = new Dialog(Objects.requireNonNull(getContext()));
        // Get the layout inflater
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View root = requireActivity().getLayoutInflater().inflate(R.layout.item_error_login, null);

        TextView tv_error=root.findViewById(R.id.tv_error);
        tv_error.setText("خطأ في عملية الدخول");
        Button btn_ok = root.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(v ->{
            builder.dismiss();

        } );

        builder.setContentView(root);

        builder.create();
        return builder;
    }

}
