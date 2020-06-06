package com.applicationgame.tv_guide.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.applicationgame.tv_guide.models.Channel;
import com.applicationgame.tv_guide.models.Program;
import com.applicationgame.tv_guide.db.ChannelDAO;
import com.applicationgame.tv_guide.db.ChannelDataBase;
import com.applicationgame.tv_guide.db.ProgramDAO;

import java.util.List;

public class Repo {

    private ChannelDAO channelDAO;
    private ProgramDAO programDAO;
    @NonNull
    private LiveData<List<Channel>> channels;
    @NonNull
    private LiveData<List<Program>> programs;
    @NonNull
    private LiveData<List<Program>> programsWithId;
    int id;


    public Repo(Application application) {
        ChannelDataBase db = ChannelDataBase.getInstance(application);
        channelDAO = db.channelDAO();
        channels   = channelDAO.getAllChannels();
        programDAO = db.programDAO();
        programs   = programDAO.getAllProg();
//        programsWithId = programDAO.getAllProgWithChannelId(id);

    }

    public LiveData<List<Channel>> getAllChannels() {
        return channels;
    }

    public LiveData<List<Program>> getAllPrograms() {
        return programs;
    }

    public LiveData<List<Program>> getProgramsWithId(int id) {
        return programDAO.getAllProgWithChannelId(id);
    }

    public void insertChannel (Channel channel) {
        new insertAsyncTaskforChannel(channelDAO).execute(channel);
    }

    public void insertProgram (Program program) {
        new insertAsyncTaskforProgram(programDAO).execute(program);
    }

    public void update(Channel channel){
        new UpdateChannelAsyncTask(channelDAO).execute(channel);
    }


    private static class insertAsyncTaskforChannel extends AsyncTask<Channel, Void, Void> {
        private ChannelDAO mAsyncTaskDAO;

        public insertAsyncTaskforChannel(ChannelDAO channelDAO) {
            mAsyncTaskDAO = channelDAO;
        }

        @Override
        protected Void doInBackground(final Channel... channels) {
            mAsyncTaskDAO.insert(channels[0]);
            return null;
        }
    }

    private static class insertAsyncTaskforProgram extends AsyncTask<Program, Void, Void> {
        private ProgramDAO mAsyncTaskDAO;

        public insertAsyncTaskforProgram(ProgramDAO programDAO) {
            mAsyncTaskDAO = programDAO;
        }

        @Override
        protected Void doInBackground(final Program... programs) {
            mAsyncTaskDAO.insert(programs[0]);
            return null;
        }
    }

    private static class UpdateChannelAsyncTask extends AsyncTask<Channel, Void, Void>{

        private ChannelDAO channelDAO;
        public UpdateChannelAsyncTask(ChannelDAO channelDAO) {
            this.channelDAO = channelDAO;
        }

        @Override
        protected Void doInBackground(Channel... channels) {
            channelDAO.update(channels[0]);
            return null;
        }
    }
}
