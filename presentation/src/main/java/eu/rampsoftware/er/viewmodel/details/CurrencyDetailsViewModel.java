package eu.rampsoftware.er.viewmodel.details;

import android.databinding.Bindable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.data.SingleValue;
import eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase;
import eu.rampsoftware.er.viewmodel.BaseDisposableViewModel;
import eu.rampsoftware.er.viewmodel.BaseViewModel;

import static com.fernandocejas.arrow.checks.Preconditions.checkArgument;
import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;
import static eu.rampsoftware.er.domain.usecases.GetCurrencySeriesUseCase.CurrencySeriesParam;


public class CurrencyDetailsViewModel extends BaseDisposableViewModel implements BaseViewModel {

    public static final String KEY_CURRENCY_CODE = "KEY_CURRENCY_CODE";
    private GetCurrencySeriesUseCase mGetSeriesUseCase;
    private boolean mIsProgressVisible;
    private List<SingleValue> mCurrencySeries;
    private String mCurrencyCode;

    public CurrencyDetailsViewModel(final GetCurrencySeriesUseCase getSeriesUseCase) {
        mGetSeriesUseCase = getSeriesUseCase;
        mCurrencySeries = new ArrayList<>();
    }

    @Bindable
    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(final String currencyCode) {
        mCurrencyCode = currencyCode;
        notifyPropertyChanged(BR.currencyCode);
    }

    @Bindable
    public List<SingleValue> getCurrencySeries() {
        return mCurrencySeries;
    }

    @Override
    public void onLoad(final Bundle bundle) {
        checkNotNull(bundle);
        checkArgument(bundle.containsKey(KEY_CURRENCY_CODE), "Currency code must be provided to onLoad method");
        setCurrencyCode(bundle.getString(KEY_CURRENCY_CODE));
        performRequest();
    }

    @Bindable
    public boolean isProgressVisible() {
        return mIsProgressVisible;
    }

    public void setProgressVisible(final boolean progressVisible) {
        mIsProgressVisible = progressVisible;
        notifyPropertyChanged(BR.progressVisible);
    }

    public void performRefresh() {
        performRequest();
    }

    private void performRequest() {
        setProgressVisible(true);
        Date endDate = new Date();
        int daysRange = 10;
        mCurrencySeries.clear();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_YEAR, -daysRange);
        Date from = calendar.getTime();
        CurrencySeriesParam params = new CurrencySeriesParam(from, endDate, mCurrencyCode);
        addDisposable(
                mGetSeriesUseCase.run(params)
                        .doFinally(() -> {
                            setProgressVisible(false);
                            notifyPropertyChanged(BR.currencySeries);
                        })
                        .subscribe(currencyData -> {
                            mCurrencySeries.add(currencyData);
                        })
        );
    }

}
