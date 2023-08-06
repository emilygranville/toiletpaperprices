package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.tolietpaperprices.Controller.MainActivity;
import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentAddTpBinding;

/**
 * A class for the view to add packages to the list of packages
 */
public class AddTPView extends Fragment implements IAddTPView {

    FragmentAddTpBinding binding;
    Listener listener;
    String NOT_CHANGED;

    public AddTPView(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentAddTpBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        NOT_CHANGED = AddTPView.this.binding.priceEditable.getText().toString();
        Log.i(MainActivity.TPP, "not changed: " + NOT_CHANGED);

         */

        this.binding.addTpDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Log.d(MainActivity.TPP, String.valueOf(AddTPView.this.binding.priceEditable.getText().equals("\n")));

                if (isFilledOut()) {
                    AddTPView.this.listener.onTPAddDoneButton(AddTPView.this.makeNewPackage());
                } else {
                    Snackbar.make(view, "Make sure to enter information in all all fields", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isFilledOut() {
        return !this.binding.brandNameEditable.getText().toString().equals("") &&
                !this.binding.priceEditable.getText().toString().equals("") &&
                !this.binding.numRollsEditable.getText().toString().equals("") &&
                !this.binding.squaresPerRollEditable.getText().toString().equals("") &&
                !this.binding.storeNameEditable.getText().toString().equals("") &&
                !this.binding.storeTownNameEditable.getText().toString().equals("");
    }

    private TPPackage makeNewPackage() {
        return new TPPackage(
                this.binding.brandNameEditable.getText().toString(),
                TPPackage.Style.NORMAL,
                Double.parseDouble(this.binding.priceEditable.getText().toString()),
                Integer.parseInt(this.binding.numRollsEditable.getText().toString()),
                Integer.parseInt(this.binding.squaresPerRollEditable.getText().toString()),
                this.binding.storeNameEditable.getText().toString(),
                this.binding.storeTownNameEditable.getText().toString());
    }
}