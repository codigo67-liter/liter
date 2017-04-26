package mx.codigo67.liter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Parse setup
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zVjNCbde9NKs3yBd0ZuJFbd0qPSyIsDyxBXiyM9P")
                .clientKey("Eyx3but12t6OF50aiPHDdQ0W6IdXmjtc7htraceB")
                .server("https://parseapi.back4app.com")
                .build());

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        ParseFacebookUtils.initialize(this);
    }
}
