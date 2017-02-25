package eu.rampsoftware.er.domain;


import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Abstraction for single unit of work from business logic standpoint. Purpose of this use case
 * is to query for data.
 *
 * @param <R> Data returned to the observer.
 */
public abstract class NoArgQueryUseCase<R> {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    public NoArgQueryUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public Observable<R> run() {
        final Observable<R> observable = this.buildUseCaseObservable()
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        return observable;
    }

    protected abstract Observable<R> buildUseCaseObservable();

}
