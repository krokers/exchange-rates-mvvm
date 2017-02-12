package eu.rampsoftware.er.properties;

import android.content.Context;

import eu.rampsoftware.er.R;

/**
 * Created by Ramps on 2017-02-12.
 */

public class ResourcesApplicationProperties implements ApplicationProperties {

    private final Context mContext;

    public ResourcesApplicationProperties(Context context){
        mContext = context;
    }


    @Override
    public String baseUrl(){
        return mContext.getResources().getString(R.string.base_url);
    }

    @Override
    public boolean logsEnabled() {
        return mContext.getResources().getBoolean(R.bool.logs_enabled);
    }
}
