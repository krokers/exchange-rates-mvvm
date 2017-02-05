package eu.rampsoftware.er.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.rampsoftware.er.domain.executor.PostExecutionThread;
import eu.rampsoftware.er.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by Ramps on 2017-02-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class QueryUseCaseTest {


    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();
    @Mock
    private ThreadExecutor mThreadExecutorMock;
    @Mock
    private PostExecutionThread mPostExecutionThreadMock;
    private QueryUseCaseTestClass mUseCase;
    private TestDisposableObserver<Object> mTestObserver;


    @Before
    public void setUp() {
        this.mUseCase = new QueryUseCaseTestClass(mThreadExecutorMock, mPostExecutionThreadMock);
        this.mTestObserver = new TestDisposableObserver<>();
        given(mPostExecutionThreadMock.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void thatObserverDisposed() {
        mUseCase.run(mTestObserver, Params.EMPTY);

        mUseCase.dispose();

        assertThat(mTestObserver.isDisposed()).isTrue();
    }

    @Test
    public void thatExceptionThrownOnNullObserver(){
        mExpectedException.expect(NullPointerException.class);
        mUseCase.run(null, Params.EMPTY);
    }

    private static class QueryUseCaseTestClass extends QueryUseCase<Object, Params> {
        QueryUseCaseTestClass(final ThreadExecutor threadExecutor, final PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
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
