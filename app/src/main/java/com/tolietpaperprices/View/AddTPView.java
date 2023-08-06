package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolietpaperprices.Model.TPPackage;
import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentAddTpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTPView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTPView extends Fragment implements IAddTPView {

    FragmentAddTpBinding binding;
    Listener listener;

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

        this.binding.addTpDoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (isFilledOut()) {
                    AddTPView.this.listener.onTPAddDoneButton(AddTPView.this.makeNewPackage());
                }
            }
        });
    }

    private boolean isFilledOut() {
        return !this.binding.brandNameEditable.getText().equals("") &&
                !this.binding.priceEditable.getText().equals("") &&
                !this.binding.numRollsEditable.getText().equals("") &&
                !this.binding.squaresPerRollEditable.getText().equals("") &&
                !this.binding.storeNameEditable.getText().equals("") &&
                !this.binding.storeTownNameEditable.getText().equals("");
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