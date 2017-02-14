package eu.rampsoftware.er.properties;

import android.content.Context;

import eu.rampsoftware.er.R;

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

    @Override
    public String getOerAppId() {
        return mContext.getString(R.string.oer_app_id);
    }

    @Override
    public String sharedPreferencesName() {
        return mContext.getResources().getString(R.string.shared_preferences_name);
    }
}
