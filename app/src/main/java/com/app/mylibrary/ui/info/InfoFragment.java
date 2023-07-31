package com.app.mylibrary.ui.info;

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

import com.app.mylibrary.databinding.FragmentInfoBinding;
import com.app.mylibrary.domain.Book;
import com.app.mylibrary.ui.login.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;

    private InfoViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(InfoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.editBtn.setVisibility(View.VISIBLE);
        binding.saveBtn.setVisibility(View.GONE);
        binding.deleteBtn.setVisibility(View.VISIBLE);
        binding.authorTxt.setEnabled(false);
        binding.titleTxt.setEnabled(false);
        binding.publishingHouseTxt.setEnabled(false);
        binding.yearOfPublishingTxt.setEnabled(false);
        binding.locationTxt.setEnabled(false);
        binding.languageTxt.setEnabled(false);
        binding.bookcaseLocationTxt.setEnabled(false);

        binding.saveBtn.setOnClickListener(v -> viewModel.savePressed(
                binding.authorTxt.getText().toString(),
                binding.titleTxt.getText().toString(),
                binding.publishingHouseTxt.getText().toString(),
                binding.yearOfPublishingTxt.getText().toString(),
                binding.locationTxt.getText().toString(),
                binding.languageTxt.getText().toString(),
                binding.bookcaseLocationTxt.getText().toString()
        ));

        binding.editBtn.setOnClickListener(v -> {
            binding.authorTxt.setEnabled(true);
            binding.titleTxt.setEnabled(true);
            binding.publishingHouseTxt.setEnabled(true);
            binding.yearOfPublishingTxt.setEnabled(true);
            binding.locationTxt.setEnabled(true);
            binding.languageTxt.setEnabled(true);
            binding.bookcaseLocationTxt.setEnabled(true);
            binding.editBtn.setVisibility(View.GONE);
            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.deleteBtn.setVisibility(View.GONE);
        });

        binding.deleteBtn.setOnClickListener(v -> viewModel.deleteBookPressed());
        viewModel.message.observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );
        viewModel.goBack.observe(getViewLifecycleOwner(), direction ->
                Navigation.findNavController(view).popBackStack()
        );
        viewModel.bookData.observe(getViewLifecycleOwner(), this::fillBookData);

        viewModel.getBookWithId(InfoFragmentArgs.fromBundle(requireArguments()).getBookId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fillBookData(Book book) {
        binding.authorTxt.setText(book.getAuthor());
        binding.titleTxt.setText(book.getTitle());
        binding.publishingHouseTxt.setText(book.getPublishingHouse());
        binding.yearOfPublishingTxt.setText(book.getYearOfPublishing() + "");
        binding.locationTxt.setText(book.getLocation());
        binding.languageTxt.setText(book.getLanguage());
        binding.bookcaseLocationTxt.setText(book.getBookcaseLocation());
    }
}
