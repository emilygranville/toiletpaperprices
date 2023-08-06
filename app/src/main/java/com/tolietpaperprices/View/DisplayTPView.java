package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentDisplayTpBinding;

import java.util.List;

/**
 * A class for the view of displaying packages
 */
public class DisplayTPView extends Fragment implements IDisplayTPView {

    FragmentDisplayTpBinding binding;
    List<TPPackage> displayPackageList;

    public DisplayTPView(List<TPPackage> displayPackageList) {
        this.displayPackageList = displayPackageList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDisplayTpBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = this.getArguments();

        if (args != null) {
            /*
            this.navChapterIndex = new ArrayList<ChapterInfo>();
            for (Parcelable bundle : args.getParcelableArray(NavigationView.CHAPTER_INDEX_KEY)) {
                this.navChapterIndex.add(ChapterInfo.fromBundle((Bundle) bundle));
            }

             */
        }
        if (this.displayPackageList != null && !this.displayPackageList.isEmpty()) {
            for (TPPackage tpPackages: this.displayPackageList) {
                //might need to put it in a queue THEN add to binding
                TextView newPackage = new TextView(this.getContext());
                newPackage.setText(newPackage.toString());
                this.binding.displayViewVerticalLayout.addView(newPackage);
            }
        } else {
            TextView defaultMessage = new TextView(this.getContext());
            defaultMessage.setText("No packages of toiletpaper to compare!");
            this.binding.displayViewVerticalLayout.addView(defaultMessage);
        }
    }
}