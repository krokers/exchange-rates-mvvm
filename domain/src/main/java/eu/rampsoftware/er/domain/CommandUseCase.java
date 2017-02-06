package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 *
 * @param <T>
 */
public abstract class CommandUseCase<T> extends DisposableUseCase {
    private final Scheduler mWorkScheduler;
    private final Scheduler mObserveScheduler;

    CommandUseCase(Scheduler workScheduler, Scheduler observeScheduler) {
        this.mWorkScheduler = workScheduler;
        this.mObserveScheduler = observeScheduler;
    }

    public void run(DisposableCompletableObserver observer, T params) {
        Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseObservable(params)
                .subscribeOn(mWorkScheduler)
                .observeOn(mObserveScheduler);
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Completable buildUseCaseObservable(final T params);

}
