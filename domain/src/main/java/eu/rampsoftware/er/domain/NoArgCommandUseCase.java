package eu.rampsoftware.er.domain;


import io.reactivex.Completable;
import io.reactivex.Scheduler;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 */
public abstract class NoArgCommandUseCase {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    NoArgCommandUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public Completable run() {
        final Completable observable = this.buildUseCaseObservable()
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        return observable;
    }

    protected abstract Completable buildUseCaseObservable();

}
