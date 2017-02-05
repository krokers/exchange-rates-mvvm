package eu.rampsoftware.er.domain;

import io.reactivex.observers.DisposableObserver;

public class TestDisposableObserver<T> extends DisposableObserver<T> {
    private int valuesCount = 0;

    @Override
    public void onNext(T value) {
        valuesCount++;
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}