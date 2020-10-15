package com.tip.lunchbox.view.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context,
                                 final RecyclerView recycleView,
                                 final ClickListener clicklistener) {

        recycleView.addOnItemTouchListener(this);
        this.clicklistener = clicklistener;
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapUp(MotionEvent motionEvent) {
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(motionEvent)) {
            clicklistener.onClick(child, recyclerView.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NotNull RecyclerView recyclerView, @NotNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

