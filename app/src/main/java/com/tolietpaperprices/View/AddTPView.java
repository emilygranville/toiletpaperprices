package com.tolietpaperprices.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tolietpaperprices.R;
import com.tolietpaperprices.databinding.FragmentAddTpBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTPView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTPView extends Fragment implements IAddTPView {

    FragmentAddTpBinding binding;

    public AddTPView() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddToiletpaper.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTPView newInstance(String param1, String param2) {
        AddTPView fragment = new AddTPView();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return this.binding.getRoot();
    }
}