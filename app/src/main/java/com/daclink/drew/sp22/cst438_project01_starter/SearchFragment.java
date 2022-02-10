package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.SearchResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentSearchBinding;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.SearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchViewModel viewModel;
    private SearchResultsAdapter adapter;

    private TextInputEditText keywordEditText, authorEditText;
    private Button searchButton;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentSearchToFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SearchFragment.this)
                        .navigate(R.id.action_SearchFragment_to_FirstFragment);
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new SearchResultsAdapter();

        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        viewModel.init();
        viewModel.getVolumesResponseLiveData().observe(this, new Observer<APIValues>() {
            @Override
            public void onChanged(APIValues response) {
                if (response != null) {
                    adapter.setResults(response);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_search_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_search_keyword);
        //authorEditText = view.findViewById(R.id.fragment_search_author);
        searchButton = view.findViewById(R.id.fragment_search_search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return view;
    }

    public void performSearch() {
        String title = keywordEditText.getEditableText().toString();

        viewModel.searchVolumes(title);
    }
}