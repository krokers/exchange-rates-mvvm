package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstraction for single unit of work from business logic standpoint. Purpose of this use case
 * is to query for data.
 *
 * @param <R> Data returned to the observer.
 * @param <T> Parameters needed to perform the unit of work.
 */
public abstract class QueryUseCase<R, T> extends DisposableUseCase {

    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    QueryUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public void run(DisposableObserver<R> observer, T params) {
        Preconditions.checkNotNull(observer);
        final Observable<R> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Observable<R> buildUseCaseObservable(final T params);


}
