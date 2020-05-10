package com.applicationgame.tv_guide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.applicationgame.tv_guide.communication.Program;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.MyViewHolder> {

    private ArrayList<Program> programs;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView prName;
        TextView sTime;
        TextView eTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.prName = itemView.findViewById(R.id.programName);
            this.sTime = itemView.findViewById(R.id.startTime);
            this.eTime = itemView.findViewById(R.id.endTime);
        }
    }

    public FragmentAdapter(ArrayList<Program> data) {
        this.programs = data;
    }

    @Override
    public FragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_channel_page, parent, false);
//
//        view.setOnClickListener(MainActivity.myOnClickListener);

        FragmentAdapter.MyViewHolder myViewHolder = new FragmentAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final FragmentAdapter.MyViewHolder holder, final int listPosition) {

        TextView prName = holder.prName;
        TextView sTime = holder.sTime;
        TextView eTime = holder.eTime;

        // Convert the characters in order to show the greek ones as well
        String prNameTmp = null;

        try {
            prNameTmp = new String(programs.get(listPosition).getTitle().getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        prName.setText(prNameTmp);
        sTime.setText(programs.get(listPosition).getStartTimeCaption());
        eTime.setText(programs.get(listPosition).getEndTimeCaption());
    }

    @Override
    public int getItemCount() {
        return programs.size();
    }
}
