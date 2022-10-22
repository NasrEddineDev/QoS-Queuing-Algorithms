package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "example")
public class Example {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int rows;
    private int columns;

    public Example(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
