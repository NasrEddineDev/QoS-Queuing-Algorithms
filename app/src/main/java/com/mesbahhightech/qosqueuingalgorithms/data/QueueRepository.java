package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QueueRepository {
    private QueueDao queueDao;
    LiveData<List<Queue>> allQueues;

    public QueueRepository(Application application){
        QoSQueuingAlgorithmDataBase db = QoSQueuingAlgorithmDataBase.getInstance(application);
        queueDao = db.queueDao();
        allQueues = queueDao.getAllQueues();
    }

    public void insert(Queue queue){
        new QueueRepository.InsertQueueAsyncTask(queueDao).execute(queue);
    }

    public void update(Queue queue){
        new QueueRepository.UpdateQueueAsyncTask(queueDao).execute(queue);
    }

    public void delete(Queue queue){
        new QueueRepository.DeleteQueueAsyncTask(queueDao).execute(queue);
    }

    public void deleteAllQueues(){
        new QueueRepository.DeleteAllQueuesAsyncTask(queueDao).execute();
    }

    public LiveData<List<Queue>> getAllQueues(){
        return allQueues;
    }

    public Queue getQueueById(int queue_id){
        return queueDao.getQueueById(queue_id);
    }

    private static class InsertQueueAsyncTask extends AsyncTask<Queue, Void, Void> {
        private QueueDao queueDao;
        private InsertQueueAsyncTask(QueueDao queueDao){
            this.queueDao = queueDao;
        }
        @Override
        protected Void doInBackground(Queue... queues){
            queueDao.insert(queues[0]);
            return null;
        }
    }

    private static class UpdateQueueAsyncTask extends AsyncTask <Queue, Void, Void>{
        private QueueDao queueDao;
        private UpdateQueueAsyncTask(QueueDao queueDao){
            this.queueDao = queueDao;
        }
        @Override
        protected Void doInBackground(Queue... queues){
            queueDao.update(queues[0]);
            return null;
        }
    }

    private static class DeleteQueueAsyncTask extends AsyncTask <Queue, Void, Void>{
        private QueueDao queueDao;
        private DeleteQueueAsyncTask(QueueDao queueDao){
            this.queueDao = queueDao;
        }
        @Override
        protected Void doInBackground(Queue... queues){
            queueDao.delete(queues[0]);
            return null;
        }
    }

    private static class DeleteAllQueuesAsyncTask extends AsyncTask <Void, Void, Void>{
        private QueueDao queueDao;
        private DeleteAllQueuesAsyncTask(QueueDao queueDao){
            this.queueDao = queueDao;
        }
        @Override
        protected Void doInBackground(Void...voids){
            queueDao.deleteAllQueues();
            return null;
        }
    }
}
