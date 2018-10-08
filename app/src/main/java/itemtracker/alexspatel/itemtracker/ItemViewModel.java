package itemtracker.alexspatel.itemtracker;

import android.arch.lifecycle.AndroidViewModel;
import java.util.List;
import android.arch.lifecycle.LiveData;
import android.app.Application;

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository mRepository;
    private LiveData<List<Item>> mAllItems;

    public ItemViewModel (Application application) {
        super(application);
        mRepository = new ItemRepository(application);
        mAllItems = mRepository.getAllItems();
    }

    LiveData<List<Item>> getAllItems() { return mAllItems; }

    public void insert(Item item) { mRepository.insert(item); }

}
