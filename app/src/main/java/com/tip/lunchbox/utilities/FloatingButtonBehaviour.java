package com.tip.lunchbox.utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tip.lunchbox.R;

/**
 * Custom behaviour class for the 'Clear Filter' chip to interact with the Bottom Sheet within the
 * CoordinatorLayout. Does not replicate the FloatingActionButton anchoring behaviour, rather
 * designed for the chip to hide below the Bottom Sheet on pull up.
 */
public class FloatingButtonBehaviour extends CoordinatorLayout.Behavior<View> {

    private final float restaurantListBottomSheetPeekHeight;
    private final float margin;

    public FloatingButtonBehaviour(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.restaurantListBottomSheetPeekHeight = context.getResources()
                .getDimension(R.dimen.value_175dp);
        this.margin = context.getResources().getDimension(R.dimen.value_10dp);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child,
                                   @NonNull View dependency) {
        return dependency.getId() == R.id.bs_restaurant_list;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child,
                                          @NonNull View dependency) {
        if (dependency.getY() < (float) parent.getHeight()
                - this.restaurantListBottomSheetPeekHeight) {
            child.setY((float) parent.getHeight() - this.restaurantListBottomSheetPeekHeight
                    - ((float) child.getHeight() + this.margin));
        } else {
            child.setY(dependency.getY() - ((float) child.getHeight() + this.margin));
        }

        return false;
    }
}
