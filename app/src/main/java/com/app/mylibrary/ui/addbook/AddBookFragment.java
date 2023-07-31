package com.app.mylibrary.ui.addbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.mylibrary.databinding.FragmentAddBookBinding;
import com.app.mylibrary.databinding.FragmentLoginBinding;
import com.app.mylibrary.ui.login.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddBookFragment extends Fragment {

    private FragmentAddBookBinding binding;

    private AddBookViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddBookViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.saveBtn.setOnClickListener(v -> viewModel.savePressed(
                binding.authorTxt.getText().toString(),
                binding.titleTxt.getText().toString(),
                binding.publishingHouseTxt.getText().toString(),
                binding.yearOfPublishingTxt.getText().toString(),
                binding.locationTxt.getText().toString(),
                binding.languageTxt.getText().toString(),
                binding.bookcaseLocationTxt.getText().toString()
        ));

        viewModel.message.observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );
        viewModel.goBack.observe(getViewLifecycleOwner(), direction ->
                Navigation.findNavController(view).popBackStack()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
