package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mesbahhightech.qosqueuingalgorithms.data.Example;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleDao;
import com.mesbahhightech.qosqueuingalgorithms.data.QoSQueuingAlgorithmDataBase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExampleRepository {
    private ExampleDao exampleDao;
    LiveData<List<Example>> allExamples;

    public ExampleRepository(Application application){
        QoSQueuingAlgorithmDataBase db = QoSQueuingAlgorithmDataBase.getInstance(application);
        exampleDao = db.exampleDao();
        allExamples = exampleDao.getAllExamples();
    }

    public long insert(Example example) throws ExecutionException, InterruptedException {
        return new InsertExampleAsyncTask(exampleDao).execute(example).get();
    }

    public void update(Example example){
        new UpdateExampleAsyncTask(exampleDao).execute(example);
    }

    public void delete(Example example){
        new DeleteExampleAsyncTask(exampleDao).execute(example);
    }

    public void deleteAllExamples(){
        new DeleteAllExamplesAsyncTask(exampleDao).execute();
    }

    public LiveData<List<Example>> getAllExamples(){
        return allExamples;
    }

    public Example getExampleById(int example_id){
        return exampleDao.getExampleById(example_id);
    }

    public Example getExampleByName(String example_name) {
        return exampleDao.getExampleByName(example_name);
    }

    private static class InsertExampleAsyncTask extends AsyncTask <Example, Void, Long>{
        private ExampleDao exampleDao;
        private InsertExampleAsyncTask(ExampleDao exampleDao){
            this.exampleDao = exampleDao;
        }
        @Override
        protected Long doInBackground(Example... examples){
            return exampleDao.insert(examples[0]);
            // return null;
        }
    }

    private static class UpdateExampleAsyncTask extends AsyncTask <Example, Void, Void>{
        private ExampleDao exampleDao;
        private UpdateExampleAsyncTask(ExampleDao exampleDao){
            this.exampleDao = exampleDao;
        }
        @Override
        protected Void doInBackground(Example... examples){
            exampleDao.update(examples[0]);
            return null;
        }
    }

    private static class DeleteExampleAsyncTask extends AsyncTask <Example, Void, Void>{
        private ExampleDao exampleDao;
        private DeleteExampleAsyncTask(ExampleDao exampleDao){
            this.exampleDao = exampleDao;
        }
        @Override
        protected Void doInBackground(Example... examples){
            exampleDao.delete(examples[0]);
            return null;
        }
    }

    private static class DeleteAllExamplesAsyncTask extends AsyncTask <Void, Void, Void>{
        private ExampleDao exampleDao;
        private DeleteAllExamplesAsyncTask(ExampleDao exampleDao){
            this.exampleDao = exampleDao;
        }
        @Override
        protected Void doInBackground(Void...voids){
            exampleDao.deleteAllExamples();
            return null;
        }
    }

//    private static class GetExampleByIdAsyncTask extends AsyncTask <int, Void, Void>{
//        private ExampleDao exampleDao;
//        private GetExampleByIdAsyncTask(ExampleDao exampleDao){
//            this.exampleDao = exampleDao;
//        }
//        @Override
//        protected Void doInBackground(Example... examples){
//            exampleDao.getExampleById();
//            return null;
//        }
//    }
//
//    private static class GetExampleByNameAsyncTask extends AsyncTask <Example, Void, Void>{
//        private ExampleDao exampleDao;
//        private GetExampleByNameAsyncTask(ExampleDao exampleDao){
//            this.exampleDao = exampleDao;
//        }
//        @Override
//        protected Void doInBackground(Example... Examples){
//            exampleDao.getExampleByName(Examples[0]);
//            return null;
//        }
//    }
}
