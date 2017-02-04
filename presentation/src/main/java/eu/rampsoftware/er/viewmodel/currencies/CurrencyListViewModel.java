package eu.rampsoftware.er.viewmodel.currencies;

import android.databinding.ObservableList;
import android.os.Bundle;

import eu.rampsoftware.er.viewmodel.BaseViewModel;

public class CurrencyListViewModel implements BaseViewModel {

    private ObservableList<CurrencyItemViewModel> mCurrencies;

    public ObservableList<CurrencyItemViewModel> getCurrencies() {
        return mCurrencies;
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onSaveInstanceState(final Bundle bundle) {

    }

    @Override
    public void onRestoreInstanceState(final Bundle bundle) {

    }
}
