package com.app.mylibrary.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.mylibrary.R;
import com.app.mylibrary.databinding.FragmentSettingsBinding;
import com.app.mylibrary.domain.EFontSize;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    private SettingsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fontSizeButtonGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            switch (checkedId) {
                case R.id.smallBtn:
                    if (isChecked) {
                        viewModel.setFontSize(EFontSize.SMALL);
                    }
                    break;
                case R.id.normalBtn:
                    if (isChecked) {
                        viewModel.setFontSize(EFontSize.NORMAL);
                    }
                    break;

                case R.id.largeBtn:
                    if (isChecked) {
                        viewModel.setFontSize(EFontSize.LARGE);
                    }
                    break;
            }
        });
        binding.modeBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {

            viewModel.setMode(isChecked);
            updateMode(isChecked);
        });
        viewModel.settingsData.observe(getViewLifecycleOwner(), data -> {
                    binding.modeBtn.setChecked(data.isDarkModeEnabled());

                    switch (data.getFontScale()) {
                        case SMALL:
                            binding.smallBtn.setChecked(true);
                            break;
                        case NORMAL:
                            binding.normalBtn.setChecked(true);
                            break;
                        case LARGE:
                            binding.largeBtn.setChecked(true);
                            break;
                    }
                }
        );

        viewModel.recreate.observe(getViewLifecycleOwner(), action -> {
            recreate();
        });
        viewModel.viewCreated();
    }

    private void recreate() {
        requireActivity().finish();
        startActivity(requireActivity().getIntent());
        requireActivity().overridePendingTransition(0, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateMode(boolean darkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
