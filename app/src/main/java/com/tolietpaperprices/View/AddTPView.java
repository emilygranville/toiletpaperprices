package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentAddTpBinding;


/**
 * A class for the view to add packages to the list of packages
 *
 * @author Emilly
 */
public class AddTPView extends Fragment implements IAddTPView {

    public static final String PACKAGE_KEY = "package key";
    public static final String INDEX_KEY = "index key";
    TPPackage.Style currentStyle = TPPackage.Style.NORMAL;

    FragmentAddTpBinding binding;

    Listener listener;
    TPPackage tpPackage;
    int index;

    /**
     * Main constructor for add view
     * @param listener listens for done button
     */
    public AddTPView(Listener listener) {
        this.listener = listener;
        this.index = -1;
    }

    /**
     * Secondary constructor for add view when editing packages
     * @param listener listens for done button
     * @param tpPackage package information provided
     */
    public AddTPView(Listener listener, TPPackage tpPackage, int index) {
        this.listener = listener;
        this.tpPackage = tpPackage;
        this.index = index;
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
        this.binding = FragmentAddTpBinding.inflate(inflater);
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
            this.tpPackage = (TPPackage) args.getSerializable(this.PACKAGE_KEY);
            this.index = args.getInt(INDEX_KEY);
        }

        RadioGroup styleRadioGroup = new RadioGroup(this.getContext());
        for (TPPackage.Style style : TPPackage.Style.values()) {
            RadioButton button = new RadioButton(this.getContext());
            styleRadioGroup.addView(button);
            button.setText(String.valueOf(style));
            if (this.tpPackage != null) {
                if (style == this.tpPackage.getStyle()) {
                    button.toggle();
                }
            } else {
                if (style == TPPackage.Style.NORMAL) {
                    button.toggle();
                }
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddTPView.this.currentStyle = TPPackage.Style.valueOf(button.getText().toString());
                }
            });
        }
        this.binding.styleHlayout.addView(styleRadioGroup);

        if (this.tpPackage != null) {
            enterInfo();
        }

        this.binding.addTpDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isFilledOut()) {
                    boolean isEditedPackage = AddTPView.this.tpPackage != null;
                    AddTPView.this.listener.onTPAddDoneButton(AddTPView.this.makeNewPackage(), isEditedPackage, AddTPView.this.index);
                } else {
                    Snackbar.make(view, AddTPView.this.getContext().getResources().getString(R.string.not_filled_correctly_label), Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Saves information when resource constraints are destoryed
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(this.PACKAGE_KEY, this.tpPackage);
        outState.putInt(this.INDEX_KEY, this.index);
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
            this.tpPackage = (TPPackage) savedInstanceState.getSerializable(this.PACKAGE_KEY);
            this.index = savedInstanceState.getInt(this.INDEX_KEY);

            enterInfo();
        }
    }

    /**
     * Adds information of package to form for editing
     */
    private void enterInfo() {
        if (this.tpPackage == null) {
            return;
        }
        if (this.tpPackage.getBrand() != null)
            this.binding.brandNameEditable.setText(this.tpPackage.getBrand());
        if (String.valueOf(this.tpPackage.getPrice()) != null)
            this.binding.priceEditable.setText(String.valueOf(this.tpPackage.getPrice()));
        if (String.valueOf(this.tpPackage.getNumRolls()) != null)
            this.binding.numRollsEditable.setText(String.valueOf(this.tpPackage.getNumRolls()));
        if (String.valueOf(this.tpPackage.getNumSquaresPerRoll()) != null)
            this.binding.squaresPerRollEditable.setText(String.valueOf(this.tpPackage.getNumSquaresPerRoll()));
        if (this.tpPackage.getStoreName() != null)
            this.binding.storeNameEditable.setText(this.tpPackage.getStoreName());
        if (this.tpPackage.getStoreTownName() != null)
            this.binding.storeTownNameEditable.setText(this.tpPackage.getStoreTownName());
    }

    /**
     * Checks whether the form is completely filled out
     */
    private boolean isFilledOut() {
        return !this.binding.brandNameEditable.getText().toString().equals("") &&
                !this.binding.priceEditable.getText().toString().equals("") &&
                !this.binding.numRollsEditable.getText().toString().equals("") &&
                !this.binding.squaresPerRollEditable.getText().toString().equals("") &&
                !this.binding.storeNameEditable.getText().toString().equals("") &&
                !this.binding.storeTownNameEditable.getText().toString().equals("");
    }

    /**
     * Creates a new package based on given information
     */
    private TPPackage makeNewPackage() {
        return new TPPackage(
                this.binding.brandNameEditable.getText().toString(),
                this.currentStyle,
                Double.parseDouble(this.binding.priceEditable.getText().toString()),
                Integer.parseInt(this.binding.numRollsEditable.getText().toString()),
                Integer.parseInt(this.binding.squaresPerRollEditable.getText().toString()),
                this.binding.storeNameEditable.getText().toString(),
                this.binding.storeTownNameEditable.getText().toString());
    }
}