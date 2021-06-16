package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "algorithm")
public class Algorithm {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;

    public Algorithm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
