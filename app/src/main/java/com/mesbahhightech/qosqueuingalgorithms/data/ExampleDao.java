package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mesbahhightech.qosqueuingalgorithms.data.Example;

import java.util.List;

@Dao
public interface ExampleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Example example);

    @Update
    void update(Example example);

    @Delete
    void delete(Example example);

    @Query("DELETE FROM example")
    void deleteAllExamples();

    @Query("SELECT * FROM example ORDER BY id")
    LiveData<List<Example>> getAllExamples();

    @Query("SELECT * FROM example where id=:example_id")
    Example getExampleById(int example_id);

    @Query("SELECT * FROM example where name=:name")
    Example getExampleByName(String name);
}
