package alex.com.example.roomplanetsample;

import android.support.annotation.NonNull;
import android.arch.persistence.room.ColumnInfo;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;



@Entity(tableName = "planet_table")
public class Planet {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "planet_id")
    private String _id;        // database record id
    private String name;        // planet name
    private double gravity;      // gravity value on this planet's surface

    public Planet(String _id, String name, double gravity) {



        this._id = _id;
        this.name = name;
        this.gravity = gravity;
    }

    public String get_id() {
        return _id;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(Float gravity) {
        this.gravity = gravity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", gravity=" + gravity +
                '}';
    }

}
