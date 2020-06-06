package com.applicationgame.tv_guide.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.applicationgame.tv_guide.communication.Channel;
import com.applicationgame.tv_guide.repository.Repo;
import com.applicationgame.tv_guide.communication.Program;

import java.util.List;

public class ProgramViewModel extends AndroidViewModel {

    private Repo repo;
    private LiveData<List<Program>> programs;
    int id;
    LiveData<List<Program>> programsWithId;

    public ProgramViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        programs = repo.getAllPrograms();
    }

    public LiveData<List<Program>> getPrograms() {
        return programs;
    }
    public LiveData<List<Program>> getProgramsWithId(int id) {
        return repo.getProgramsWithId(id);
    }

    public void setPrograms(LiveData<List<Program>> programs) {
        this.programs = programs;
    }

    public void insert(Program program) {
        repo.insertProgram(program);
    }

    public void update(Channel channel) {
        repo.update(channel);
    }
}
