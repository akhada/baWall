package com.github.mobileartisans.bawall.component;

import android.view.View;
import android.widget.AdapterView;

public abstract class ItemSelectedListener implements AdapterView.OnItemSelectedListener {
    private int selectedItem;

    protected ItemSelectedListener(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public abstract void onSelectItem(AdapterView<?> parentView, View selectedItemView, int position, long id);

    @Override
    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
        if (selectedItem != position) {
            onSelectItem(parentView, selectedItemView, position, id);
            selectedItem = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
    }


}
