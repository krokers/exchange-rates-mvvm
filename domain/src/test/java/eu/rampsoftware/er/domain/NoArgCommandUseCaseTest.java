package eu.rampsoftware.er.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NoArgCommandUseCaseTest {

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();
    @Mock
    private Scheduler mWorkScheduler;
    @Mock
    private Scheduler mObserveScheduler;
    private NoArgCommandUseCaseTestClass mUseCase;
    private TestDisposableCompletableObserver mTestObserver;


    @Before
    public void setUp() {
        this.mUseCase = new NoArgCommandUseCaseTestClass(mWorkScheduler, mObserveScheduler);
        this.mTestObserver = new TestDisposableCompletableObserver();
    }

    @Test
    public void thatObserverDisposed() {
        mUseCase.run(mTestObserver);

        mUseCase.dispose();

        assertThat(mTestObserver.isDisposed()).isTrue();
    }

    @Test
    public void thatExceptionThrownOnNullObserver() {
        mExpectedException.expect(NullPointerException.class);
        mUseCase.run(null);
    }

    private static class NoArgCommandUseCaseTestClass extends NoArgCommandUseCase {
        NoArgCommandUseCaseTestClass(final Scheduler workScheduler, final Scheduler observeScheduler) {
            super(workScheduler, observeScheduler);
        }

        @Override
        protected Completable buildUseCaseObservable() {
            return new Completable() {
                @Override
                protected void subscribeActual(final CompletableObserver s) {

                }
            };
        }
    }
}
