package com.app.mylibrary.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mylibrary.databinding.ItemBookBinding;
import com.app.mylibrary.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ListAdapter<BookUi, BookAdapter.ViewHolder> implements Filterable {

    private List<BookUi> originalList = null;

    private String searchText = null;

    @Override
    public Filter getFilter() {
        return filter;
    }

    interface BookAdapterListener {

        void bookSelected(BookUi book);
    }

    private final BookAdapterListener listener;

    protected BookAdapter(BookAdapterListener listener) {
        super(new DiffUtil.ItemCallback<BookUi>() {

            @Override
            public boolean areItemsTheSame(@NonNull BookUi oldItem, @NonNull BookUi newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull BookUi oldItem, @NonNull BookUi newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }
        });
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookUi book = getItem(position);
        holder.binding.titleTxt.setText(book.getTitle());
        holder.binding.authorTxt.setText(book.getAuthor());
        holder.itemView.setOnClickListener(v -> listener.bookSelected(book));
    }

    @Override
    public void submitList(@Nullable List<BookUi> list) {
        submitList(list, false);
    }

    private void submitList(List<BookUi> list, Boolean filtered) {
        if (!filtered) {
            originalList = list;
        }

        super.submitList(list);
        if (searchText != null && !searchText.trim().isEmpty()) {
            getFilter().filter(searchText);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemBookBinding binding;

        public ViewHolder(@NonNull ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            searchText = constraint.toString();
            List<BookUi> filteredList = new ArrayList<>();

            if (!constraint.toString().trim().isEmpty()) {
                CharSequence query = constraint.toString().trim().toLowerCase();
                for (BookUi b : originalList) {
                    if (b.getTitle().toLowerCase().contains(query) ||
                            b.getAuthor().toLowerCase().contains(query)
                    ) {
                        filteredList.add(b);
                    }
                }
            } else {
                filteredList = originalList;
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            submitList((List<BookUi>) results.values, true);
        }
    };
}
