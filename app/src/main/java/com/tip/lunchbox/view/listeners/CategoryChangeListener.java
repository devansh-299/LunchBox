package com.tip.lunchbox.view.listeners;

import io.reactivex.rxjava3.core.Observer;

/**
 * Customer listener implemented for triggering the {@link Observer#onNext(Object)} for when the
 * user changes their filter selection.
 */
public interface CategoryChangeListener {
    void onFilterChanged(Integer categoryId);
}
