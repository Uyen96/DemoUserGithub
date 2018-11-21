package com.example.uyen.demousergithub;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String METHOD_REQUEST_API = "GET";
    private static final String RESPONSE_ID = "id";
    private static final String RESPONSE_NAME = "name";
    private static final String RESPONSE_FULL_NAME = "full_name";
    private static final String RESPONSE_DES = "description";
    private static final String URL = "https://api.github.com/users/google/repos";

    private List<User> mUsers = new ArrayList<>();
    private RecyclerView mRecyclerUser;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerUser = findViewById(R.id.recycler_user);
        new FetchData().execute();
    }

    private class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String user = "";
            try {
                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(METHOD_REQUEST_API);
                InputStream in = new BufferedInputStream(conn.getInputStream());
                user = convertStreamToString(in);
            } catch (Exception e) {

            }
            return user;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt(RESPONSE_ID);
                        String name = jsonObject.getString(RESPONSE_NAME);
                        String fullName = jsonObject.getString(RESPONSE_FULL_NAME);
                        String des = jsonObject.getString(RESPONSE_DES);
                        User user = new User(id, name, fullName, des);
                        mUsers.add(user);
                        mAdapter = new UserAdapter(mUsers);
                        mRecyclerUser.setAdapter(mAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
