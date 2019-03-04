package singh.harmeet.com.searchproject1.presenter;

import singh.harmeet.com.searchproject1.view.MView;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public class BasePresenter <V extends MView> implements Presenter<V> {

    private V mView;

    @Override
    public void attachView(V mView) {
        this.mView= mView;
    }

    @Override
    public void detachView() {
        mView=null;
    }

    boolean isViewAvailable(){
        return mView!=null;
    }

    public V getMView(){
        return mView;
    }

    public void checkmView(){
        if(!isViewAvailable()) throw new MViewNotAttachedException();
    }

    public static class  MViewNotAttachedException extends RuntimeException{
        public MViewNotAttachedException(){
            super();
        }
    }
}
