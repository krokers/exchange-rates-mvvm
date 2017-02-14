package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 */
public abstract class NoArgCommandUseCase extends DisposableUseCase {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    NoArgCommandUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public void run(DisposableCompletableObserver observer) {
        Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseObservable()
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Completable buildUseCaseObservable();

}
