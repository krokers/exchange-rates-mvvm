package eu.rampsoftware.er;

import android.app.Application;

import eu.rampsoftware.er.di.ApplicationComponent;
import eu.rampsoftware.er.di.ApplicationModule;
import eu.rampsoftware.er.di.DaggerApplicationComponent;

/**
 * Created by Ramps on 2017-02-12.
 */

public class ExchangeRatesApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
