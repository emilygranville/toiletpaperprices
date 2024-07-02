package com.toiletpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.toiletpaperprices.Controller.MainActivity;
import com.toiletpaperprices.Model.TPPackage;
import com.toiletpaperprices.R;
import com.toiletpaperprices.databinding.FragmentDisplayTpBinding;

import java.io.Serializable;
import java.util.List;

/**
 * A class for the view of displaying packages
 *
 * @author Emily
 */
public class DisplayTPView extends Fragment implements IDisplayTPView {

    FragmentDisplayTpBinding binding;
    Listener listener;
    List<TPPackage> displayPackageList;

    /**
     * Constructor for display view
     * @param listener listens for edit and delete buttons
     * @param displayPackageList list of packages
     */
    public DisplayTPView(Listener listener, List<TPPackage> displayPackageList) {
        this.listener = listener;
        this.displayPackageList = displayPackageList;
    }

    public DisplayTPView(Listener listener) {
        this.listener = listener;
    }

    /**
     * Sets up the fragment
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentDisplayTpBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     * Displays the fragment
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = this.getArguments();
        if (args != null) {
            this.displayPackageList =
                    (List<TPPackage>) args.getSerializable(MainActivity.LIST_OF_PACKAGES_KEY);
        }

        this.displayList();
    }

    /**
     * Saves information when resource constraints are destroyed
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putSerializable(MainActivity.LIST_OF_PACKAGES_KEY,
                (Serializable) this.displayPackageList);
    }

    /**
     * Restores instance when it was destroyed
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            this.displayPackageList =
                    (List<TPPackage>) savedInstanceState.getSerializable(
                            MainActivity.LIST_OF_PACKAGES_KEY);
        }
    }

    /**
     * Adds information to display
     */
    private void displayList() {
        if (this.displayPackageList != null && !this.displayPackageList.isEmpty()) {
            for (int i = 0; i < this.displayPackageList.size(); i++) {

                TPPackage tpPackage = this.displayPackageList.get(i);

                LinearLayout innerVertical = new LinearLayout(this.getContext());
                innerVertical.setOrientation(LinearLayout.VERTICAL);

                TextView newPackage = new TextView(this.getContext());
                newPackage.setText(tpPackage.toString());
                innerVertical.addView(newPackage);
                //innerVertical.setBackgroundColor(getResources().getColor(R.color.light_gray_text));

                LinearLayout innerHorizontal = new LinearLayout(this.getContext());
                //RelativeLayout innerHorizontal = new RelativeLayout(this.getContext());
                innerHorizontal.setOrientation(LinearLayout.HORIZONTAL);
                innerHorizontal.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                //innerHorizontal.setBackgroundColor(getResources().getColor(R.color.fourth_color));

                Button editButton = new Button(this.getContext());
                editButton.setId(i);
                editButton.setText(this.getContext().getResources().getString(R.string.edit_button_label));
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DisplayTPView.this.listener.editPackageButton(editButton.getId());
                    }
                });
//                RelativeLayout.LayoutParams editLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//                editLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//                //editLayoutParams.addRule(RelativeLayout.ALIGN_RIGHT, (i * -1));
//                editButton.setLayoutParams(editLayoutParams);
                innerHorizontal.addView(editButton);

                Button deleteButton = new Button(this.getContext());
                deleteButton.setId(i * -1);
                deleteButton.setText(this.getContext().getResources().getString(R.string.delete_button_label));
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DisplayTPView.this.listener.deletePackageButton(deleteButton.getId() * -1);
                    }
                });
//                RelativeLayout.LayoutParams deleteLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//                //deleteLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//                deleteLayoutParams.addRule(RelativeLayout.toRightof, i);
//                deleteButton.setLayoutParams(deleteLayoutParams);
                //deleteButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                innerHorizontal.addView(deleteButton);

                innerVertical.addView(innerHorizontal);
                this.binding.displayViewVerticalLayout.addView(innerVertical);
            }
        } else {
            TextView defaultMessage = new TextView(this.getContext());
            defaultMessage.setText(this.getContext().getResources().getString(R.string.no_packages_label));
            this.binding.displayViewVerticalLayout.addView(defaultMessage);
        }
    }
}