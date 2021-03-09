package com.misknet.tabseet.marketer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ToolbarBehaviour extends CoordinatorLayout.Behavior {


    public ToolbarBehaviour(){
        super();
    }
    public ToolbarBehaviour(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency instanceof Toolbar ){
            return true;
        }
        return false;
    }


    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        if (dependency instanceof Toolbar){

            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            TypedArray typedArray=child.getContext().obtainStyledAttributes(new int[]{R.attr.actionBarSize});
            int toolbarsizeinpixel= typedArray.getDimensionPixelSize(0,0);
            params.setMargins(0,toolbarsizeinpixel,0,toolbarsizeinpixel);
            child.setLayoutParams(params);
            return true;
        }
        return false;
    }
}
