package itemtracker.alexspatel.itemtracker;

import java.util.List;
import android.arch.lifecycle.LiveData;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

public class ItemRepository {

    private ItemDao mItemDao;
    private LiveData<List<Item>> mAllItems;

    ItemRepository(Application application) {
        ItemRoomDatabase db = ItemRoomDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllItems = mItemDao.getAllItems();
    }

    LiveData<List<Item>> getAllItems() {
        return mAllItems;
    }

    public void insert (Item item) {
        new insertAsyncTask(mItemDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao mAsyncTaskDao;

        insertAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            String item = params[0].getItem();
            Integer exists = mAsyncTaskDao.selectItem(item);
            if (exists == 0) {
                mAsyncTaskDao.insert(params[0]);
            } else {
                // TODO: Display message: item already exists!
                Log.d("exists", "Item already exists");
            }

            return null;
        }
    }
}
