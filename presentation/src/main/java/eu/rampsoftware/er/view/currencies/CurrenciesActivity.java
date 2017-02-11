package eu.rampsoftware.er.view.currencies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eu.rampsoftware.er.R;
import eu.rampsoftware.er.viewmodel.currencies.CurrencyListViewModel;

public class CurrenciesActivity extends AppCompatActivity {

    private CurrencyListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);
        mViewModel = new CurrencyListViewModel();
        mViewModel.onLoad();
    }


    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        mViewModel.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }
}
