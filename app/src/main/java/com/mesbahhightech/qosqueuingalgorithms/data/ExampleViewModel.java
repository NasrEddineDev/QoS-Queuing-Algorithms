package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesbahhightech.qosqueuingalgorithms.data.Example;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExampleViewModel extends AndroidViewModel {
    private ExampleRepository exampleRepository;
    private LiveData<List<Example>> allExamples;
    private List<Example> allExamples1;

    public ExampleViewModel(@NonNull Application application) {
        super(application);
        exampleRepository = new ExampleRepository(application);
        allExamples = exampleRepository.getAllExamples();
        allExamples1 = exampleRepository.getAllExamples1();
    }

    public long insert(Example example) throws ExecutionException, InterruptedException {
        return exampleRepository.insert(example);
    }

    public void update(Example example){
        exampleRepository.update(example);
    }

    public void delete(Example example){
        exampleRepository.delete(example);
    }

    public void deleteAllExamples(){
        exampleRepository.deleteAllExamples();
    }

    public LiveData<List<Example>> getAllExamples(){
        return allExamples;
    }
    public List<Example> getAllExamples1(){
        return allExamples1;
    }

    public Example getExampleById(int example_id){
        return exampleRepository.getExampleById(example_id);
    }

    public Example getExampleByName(String example_name){
        return exampleRepository.getExampleByName(example_name);
    }
}
