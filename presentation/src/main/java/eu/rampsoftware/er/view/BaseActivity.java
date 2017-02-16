package eu.rampsoftware.er.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Base Activity which ensures that Activity scope dependencies are not
 * recreated after screen rotation, but are retained. This means that view model
 * attached to the activity will survive the rotation change. This is achieved by
 * simply retaining the dagger component created by this activity.<p>
 * You can check if activity is created for the first time or recreated due to orientation change
 * via {@link BaseActivity#isRetained(Bundle)} method.
 */
public abstract class BaseActivity<T> extends AppCompatActivity {

    private static final String KEY_IS_RETAINED = "KEY_IS_RETAINED";

    private T mComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(getActivityComponent());
    }

    protected abstract void injectDependencies(final T activityComponent);

    @SuppressWarnings("unchecked")
    protected T getActivityComponent() {
        if (null != mComponent) {
            return mComponent;
        }
        final Object retainedObject = getLastCustomNonConfigurationInstance();
        if (retainedObject != null) {
            //we are retaining the object, so we can safely cast it to our component class.
            mComponent = (T) retainedObject;
        } else {
            mComponent = newComponent();
        }
        return mComponent;
    }

    /**
     * Purpose of this method is to return new instance of dagger component.
     * It is ensured that this method is called only for the first time the activity
     * is created, and won't be called on activity recreation due to rotation change.
     *
     * @return
     */
    protected abstract T newComponent();

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getActivityComponent();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IS_RETAINED, isChangingConfigurations());
    }

    protected boolean isRetained(final Bundle state) {
        return state != null && state.containsKey(KEY_IS_RETAINED) && state.getBoolean(KEY_IS_RETAINED);
    }

}
