package com.app.mylibrary.ui.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.mylibrary.R;
import com.app.mylibrary.databinding.FragmentInfoBinding;
import com.app.mylibrary.databinding.FragmentMainBinding;
import com.app.mylibrary.ui.login.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    private MainViewModel viewModel;

    private BookAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new BookAdapter(v -> Navigation.findNavController(view).navigate(MainFragmentDirections.actionMainFragmentToInfoFragment(v.getId())));
        binding.bookList.setAdapter(adapter);
        binding.addBtn.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.addBookFragment));
        binding.profileBtn.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.profileFragment));
        binding.settingsBtn.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.settingsFragment));
        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable query) {
                adapter.getFilter().filter(query);
            }
        });
        viewModel.message.observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );
        viewModel.booksData.observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
