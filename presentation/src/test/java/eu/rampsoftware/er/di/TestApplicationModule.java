package eu.rampsoftware.er.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ramps on 2017-02-12.
 */
@Module
public class TestApplicationModule {

    @Provides
    @Named("example")
    String provideExampleString() {
        return "Test Example";
    }
}
