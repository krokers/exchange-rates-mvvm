package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Abstraction for single unit of work which purpose is to change the state.
 */
public abstract class NoArgCommandUseCase extends DisposableUseCase {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    NoArgCommandUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void run(DisposableCompletableObserver observer) {
        Preconditions.checkNotNull(observer);
        final Completable observable = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Completable buildUseCaseObservable();

}
