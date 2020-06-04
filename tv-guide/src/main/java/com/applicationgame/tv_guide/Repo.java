package com.applicationgame.tv_guide;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.communication.Program;
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


    public Repo(Application application) {
        ChannelDataBase db = ChannelDataBase.getInstance(application);
        channelDAO = db.channelDao;
        channels   = channelDAO.getAllChannels();
        programDAO = db.programDAO;
        programs   = programDAO.getAllProg();

    }

    public LiveData<List<Channel>> getAllChannels() {
        return channels;
    }

    public LiveData<List<Program>> getAllPrograms() {
        return programs;
    }

    public void insertChannel (Channel channel) {
        new insertAsyncTaskforChannel(channelDAO).execute(channel);
    }

    public void insertProgram (Program program) {
        new insertAsyncTaskforProgram(programDAO).execute(program);
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
}
