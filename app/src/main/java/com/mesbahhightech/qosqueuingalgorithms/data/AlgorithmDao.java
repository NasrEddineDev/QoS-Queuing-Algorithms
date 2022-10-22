package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mesbahhightech.qosqueuingalgorithms.data.Algorithm;

import java.util.List;

@Dao
public interface AlgorithmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Algorithm algorithm);
    @Update
    void update(Algorithm algorithm);
    @Delete
    void delete(Algorithm algorithm);

    @Query("DELETE FROM algorithm")
    void deleteAllAlgorithms();

    @Query("SELECT * FROM algorithm ORDER BY id")
    LiveData<List<Algorithm>> getAllAlgorithms();

    @Query("SELECT * FROM algorithm where id=:algorithm_id")
    Algorithm getAlgorithmById(int algorithm_id);

    @Query("SELECT * FROM algorithm where name=:name")
    Algorithm getAlgorithmByName(String name);
}
