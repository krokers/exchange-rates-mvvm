package eu.rampsoftware.er.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ramps on 2017-02-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class QueryUseCaseTest {


    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();
    private Scheduler mWorkScheduler;
    private Scheduler mObserveScheduler;
    private QueryUseCaseTestClass mUseCase;
    private TestDisposableObserver<Object> mTestObserver;


    @Before
    public void setUp() {
        mWorkScheduler = new TestScheduler();
        mObserveScheduler = new TestScheduler();
        this.mUseCase = new QueryUseCaseTestClass(mWorkScheduler, mObserveScheduler);
        this.mTestObserver = new TestDisposableObserver<>();
    }

    @Test
    public void thatObserverDisposed() {
        mUseCase.run(mTestObserver, Params.EMPTY);

        mUseCase.dispose();

        assertThat(mTestObserver.isDisposed()).isTrue();
    }

    @Test
    public void thatExceptionThrownOnNullObserver() {
        mExpectedException.expect(NullPointerException.class);
        mUseCase.run(null, Params.EMPTY);
    }

    private static class QueryUseCaseTestClass extends QueryUseCase<Object, Params> {
        QueryUseCaseTestClass(final Scheduler workScheduler, final Scheduler observeScheduler) {
            super(workScheduler, observeScheduler);
        }

        @Override
        protected Observable<Object> buildUseCaseObservable(final Params params) {
            return Observable.empty();
        }
    }

    private static class Params {
        public static Params EMPTY = new Params();
    }
}
