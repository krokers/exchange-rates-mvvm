package eu.rampsoftware.er.navigation;

import android.app.Activity;
import android.content.Intent;

import eu.rampsoftware.er.view.currencies.CurrencyDetailsActivity;


public class Navigator {

    private Activity mContext;

    public Navigator(final Activity context) {
        mContext = context;
    }

    public void navigateToCurrencyDetails(final String currencyCode) {
        Intent intentToLaunch = CurrencyDetailsActivity.getCallingIntent(mContext, currencyCode);
        mContext.startActivity(intentToLaunch);
    }
}
