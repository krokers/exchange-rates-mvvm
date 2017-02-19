package eu.rampsoftware.er.viewmodel;


import android.os.Bundle;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import eu.rampsoftware.er.BuildConfig;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase;
import eu.rampsoftware.er.viewmodel.details.CurrencyDetailsViewModel;
import io.reactivex.observers.DisposableObserver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, constants = BuildConfig.class)
public class CurrencyDetailsViewModelTest {

    @Rule
    public ExpectedException mExpectedException = ExpectedException.none();
    @Mock
    GetCurrencySeriesUseCase mGetCurrencySeriesUseCaseMock;

    @Captor
    ArgumentCaptor<DisposableObserver<SingleValue>> mObserverCaptor;

    @Captor
    ArgumentCaptor<GetCurrencySeriesUseCase.CurrencySeriesParam> mSeriesParamCaptor;

    private CurrencyDetailsViewModel viewModel;

    @Before
    public void setUp() {
        initMocks(this);
        viewModel = new CurrencyDetailsViewModel(mGetCurrencySeriesUseCaseMock);
    }

    @Test
    public void thatExceptionThrownOnNullInitialData() {
        mExpectedException.expect(NullPointerException.class);
        viewModel.onLoad(null);
    }

    @Test
    public void thatExceptionThrownOnMissingInitialData() {
        mExpectedException.expect(IllegalArgumentException.class);
        viewModel.onLoad(new Bundle());
    }

    @Test
    public void thatRequestPerformedOnLoad() {
        viewModel.onLoad(initialBundle("PLN"));

        verify(mGetCurrencySeriesUseCaseMock).run(mObserverCaptor.capture(), mSeriesParamCaptor.capture());
    }

    @Test
    public void thatProgressVisibleOnLoad() {
        assertThat(viewModel.isProgressVisible(), is(false));
        viewModel.onLoad(initialBundle("PLN"));
        assertThat(viewModel.isProgressVisible(), is(true));
    }

    @Test
    public void thatProgressDismissedOnDataLoaded() {
        viewModel.onLoad(initialBundle("PLN"));
        assertThat(viewModel.isProgressVisible(), is(true));
        verify(mGetCurrencySeriesUseCaseMock).run(mObserverCaptor.capture(), mSeriesParamCaptor.capture());
        final DisposableObserver<SingleValue> observer = mObserverCaptor.getValue();

        observer.onComplete();

        assertThat(viewModel.isProgressVisible(), is(false));
    }

    @Test
    public void thatUseCaseDisposedOnDispose(){
        viewModel.dispose();

        verify(mGetCurrencySeriesUseCaseMock).dispose();
    }

    private Bundle initialBundle(final String currencyCode) {
        Bundle bundle = new Bundle();
        bundle.putString(CurrencyDetailsViewModel.KEY_CURRENCY_CODE, currencyCode);
        return bundle;
    }

}
