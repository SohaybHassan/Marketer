package com.misknet.tabseet.marketer.custemEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.misknet.tabseet.marketer.R;


public class EditTextThought extends FrameLayout {
    LinearLayout contener;
    EditText ed_Text;
    TextView tv_grop;
    Drawable myimage;
    String mText;
    int mImage;
    int inputTypes;
    boolean setEnable;

    public EditTextThought(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EditTextThought
                , 0, 0);
        mText = typedArray.getString(R.styleable.EditTextThought_new_text);
        mImage = typedArray.getResourceId(R.styleable.EditTextThought_new_image_resrses, -1);
        inputTypes = typedArray.getInt(R.styleable.EditTextThought_android_inputType, 0);
        setEnable = typedArray.getBoolean(R.styleable.EditTextThought_android_state_enabled, true);

        View view = LayoutInflater.from(context).inflate(R.layout.my_edit_text, this);
        contener = view.findViewById(R.id.contecter);
        ed_Text = view.findViewById(R.id.ed_phone);
        tv_grop = view.findViewById(R.id.ed_phone_after_clicked_phone);

        ed_Text.setHint(mText);
        ed_Text.setInputType(inputTypes);
        ed_Text.setEnabled(setEnable);
        ed_Text.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.d("edTExt-1", ed_Text.getText().toString());
                    ed_Text.setHint(mText);
                    ed_Text.setAlpha(1);
                    contener.setAlpha(1);
                    tv_grop.setVisibility(VISIBLE);
                    ed_Text.setCompoundDrawablesRelative(null, null, null, null);
                }
                if (!hasFocus && ed_Text.getText().toString().isEmpty()) {
                    contener.setAlpha(0.5f);
                    tv_grop.setVisibility(GONE);
                    ed_Text.setCompoundDrawablesRelative(myimage, null, null, null);
                }
                if (!hasFocus && !ed_Text.getText().toString().isEmpty()) {
                    contener.setAlpha(1);
                    tv_grop.setVisibility(VISIBLE);
                    ed_Text.setCompoundDrawablesRelative(null, null, null, null);
                }
            }
        });
        tv_grop.setText(mText);
        myimage = getContext().getResources().getDrawable(mImage, null);
        myimage.setBounds(0, 0, 18, 25);
        ed_Text.setCompoundDrawablesRelative(myimage, null, null, null);
        tv_grop.setCompoundDrawablesRelative(myimage, null, null, null);


    }


    public Editable getmyText() {
        Editable textmy = ed_Text.getText();
        //Log.e("ttttt",textmy.toString());
        return textmy;

    }

    public void setmyText(String editable) {
        ed_Text.setText(editable);

    }


    public void setmyststus(boolean editable) {
        ed_Text.setEnabled(editable);

    }

}




