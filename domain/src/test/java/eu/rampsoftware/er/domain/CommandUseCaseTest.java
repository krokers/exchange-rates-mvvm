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
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by Ramps on 2017-02-05.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandUseCaseTest {

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();
    @Mock
    private Scheduler mWorkScheduler;
    @Mock
    private Scheduler mObserveScheduler;
    private CommandUseCaseTestClass mUseCase;
    private TestDisposableCompletableObserver mTestObserver;


    @Before
    public void setUp() {
        this.mUseCase = new CommandUseCaseTestClass(mWorkScheduler, mObserveScheduler);
        this.mTestObserver = new TestDisposableCompletableObserver();
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

    private static class CommandUseCaseTestClass extends CommandUseCase<Params> {
        CommandUseCaseTestClass(final Scheduler workScheduler, final Scheduler observeScheduler) {
            super(workScheduler, observeScheduler);
        }

        @Override
        protected Completable buildUseCaseObservable(final Params params) {
            return new Completable() {
                @Override
                protected void subscribeActual(final CompletableObserver s) {

                }
            };
        }
    }

    private static class Params {
        public static Params EMPTY = new Params();
    }
}
