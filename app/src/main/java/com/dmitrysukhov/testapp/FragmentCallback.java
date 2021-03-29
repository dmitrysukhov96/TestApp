package com.dmitrysukhov.testapp;

import androidx.fragment.app.Fragment;

public interface FragmentCallback {
    public void createNewFragment();
    public void deleteFragment(int id);
}
