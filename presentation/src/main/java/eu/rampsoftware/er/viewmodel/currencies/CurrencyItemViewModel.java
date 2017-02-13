package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import eu.rampsoftware.er.BR;

/**
 * Created by Ramps on 2017-02-04.
 */

public class CurrencyItemViewModel extends BaseObservable{

    private final String mCurrencyCode;
    private final Double mCurrencyValue;

    public CurrencyItemViewModel(final String currencyCode, final Double value) {
        mCurrencyCode = currencyCode;
        mCurrencyValue = value;
        notifyPropertyChanged(BR.currencyCode);
        notifyPropertyChanged(BR.currencyValue);
    }

    @Bindable
    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    @Bindable
    public Double getCurrencyValue() {
        return mCurrencyValue;
    }
}
