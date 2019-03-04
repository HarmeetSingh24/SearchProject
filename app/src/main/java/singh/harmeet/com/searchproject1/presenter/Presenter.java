package singh.harmeet.com.searchproject1.presenter;

import singh.harmeet.com.searchproject1.view.MView;

/**
 * Created by harmeet.assi on 4/11/2018.
 */


public interface Presenter<V extends MView> {

    void attachView(V mView);

    void detachView();
}
