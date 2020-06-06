package com.applicationgame.tv_guide;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applicationgame.tv_guide.models.Program;
import com.applicationgame.tv_guide.view_models.ProgramViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChannelFragment extends Fragment {

    private static ArrayList<Program> program_list;
    private RecyclerView recyclerView;
    private FragmentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgramViewModel programViewModel;
//    ArrayList<Program>

    private boolean isConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) return false;

            return networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_recycler, container, false);

        programViewModel = ViewModelProviders.of(this).get(ProgramViewModel.class);
        recyclerView = rootView.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int id = getArguments().getInt("id");
        if(isConnected()) {
            program_list = getArguments().getParcelableArrayList("programList");
            adapter = new FragmentAdapter(program_list);
            recyclerView.setAdapter(adapter);
        }else {
            programViewModel.getProgramsWithId(id).observe(getViewLifecycleOwner(), new Observer<List<Program>>() {
                @Override
                public void onChanged(@Nullable final List<Program> programs) {
                    program_list = (ArrayList<Program>) programs;
                    adapter = new FragmentAdapter(program_list);
                    adapter.setPrograms(programs);
                    recyclerView.setAdapter(adapter);
                }
            });

        }
        return rootView;
    }

}
