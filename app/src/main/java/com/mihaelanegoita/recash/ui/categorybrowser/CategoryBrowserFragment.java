package com.mihaelanegoita.recash.ui.categorybrowser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mihaelanegoita.recash.databinding.FragmentCategoryBrowserBinding;

public class CategoryBrowserFragment extends BottomSheetDialogFragment {
    private FragmentCategoryBrowserBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBrowserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
