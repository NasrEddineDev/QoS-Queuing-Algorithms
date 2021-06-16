package com.mesbahhightech.qosqueuingalgorithms.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mesbahhightech.qosqueuingalgorithms.data.Algorithm;
import com.mesbahhightech.qosqueuingalgorithms.data.AlgorithmRepository;

import java.util.List;

public class AlgorithmViewModel extends AndroidViewModel {
    private AlgorithmRepository algorithmRepository;
    private LiveData<List<Algorithm>> allAlgorithms;

    public AlgorithmViewModel(@NonNull Application application) {
        super(application);
        algorithmRepository = new AlgorithmRepository(application);
        allAlgorithms = algorithmRepository.getAllAlgorithms();
    }

    public void insert(Algorithm algorithm){
        algorithmRepository.insert(algorithm);
    }

    public void update(Algorithm algorithm){
        algorithmRepository.update(algorithm);
    }

    public void delete(Algorithm algorithm){
        algorithmRepository.delete(algorithm);
    }

    public void deleteAllAlgorithms(){
        algorithmRepository.deleteAllAlgorithms();
    }

    public LiveData<List<Algorithm>> getAllAlgorithms(){
        return allAlgorithms;
    }

    public Algorithm getAlgorithmById(int algorithm_id){
        return algorithmRepository.getAlgorithmById(algorithm_id);
    }

    public Algorithm getAlgorithmByName(String algorithm_name){
        return algorithmRepository.getAlgorithmByName(algorithm_name);
    }
}
