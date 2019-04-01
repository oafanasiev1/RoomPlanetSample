package alex.com.example.roomplanetsample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Planet.class}, version = 1)
public abstract class PlanetRoomDatabase extends RoomDatabase {

    public abstract PlanetDao planetDao();

    private static volatile PlanetRoomDatabase INSTANCE;


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlanetDao mDao;

        PopulateDbAsync(PlanetRoomDatabase db) {
            mDao = db.planetDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Planet planet = new Planet("1", "Earth", 9.8);
            mDao.insert(planet);
            planet = new Planet("2", "Mars", 14.8);
            mDao.insert(planet);
            return null;
        }
    }



    static PlanetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlanetRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlanetRoomDatabase.class, "planet_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
