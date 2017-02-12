package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.properties.ResourcesApplicationProperties;

/**
 * Created by Ramps on 2017-02-12.
 */
@Module
public class TestApplicationModule {

    private final Context mContext;

    public TestApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    @Named("example")
    String provideExampleString() {
        return "Test Example";
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return mContext;
    }

    @Provides
    @Singleton
    public ApplicationProperties provideApplicationProperties(final Context context){
        return new ResourcesApplicationProperties(context);
    }
}
