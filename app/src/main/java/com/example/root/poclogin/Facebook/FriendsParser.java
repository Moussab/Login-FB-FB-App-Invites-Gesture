package com.example.root.poclogin.Facebook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by root on 17/12/17.
 */

public class FriendsParser {

    public static ArrayList<Friend> getFriendsList(JSONObject s){
        ArrayList<Friend> list = new ArrayList<Friend>();
        try {

            String data = "";
            if (s.has("data")){
                data = s.getString("data");
            }


            JSONArray jsonArray = new JSONArray(data);

            Friend friend;
            for(int i=0;i<jsonArray.length();i++){
                friend = new Friend();
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                String id = "";
                if (jsonObject.has("id")){
                    id = jsonObject.getString("id");
                    friend.setId(id);
                }

                String name = "";
                if (jsonObject.has("name")){
                    name = jsonObject.getString("name");
                    friend.setName(name);
                }

                list.add(friend);
            }

        }catch (JSONException e) {
            return null;
        }
        return list;
    }


}
