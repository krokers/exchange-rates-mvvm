package eu.rampsoftware.er.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fernandocejas.arrow.checks.Preconditions;

import eu.rampsoftware.er.view.currencies.CurrencyDetailsActivity;

/**
 * Created by Ramps on 2017-02-18.
 */

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
