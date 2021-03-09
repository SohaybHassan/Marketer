package com.misknet.tabseet.marketer.custemEditText;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.misknet.tabseet.marketer.Network.asp.featuers.GenerlNetworking;
import com.misknet.tabseet.marketer.Network.asp.models.TabseetApp;
import com.misknet.tabseet.marketer.Network.utiles.NetworkingParent;
import com.misknet.tabseet.marketer.Network.utiles.RequesListiner;
import com.misknet.tabseet.marketer.R;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.ContactUs;
import com.misknet.tabseet.marketer.featuers.settingFragment.model.ContactUsReslet;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ContactUsDialog extends DialogFragment {
    EditText text;
    String mass;
    Button btn_ok;
    TextContactMassage lisener;

    public ContactUsDialog(TextContactMassage lisener) {
        this.lisener = lisener;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog builder = new Dialog(Objects.requireNonNull(getContext()));
        // Get the layout inflater

        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        View root = requireActivity().getLayoutInflater().inflate(R.layout.contact_us_text, null);
        text = root.findViewById(R.id.ed_text);

        btn_ok = root.findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(v -> {
            if (NetworkingParent.myConnection) {
                mass = text.getText().toString().trim();

                lisener.massage(mass);

            } else {

                }

            builder.dismiss();
        });


        builder.setContentView(root);

        builder.create();
        return builder;
    }

    public interface TextContactMassage {
        void massage(String massag);
    }


}
