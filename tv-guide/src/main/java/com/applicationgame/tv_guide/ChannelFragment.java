package com.applicationgame.tv_guide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationgame.tv_guide.communication.Program;

import java.util.ArrayList;

public class ChannelFragment extends Fragment {

    private static ArrayList<Program> program_list;
    private RecyclerView recyclerView;
    private FragmentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_recycler, container, false);

        recyclerView = rootView.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        program_list = getArguments().getParcelableArrayList("programList");

        adapter = new FragmentAdapter(program_list);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
