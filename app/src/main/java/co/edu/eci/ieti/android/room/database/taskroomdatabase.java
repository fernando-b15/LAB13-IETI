package co.edu.eci.ieti.android.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.eci.ieti.android.dao.taskdao;
import co.edu.eci.ieti.android.network.data.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)

public abstract class taskroomdatabase  extends RoomDatabase {
    public abstract taskdao taskdao();
    private static volatile taskroomdatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static taskroomdatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (taskroomdatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            taskroomdatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
