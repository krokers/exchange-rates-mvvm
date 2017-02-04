package eu.rampsoftware.er.viewmodel;

import android.os.Bundle;

/**
 * Created by Ramps on 2017-02-04.
 */

public interface BaseViewModel {
    void onLoad();
    void onSaveInstanceState(Bundle bundle);
    void onRestoreInstanceState(Bundle bundle);
}
