package eu.rampsoftware.er.domain;


import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
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

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    QueryUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void run(DisposableObserver<R> observer, T params) {
        Preconditions.checkNotNull(observer);
        final Observable<R> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    protected abstract Observable<R> buildUseCaseObservable(final T params);


}
