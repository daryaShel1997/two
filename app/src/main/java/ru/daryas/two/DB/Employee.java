package ru.daryas.two.DB;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey
    public int id;

    public String name;

    public int salary;

    public Bitmap img;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Bitmap getImage() {
        return img;
    }
}
