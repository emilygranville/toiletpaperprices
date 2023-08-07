package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.tolietpaperprices.Controller.MainActivity;
import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentAddTpBinding;

/**
 * A class for the view to add packages to the list of packages
 *
 * @author Emilly
 */
public class AddTPView extends Fragment implements IAddTPView {

    FragmentAddTpBinding binding;
    Listener listener;
    TPPackage.Style currentStyle = TPPackage.Style.NORMAL;

    /**
     * Constructor for add view
     * @param listener listens for done button
     */
    public AddTPView(Listener listener) {
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

        RadioGroup styleRadioGroup = new RadioGroup(this.getContext());
        for (TPPackage.Style style : TPPackage.Style.values()) {
            RadioButton button = new RadioButton(this.getContext());
            styleRadioGroup.addView(button);
            button.setText(String.valueOf(style));
            if (style == TPPackage.Style.NORMAL) {
                button.toggle();
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddTPView.this.currentStyle = TPPackage.Style.valueOf(button.getText().toString());
                }
            });
        }
        this.binding.styleHlayout.addView(styleRadioGroup);

        this.binding.addTpDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isFilledOut()) {
                    AddTPView.this.listener.onTPAddDoneButton(AddTPView.this.makeNewPackage());
                } else {
                    Snackbar.make(view, AddTPView.this.getContext().getResources().getString(R.string.not_filled_correctly_label), Snackbar.LENGTH_LONG).show();
                }
            }
        });
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