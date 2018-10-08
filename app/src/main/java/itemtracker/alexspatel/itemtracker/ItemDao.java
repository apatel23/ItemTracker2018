package itemtracker.alexspatel.itemtracker;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import java.util.List;

@Dao
public interface ItemDao {

    @Insert
    void insert(Item item);

    @Query("DELETE FROM item_table")
    void deleteAll();

    @Query("SELECT * from item_table ORDER BY item ASC")
    LiveData<List<Item>> getAllItems();
}