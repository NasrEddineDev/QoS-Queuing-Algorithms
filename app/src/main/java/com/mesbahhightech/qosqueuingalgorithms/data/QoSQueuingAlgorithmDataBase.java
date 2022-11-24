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
                    QoSQueuingAlgorithmDataBase.class, "qosqueuingalgorithmdatabase")
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

            exampleDao.insert(new Example("Exmple 01: SPQ, RR, WRR",4,20));
            queueDao.insert(new Queue("Time", "0.5;1;1.5;2;2.5;3;3.5;4;4.5;5;5.5;6;6.5;7;7.5;8;8.5;9;9.5;10",1));
            queueDao.insert(new Queue("File A", ";A;;;;A;A;;;A;A;;;;;;;;;",1));
            queueDao.insert(new Queue("File B", ";;;B;;B;;B;;B;;;;;;;;;;",1));
            queueDao.insert(new Queue("File C", ";C;;C;;;C;C;;;C;;;;;;;;;",1));

            exampleDao.insert(new Example("Exmple 02: SPQ, RR, WRR",4,20));
            queueDao.insert(new Queue("Time", "0.5;1;1.5;2;2.5;3;3.5;4;4.5;5;5.5;6;6.5;7;7.5;8;8.5;9;9.5;10",1));
            queueDao.insert(new Queue("File A", ";A;;;A;;;;A;A;;;A;;;;;;",1));
            queueDao.insert(new Queue("File B", ";B;;B;;;B;;B;;;B;B;;;;;;;",1));
            queueDao.insert(new Queue("File C", ";C;;C;C;C;C;;;C;;C;;C;;;;;;",1));

            exampleDao.insert(new Example("Exmple 03: SPQ, RR, WRR",4,20));
            queueDao.insert(new Queue("Time", "0.5;1;1.5;2;2.5;3;3.5;4;4.5;5;5.5;6;6.5;7;7.5;8;8.5;9;9.5;10",1));
            queueDao.insert(new Queue("File A", ";;;A;;;;A;;;;A;;;;A;;;;A",1));
            queueDao.insert(new Queue("File B", ";B;;B;;B;;B;;B;;B;;B;;B;;B;;B",1));
            queueDao.insert(new Queue("File C", "C;C;C;C;C;C;C;C;C;C;C;C;C;C;C;C;C;C;C;C",1));

            exampleDao.insert(new Example("Exmple 04: WRR-T,DWRR-T",3,8));
            queueDao.insert(new Queue("Time", "0.5;1;1.5;2;2.5;3;3.5;4;4.5;5;5.5;6;6.5;7;7.5;8;8.5;9;9.5;10",1));
            queueDao.insert(new Queue("File A", ";500;;;;700;;800;;;;300;;400;;;;;;",1));
            queueDao.insert(new Queue("File B", ";750;;1000;;;;950;;650;;550;;;;;;;;",1));
            queueDao.insert(new Queue("File C", ";550;;450;;750;;;;350;;;;550;;;;;;",1));

            exampleDao.insert(new Example("Exmple 05: WRR-T,DWRR-T",3,8));
            queueDao.insert(new Queue("Time", "0.5;1;1.5;2;2.5;3;3.5;4;4.5;5;5.5;6;6.5;7;7.5;8;8.5;9;9.5;10",1));
            queueDao.insert(new Queue("File A", ";1000;;600;500;;600;400;;500;;;;;;;;;;",1));
            queueDao.insert(new Queue("File B", ";400;;300;;400;300;600;;500;;;;;;;;;;",1));
            queueDao.insert(new Queue("File C", ";300;;200;400;;;500;400;;300;;;;;;;;;",1));

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
