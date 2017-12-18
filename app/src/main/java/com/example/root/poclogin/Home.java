package com.example.root.poclogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.poclogin.Facebook.Friend;
import com.example.root.poclogin.Facebook.FriendList;
import com.example.root.poclogin.Facebook.FriendsParser;
import com.example.root.poclogin.Gesture.GestureActivity;
import com.example.root.poclogin.Gesture.GestureListActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.GameRequestContent;
import com.facebook.share.widget.GameRequestDialog;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    //liste qui contient les ami(e)s qui utilsent la meme application
    private ArrayList<Friend> list = new ArrayList<Friend>();

    //send notification pour inviter mes ami(e)s a rejoindre la partie
    GameRequestDialog requestDialog;
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();


        requestDialog = new GameRequestDialog(this);
        requestDialog.registerCallback(callbackManager,
                new FacebookCallback<GameRequestDialog.Result>() {
                    public void onSuccess(GameRequestDialog.Result result) {
                        String id = result.getRequestId();
                        System.out.println(result);
                    }
                    public void onCancel() {}
                    public void onError(FacebookException error) {
                        System.out.println("helll");
                    }
                }
        );


        logoutListnner();

        getFriendsList();

        btnManager();





    }

    private void btnManager() {
        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,FriendList.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("friends", Home.this.list);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestButton();
            }
        });


        ((Button)findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,GestureListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFriendsList() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/"+ Profile.getCurrentProfile().getId()+"/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Home.this.list = FriendsParser.getFriendsList(response.getJSONObject());
                    }
                }
        ).executeAsync();
    }

    private void logoutListnner() {

        new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    finish();
                }
            }
        };

    }


    private void onClickRequestButton() {

        GameRequestContent content = new GameRequestContent.Builder()
                .setMessage("Come play this level with me")
                .build();
        requestDialog.show(content);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
