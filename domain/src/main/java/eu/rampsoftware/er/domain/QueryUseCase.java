package eu.rampsoftware.er.domain;


import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Abstraction for single unit of work from business logic standpoint. Purpose of this use case
 * is to query for data.
 *
 * @param <R> Data returned to the observer.
 * @param <T> Parameters needed to perform the unit of work.
 */
public abstract class QueryUseCase<R, T> {

    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    protected QueryUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public Observable<R> run(T params) {
        final Observable<R> observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        return observable;
    }

    protected abstract Observable<R> buildUseCaseObservable(final T params);


}
