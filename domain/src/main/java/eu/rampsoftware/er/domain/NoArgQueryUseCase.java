package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;

/**
 * Abstraction for single unit of work from business logic standpoint. Purpose of this use case
 * is to query for data.
 *
 * @param <R> Data returned to the observer.
 */
public abstract class NoArgQueryUseCase<R> extends DisposableUseCase {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    public NoArgQueryUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public void run(DisposableObserver<R> observer) {
        Preconditions.checkNotNull(observer);
        final Observable<R> observable = this.buildUseCaseObservable()
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Observable<R> buildUseCaseObservable();

}
