package com.mesbahhightech.qosqueuingalgorithms.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Example.class, Algorithm.class, Queue.class}, version = 1)
public abstract class QoSQueuingAlgorithmDataBase extends RoomDatabase {

    private static QoSQueuingAlgorithmDataBase instance;

    public abstract ExampleDao exampleDao();
    public abstract AlgorithmDao algorithmDao();
    public abstract QueueDao queueDao();

    public static synchronized QoSQueuingAlgorithmDataBase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    QoSQueuingAlgorithmDataBase.class, "qosqueuingalgorithm_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExampleDao exampleDao;
        private AlgorithmDao algorithmDao;
        private QueueDao queueDao;

        private PopulateDbAsyncTask(QoSQueuingAlgorithmDataBase db){
            exampleDao = db.exampleDao();
            algorithmDao = db.algorithmDao();
            queueDao = db.queueDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            exampleDao.insert(new Example("example 01",3,8));

            queueDao.insert(new Queue("File A", "A;;;B;;;;C",1));
            queueDao.insert(new Queue("File B", "A;;B;;;C;;C",1));
            queueDao.insert(new Queue("File C", ";B;;;A;;;C",1));

            algorithmDao.insert(new Algorithm("SPQ","Simple Priority Queuing"));
            algorithmDao.insert(new Algorithm("RR","Round Robin"));
            algorithmDao.insert(new Algorithm("WRR","Weighted Round Robin"));
            algorithmDao.insert(new Algorithm("FQ","Fair Queuing"));
            algorithmDao.insert(new Algorithm("WFQ","Weighted Fair Queuing"));
            algorithmDao.insert(new Algorithm("DWRR","Difiat Weighted Round Robin"));
            return null;
        }
    }
}
