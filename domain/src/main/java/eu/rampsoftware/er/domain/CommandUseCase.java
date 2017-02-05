package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 *
 * @param <T>
 */
public abstract class CommandUseCase<T> extends DisposableUseCase {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    CommandUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void run(DisposableCompletableObserver observer, T params) {
        Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Completable buildUseCaseObservable(final T params);

}
