package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentDisplayTpBinding;

import java.util.List;

/**
 * A class for the view of displaying packages
 *
 * @author Emily
 */
public class DisplayTPView extends Fragment implements IDisplayTPView {

    public static final String LIST_OF_PACKAGES_S = "list of packages";
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
            this.displayPackageList = (List<TPPackage>) args.getSerializable(LIST_OF_PACKAGES_S);
        }

        if (this.displayPackageList != null && !this.displayPackageList.isEmpty()) {
            for (int i = 0; i < this.displayPackageList.size(); i++) {

                TPPackage tpPackage = this.displayPackageList.get(i);

                LinearLayout innerVertical = new LinearLayout(this.getContext());
                innerVertical.setOrientation(LinearLayout.VERTICAL);

                //might need to put it in a queue THEN add to binding
                TextView newPackage = new TextView(this.getContext());
                newPackage.setText(tpPackage.toString() + "\n");
                innerVertical.addView(newPackage);

                LinearLayout innerHorizontal = new LinearLayout(this.getContext());
                innerHorizontal.setOrientation(LinearLayout.HORIZONTAL);
                innerHorizontal.setMinimumWidth(FrameLayout.LayoutParams.MATCH_PARENT);

                Button editButton = new Button(this.getContext());
                editButton.setId(i);
                editButton.setText(this.getContext().getResources().getString(R.string.edit_button_label));
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DisplayTPView.this.listener.editPackageButton(editButton.getId());
                    }
                });
                editButton.setMinimumWidth(FrameLayout.LayoutParams.MATCH_PARENT);
                innerHorizontal.addView(editButton);

                Button deleteButton = new Button(this.getContext());
                deleteButton.setId(i);
                deleteButton.setText(this.getContext().getResources().getString(R.string.delete_button_label));
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DisplayTPView.this.listener.deletePackageButton(deleteButton.getId());
                    }
                });
                deleteButton.setMinimumWidth(FrameLayout.LayoutParams.MATCH_PARENT);
                innerHorizontal.addView(deleteButton);

                innerVertical.addView(innerHorizontal);
                this.binding.displayViewVerticalLayout.addView(innerVertical);
            }
        } else {
            TextView defaultMessage = new TextView(this.getContext());
            defaultMessage.setText("No packages of toiletpaper to compare!");
            this.binding.displayViewVerticalLayout.addView(defaultMessage);
        }
    }
}