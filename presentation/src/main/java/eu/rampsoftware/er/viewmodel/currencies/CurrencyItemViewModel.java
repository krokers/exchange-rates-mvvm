package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Locale;

import eu.rampsoftware.er.BR;
import eu.rampsoftware.er.navigation.Navigator;

import static com.fernandocejas.arrow.checks.Preconditions.checkNotNull;

public class CurrencyItemViewModel extends BaseObservable {

    private final String mCurrencyCode;
    private final String mCurrencyValue;
    private final Navigator mNavigator;

    public CurrencyItemViewModel(Navigator navigator, final String currencyCode, final Double value) {
        this.mNavigator = checkNotNull(navigator);
        mCurrencyCode = currencyCode;
        mCurrencyValue = String.format(Locale.ENGLISH, "%.2f $", value);
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

    public void onItemClicked(Object source) {
        mNavigator.navigateToCurrencyDetails(mCurrencyCode);
    }

}
