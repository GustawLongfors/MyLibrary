package com.app.mylibrary.ui.profile;

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

import com.app.mylibrary.R;
import com.app.mylibrary.databinding.FragmentMainBinding;
import com.app.mylibrary.databinding.FragmentProfileBinding;
import com.app.mylibrary.ui.login.LoginViewModel;
import com.bumptech.glide.Glide;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    private ProfileViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.logoutBtn.setOnClickListener(v -> viewModel.logoutPressed());
        viewModel.message.observe(getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        );
        viewModel.navigation.observe(getViewLifecycleOwner(), direction ->
                Navigation.findNavController(view).navigate(direction)
        );
        viewModel.profileData.observe(getViewLifecycleOwner(), profileUi ->
                showProfileData(profileUi)
        );
        viewModel.logout.observe(getViewLifecycleOwner(), unused -> {
            Navigation.findNavController(view).popBackStack(R.id.loginFragment, false);
        });
        viewModel.viewCreated();
    }

    private void showProfileData(ProfileUi profileUi) {
        binding.emailTxt.setText(profileUi.getEmail());
        binding.booksCountTxt.setText(profileUi.getBooksCount() + "");

        Glide.with(this)
                .load(profileUi.getProfilePictureUrl())
                .into(binding.profilePicture);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
