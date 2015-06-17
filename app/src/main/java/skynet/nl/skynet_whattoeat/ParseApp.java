package skynet.nl.skynet_whattoeat;

/**
 * Created by steve_000 on 17-6-2015.
 */
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Parse inizialization code
        Parse.initialize(this, "QXKR33utE2hDQf3BS5MxEqAWbONNdNDWFsKuQU3F", "yEvhXoDLAciDyBFA8HoRvCUzWWyLzq9AZhaaaf3i");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }

}