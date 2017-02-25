package eu.rampsoftware.er.viewmodel;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseDisposableViewModel implements BaseViewModel {

    private final CompositeDisposable mDisposables;

    protected BaseDisposableViewModel() {
        mDisposables = new CompositeDisposable();
    }

    public void dispose() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    protected void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

}
