package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesbahhightech.qosqueuingalgorithms.data.Example;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleRepository;

import java.util.List;

public class ExampleViewModel extends AndroidViewModel {
    private ExampleRepository exampleRepository;
    private LiveData<List<Example>> allExamples;

    public ExampleViewModel(@NonNull Application application) {
        super(application);
        exampleRepository = new ExampleRepository(application);
        allExamples = exampleRepository.getAllExamples();
    }

    public void insert(Example example){
        exampleRepository.insert(example);
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

    public Example getExampleById(int example_id){
        return exampleRepository.getExampleById(example_id);
    }

    public Example getExampleByName(String example_name){
        return exampleRepository.getExampleByName(example_name);
    }
}
