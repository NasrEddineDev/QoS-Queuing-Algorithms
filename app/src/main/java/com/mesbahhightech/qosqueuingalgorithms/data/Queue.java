package com.mesbahhightech.qosqueuingalgorithms.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "queue",
        foreignKeys = @ForeignKey(entity = Example.class,
        parentColumns = "id",
        childColumns = "example_id",
        onDelete = ForeignKey.CASCADE))
public class Queue {
    @PrimaryKey(autoGenerate = true)
    private int id;
    // if row_contents=A;B;C, then this row contain 3 column
    private String name;
    private String content;
    private int example_id;

    public Queue(String name, String content, int example_id) {
        this.name = name;
        this.content = content;
        this.example_id = example_id;
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

    public String getContent() {
        return content;
    }

    public int getExample_id() {
        return example_id;
    }
}
