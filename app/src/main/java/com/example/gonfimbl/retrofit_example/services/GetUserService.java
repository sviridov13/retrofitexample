package com.example.gonfimbl.retrofit_example.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.gonfimbl.retrofit_example.models.User;
import com.example.gonfimbl.retrofit_example.network.UserService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class GetUserService extends IntentService {

    private static final String TAG = GetUserService.class.getSimpleName();

    public static final String SHOW_USER_ACTION = "com.example.gonfimbl.SHOW_USER_ACTION";

    private static final String BASE_URL = "http://demo8782219.mockable.io/";

    public GetUserService() {
        super("GetUserService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, intent.toUri(0));
        User user;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<User> call = service.fetchUser();
        try {
            Response<User> userResponse = call.execute();
            user = userResponse.body();
            sendUserInActivity(user);
            stopSelf();
        } catch (IOException e) {
            stopSelf();
            Log.e(TAG, e.getMessage());
        }
    }

    private void sendUserInActivity(User user) {
        Intent intent = new Intent(SHOW_USER_ACTION);
        intent.putExtra("name", user.getName());
        intent.putExtra("surname", user.getSurname());
        intent.putExtra("birthdate", user.getBirthDate());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


}
