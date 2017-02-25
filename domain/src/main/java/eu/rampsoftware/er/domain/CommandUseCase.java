package eu.rampsoftware.er.domain;


import io.reactivex.Completable;
import io.reactivex.Scheduler;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 *
 * @param <T>
 */
public abstract class CommandUseCase<T> {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    public CommandUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public Completable run(T params) {
        final Completable observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        return observable;
    }

    protected abstract Completable buildUseCaseObservable(final T params);

}
