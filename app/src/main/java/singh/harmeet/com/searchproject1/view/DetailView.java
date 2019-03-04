package singh.harmeet.com.searchproject1.view;

import singh.harmeet.com.searchproject1.data.model.Event;

/**
 * Created by harmeet.assi on 4/15/2018.
 */

public interface DetailView extends MView{

    void showFavorite();

    void showError();

    void showItems();

    void hideFav();

    void loadItems(Event event);
}
