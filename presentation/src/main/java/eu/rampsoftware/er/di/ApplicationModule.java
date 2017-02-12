package eu.rampsoftware.er.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.rampsoftware.er.properties.ApplicationProperties;
import eu.rampsoftware.er.properties.ResourcesApplicationProperties;

/**
 * Dagger module responsible for providing {@link ApplicationComponent} scope dependencies.
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context){
        mContext = context;
    }

    @Provides
    @Named("example")
    @Singleton
    public String provideExampleString() {
        return "Production Example";
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
