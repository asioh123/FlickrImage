package com.example.assy.flickrimage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by assy on 25/09/2015.
 */
public class jsonParse {

    public static List<Images> parseFeed(String content)
    {
        try{
            List<Images> ImagesList = new ArrayList<>();

            JSONObject object = new JSONObject(content);
            JSONObject photos = object.getJSONObject("photos");
            JSONArray ar = photos.getJSONArray("photo");



            for (int i = 0; i < ar.length(); i++) {
                JSONObject Jasonobject = ar.getJSONObject(i);
            }

            for(int i = 0 ;i<ar.length();i++)
            {
                JSONObject obj = ar.getJSONObject(i);
                Images ImagesListNew = new Images(obj.getString("id"),obj.getString("owner"),obj.getString("secret"),obj.getString("server"),obj.getString("farm"),obj.getString("title"),obj.getString("ispublic"),obj.getString("isfriend"),obj.getString("isfamily"));
                ImagesList.add(ImagesListNew);
            }

            return ImagesList;
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }


}
