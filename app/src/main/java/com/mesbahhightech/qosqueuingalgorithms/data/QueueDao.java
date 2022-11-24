package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mesbahhightech.qosqueuingalgorithms.data.Queue;

import java.util.List;

@Dao
public interface QueueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Queue queue);

    @Update
    void update(Queue queue);

    @Delete
    void delete(Queue queue);

    @Query("DELETE FROM queue")
    void deleteAllQueues();

    @Query("SELECT * FROM queue ORDER BY id")
    LiveData<List<Queue>> getAllQueues();

    @Query("SELECT * FROM queue where example_id=:example_id")
    Queue getQueueByExampleId(int example_id);

    @Query("SELECT * FROM queue where id=:queue_id")
    Queue getQueueById(int queue_id);
}
