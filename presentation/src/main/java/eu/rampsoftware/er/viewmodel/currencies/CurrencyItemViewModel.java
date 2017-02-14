package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Locale;

import eu.rampsoftware.er.BR;

public class CurrencyItemViewModel extends BaseObservable{

    private final String mCurrencyCode;
    private final String mCurrencyValue;

    public CurrencyItemViewModel(final String currencyCode, final Double value) {
        mCurrencyCode = currencyCode;
        mCurrencyValue = String.format(Locale.ENGLISH,"%.2f $", value);
        notifyPropertyChanged(BR.currencyCode);
        notifyPropertyChanged(BR.currencyValue);
    }

    @Bindable
    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    @Bindable
    public String getCurrencyValue() {
        return mCurrencyValue;
    }
}
