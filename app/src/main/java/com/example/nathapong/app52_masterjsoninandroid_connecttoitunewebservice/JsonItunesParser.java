package com.example.nathapong.app52_masterjsoninandroid_connecttoitunewebservice;

        import com.example.nathapong.app52_masterjsoninandroid_connecttoitunewebservice.Model.ItunesStuff;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class JsonItunesParser {



   public static ItunesStuff getItunesStuff(String url) throws JSONException{

       ItunesStuff itunesStuff = new ItunesStuff();

       JSONObject itunesStuffJsonObject = new JSONObject(url);

       // access ไปยัง top level ของ JASON Array
       JSONArray resultJsonArray = itunesStuffJsonObject.getJSONArray("results");

       // access ไปยัง index 0 ของ JSON Array
       JSONObject artistObject = resultJsonArray.getJSONObject(0);

       // เรียกใช้ Method getString ดึงค่าจาก JSON Array index 0 แล้วเอาไปเก็บในตัวแปรต่างๆ โดยทำงานผ่าน Method set
       itunesStuff.setType(getString("wrapperType", artistObject));
       itunesStuff.setKind(getString("kind", artistObject));
       itunesStuff.setArtistName(getString("artistName", artistObject));
       itunesStuff.setCollectionName(getString("collectionName", artistObject));
       itunesStuff.setArtistViewURL(getString("artworkUrl100", artistObject));
       itunesStuff.setTrackName(getString("trackName", artistObject));

       return itunesStuff;

   }


    private static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getJSONObject(tagName);
    }


    private static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }


    private static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }


    private static boolean getBoolean(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getBoolean(tagName);
    }


    private static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }

}
