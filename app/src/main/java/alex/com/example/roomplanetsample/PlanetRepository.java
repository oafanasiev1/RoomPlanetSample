package alex.com.example.roomplanetsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class PlanetRepository {


    private PlanetDao mPlanetDao;
    private LiveData<List<Planet>> mAllPlanets;

    PlanetRepository(Application application) {
        PlanetRoomDatabase db = PlanetRoomDatabase.getDatabase(application);
        mPlanetDao = db.planetDao();
        mAllPlanets = mPlanetDao.getAllPlanets();
    }

    LiveData<List<Planet>> getAllPlanets() {
        return mAllPlanets;
    }


    public void insert (Planet planet) {
        new insertAsyncTask(mPlanetDao).execute(planet);
    }

    private static class insertAsyncTask extends AsyncTask<Planet, Void, Void> {

        private PlanetDao mAsyncTaskDao;

        insertAsyncTask(PlanetDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Planet... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
