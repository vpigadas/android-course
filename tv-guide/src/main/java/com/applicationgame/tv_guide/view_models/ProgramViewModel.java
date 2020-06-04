package com.applicationgame.tv_guide.view_models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.applicationgame.tv_guide.Repo;
import com.applicationgame.tv_guide.communication.Program;

import java.util.List;

public class ProgramViewModel extends AndroidViewModel {

    private Repo repo;
    private LiveData<List<Program>> programs;

    public ProgramViewModel(@NonNull Application application) {
        super(application);
        repo = new Repo(application);
        programs = repo.getAllPrograms();
    }

    public LiveData<List<Program>> getPrograms() {
        return programs;
    }

    public void setPrograms(LiveData<List<Program>> programs) {
        this.programs = programs;
    }

    public void insert(Program program) {
        repo.insertProgram(program);
    }
}
