package curso.mpgo.com.desafio1;

import android.app.Application;

import com.facebook.FacebookSdk;

//CICLO DE VIDA DA APLICAÇAO
/**
 * Created by ricardoogliari on 5/10/16.
 */
public class CoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}
