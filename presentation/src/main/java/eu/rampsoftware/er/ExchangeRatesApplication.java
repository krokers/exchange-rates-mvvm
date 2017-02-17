package eu.rampsoftware.er;

import android.app.Application;

import eu.rampsoftware.er.di.ApplicationComponent;
import eu.rampsoftware.er.di.ApplicationModule;
import eu.rampsoftware.er.di.DaggerApplicationComponent;
import io.realm.Realm;

public class ExchangeRatesApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    protected void initRealm() {
        Realm.init(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
