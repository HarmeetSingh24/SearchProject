package singh.harmeet.com.searchproject1.view;

import java.util.List;

import singh.harmeet.com.searchproject1.data.model.Event;

/**
 * Created by harmeet.assi on 4/10/2018.
 */

public interface MainView extends MView {

    void showResults(List<Event> result);

    void Error();

    int itemsVisible();

    void InternetError();
}
//                  "huge":"https://chairnerd.global.ssl.fastly.net/images/performers-landscape/oakland-athletics-f6dfcf/14/huge.jpg"
//                  "huge":"https://chairnerd.global.ssl.fastly.net/images/performers-landscape/oakland-athletics-f6dfcf/14/huge.jpg"
