package eu.rampsoftware.er.domain;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class DisposableUseCase {

    private final CompositeDisposable mDisposables;

    protected DisposableUseCase() {
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
