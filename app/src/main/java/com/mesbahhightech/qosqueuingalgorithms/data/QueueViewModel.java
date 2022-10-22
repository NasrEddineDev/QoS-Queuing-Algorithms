package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesbahhightech.qosqueuingalgorithms.data.Queue;
import com.mesbahhightech.qosqueuingalgorithms.data.QueueRepository;

import java.util.List;

public class QueueViewModel extends AndroidViewModel {
    private QueueRepository queueRepository;
    private LiveData<List<Queue>> allQueues;

    public QueueViewModel(@NonNull Application application) {
        super(application);
        queueRepository = new QueueRepository(application);
        allQueues = queueRepository.getAllQueues();
    }

    public void insert(Queue queue){
        queueRepository.insert(queue);
    }

    public void update(Queue queue){
        queueRepository.update(queue);
    }

    public void delete(Queue queue){
        queueRepository.delete(queue);
    }

    public void deleteAllQueues(){
        queueRepository.deleteAllQueues();
    }

    public LiveData<List<Queue>> getAllQueues(){
        return allQueues;
    }

    public Queue getQueueById(int queue_id){
        return queueRepository.getQueueById(queue_id);
    }
}
