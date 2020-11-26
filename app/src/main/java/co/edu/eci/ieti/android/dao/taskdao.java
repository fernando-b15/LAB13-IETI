package co.edu.eci.ieti.android.dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.edu.eci.ieti.android.network.data.Task;

public interface taskdao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table")
    List<Task> getAllTask();
}
