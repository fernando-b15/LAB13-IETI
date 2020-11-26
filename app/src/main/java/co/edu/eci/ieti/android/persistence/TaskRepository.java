package co.edu.eci.ieti.android.persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.edu.eci.ieti.android.dao.taskdao;
import co.edu.eci.ieti.android.network.data.Task;
import co.edu.eci.ieti.android.room.database.taskroomdatabase;

public class TaskRepository {
    private taskdao mtaskDao;
    private List<Task> tasks;

    TaskRepository(Application application) {
        taskroomdatabase db = taskroomdatabase.getDatabase(application);
        mtaskDao = db.taskdao();
        tasks = mtaskDao.getAllTask();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Task>> getAllTask() {
        return tasks;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWordDao.insert(word);
        });
    }
}
