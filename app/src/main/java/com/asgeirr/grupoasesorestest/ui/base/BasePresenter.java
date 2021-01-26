package com.asgeirr.grupoasesorestest.ui.base;

import com.asgeirr.grupoasesorestest.data.AppDataManager;
import com.asgeirr.grupoasesorestest.data.DataManager;
import com.asgeirr.grupoasesorestest.data.remote.ApiHelper;
import com.asgeirr.grupoasesorestest.data.remote.AppApiHelper;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;
    private DataManager mDataManager;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        mDataManager = new AppDataManager(getMvpView().getmContext());
        getMvpView().hideLoading();
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    @Override
    public void onDetach() {
        mMvpView = null;
        mDataManager.detach();
        mDataManager = null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
