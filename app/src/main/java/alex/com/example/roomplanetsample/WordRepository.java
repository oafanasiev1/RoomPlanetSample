package alex.com.example.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.database.sqlite.SQLiteCursor;
import android.os.AsyncTask;

import java.util.List;

import alex.com.example.roomplanetsample.Word;
import alex.com.example.roomplanetsample.WordRoomDatabase;

class WordRepository {

    private static final SQLiteCursor WordRoomDatabase = ;
    private alex.com.example.roomwordsample.WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    WordRepository(Application application) {
        alex.com.example.roomplanetsample.WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private alex.com.example.roomwordsample.WordDao mAsyncTaskDao;

        insertAsyncTask(alex.com.example.roomwordsample.WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
