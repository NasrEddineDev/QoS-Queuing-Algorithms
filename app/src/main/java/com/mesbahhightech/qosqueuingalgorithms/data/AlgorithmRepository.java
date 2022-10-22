package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlgorithmRepository {
    private AlgorithmDao algorithmDao;
    LiveData<List<Algorithm>> allAlgorithms;

    public AlgorithmRepository(Application application){
        QoSQueuingAlgorithmDataBase db = QoSQueuingAlgorithmDataBase.getInstance(application);
        algorithmDao = db.algorithmDao();
        allAlgorithms = algorithmDao.getAllAlgorithms();
    }

    public void insert(Algorithm algorithm){
        new AlgorithmRepository.InsertAlgorithmAsyncTask(algorithmDao).execute(algorithm);
    }

    public void update(Algorithm algorithm){
        new AlgorithmRepository.UpdateAlgorithmAsyncTask(algorithmDao).execute(algorithm);
    }

    public void delete(Algorithm algorithm){
        new AlgorithmRepository.DeleteAlgorithmAsyncTask(algorithmDao).execute(algorithm);
    }

    public void deleteAllAlgorithms(){
        new AlgorithmRepository.DeleteAllAlgorithmsAsyncTask(algorithmDao).execute();
    }

    public LiveData<List<Algorithm>> getAllAlgorithms(){
        return allAlgorithms;
    }

    public Algorithm getAlgorithmById(int algorithm_id){
        return algorithmDao.getAlgorithmById(algorithm_id);
    }

    public Algorithm getAlgorithmByName(String algorithm_name) {
        return algorithmDao.getAlgorithmByName(algorithm_name);
    }

    private static class InsertAlgorithmAsyncTask extends AsyncTask<Algorithm, Void, Void> {
        private AlgorithmDao algorithmDao;
        private InsertAlgorithmAsyncTask(AlgorithmDao algorithmDao){
            this.algorithmDao = algorithmDao;
        }
        @Override
        protected Void doInBackground(Algorithm... algorithms){
            algorithmDao.insert(algorithms[0]);
            return null;
        }
    }

    private static class UpdateAlgorithmAsyncTask extends AsyncTask <Algorithm, Void, Void>{
        private AlgorithmDao algorithmDao;
        private UpdateAlgorithmAsyncTask(AlgorithmDao algorithmDao){
            this.algorithmDao = algorithmDao;
        }
        @Override
        protected Void doInBackground(Algorithm... algorithms){
            algorithmDao.update(algorithms[0]);
            return null;
        }
    }

    private static class DeleteAlgorithmAsyncTask extends AsyncTask <Algorithm, Void, Void>{
        private AlgorithmDao algorithmDao;
        private DeleteAlgorithmAsyncTask(AlgorithmDao algorithmDao){
            this.algorithmDao = algorithmDao;
        }
        @Override
        protected Void doInBackground(Algorithm... algorithms){
            algorithmDao.delete(algorithms[0]);
            return null;
        }
    }

    private static class DeleteAllAlgorithmsAsyncTask extends AsyncTask <Void, Void, Void>{
        private AlgorithmDao algorithmDao;
        private DeleteAllAlgorithmsAsyncTask(AlgorithmDao algorithmDao){
            this.algorithmDao = algorithmDao;
        }
        @Override
        protected Void doInBackground(Void...voids){
            algorithmDao.deleteAllAlgorithms();
            return null;
        }
    }
}
