package com.bulat_galiev.task3.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.bulat_galiev.task3.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Socket;
import java.util.concurrent.ExecutionException;

/**
 * Created by BulatGaliev on 13.05.16.
 */
public class Requests {
    Context context;
    String cid;
    String sid;
    public Requests(Context context) {
        this.context = context;
    }
    public String getCid() {
        return cid;
    }
    private JSONObject makeRequest(JSONObject jsonRequest) {
        JSONObject returnJson = null;
        try {
            returnJson =  new ClientTask(context, SocketSingleton.getSocket()).execute(jsonRequest).get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("Exception", "Requests: cannot get json:" + e.toString());
        }
        return returnJson;
    }
    private boolean isResponseOk(JSONObject jsonResponse) {
        try {
            if (jsonResponse!=null && jsonResponse.has(context.getResources().getString(R.string.json_data))) {
                JSONObject data = jsonResponse.getJSONObject(context.getResources().getString(R.string.json_data));
                return (data.has(context.getResources().getString(R.string.json_status)) && (data.getInt(context.getResources().getString(R.string.json_status)) == 0));
            }
        } catch (JSONException e) {
            Log.e("Exception", "Requests: incorrect response:" + e.toString());
        }
        return false;
    }
    public boolean loginRequest(String login, String pass) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_auth));
            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_login), login);
            data.put(context.getResources().getString(R.string.json_pass), pass);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }
        JSONObject jsonResponse = makeRequest(jsonRequest);
        if (isResponseOk(jsonResponse)) {
            try {
                sid = jsonResponse.getJSONObject(context.getResources().getString(R.string.json_data)).getString(context.getResources().getString(R.string.json_sid));
                cid = jsonResponse.getJSONObject(context.getResources().getString(R.string.json_data)).getString(context.getResources().getString(R.string.json_cid));
            } catch (JSONException e) {
                Log.e("Exception", "Requests: cannot get cid or sid:" + e.toString());
            }
            return true;
        } else {
            return false;
        }


    }

    public boolean registerRequest(String login, String pass, String nick) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_register));
            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_login), login);
            data.put(context.getResources().getString(R.string.json_pass), pass);
            data.put(context.getResources().getString(R.string.json_nick), nick);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }

        return isResponseOk(makeRequest(jsonRequest));
    }

    public JSONObject chatListRequest() {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_chat_list));

            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_cid), cid);
            data.put(context.getResources().getString(R.string.json_sid), sid);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }
        return makeRequest(jsonRequest);
    }

    public boolean addChatRequest(String name, String about) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_create_channel));

            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_cid), cid);
            data.put(context.getResources().getString(R.string.json_sid), sid);
            data.put(context.getResources().getString(R.string.json_name), name);
            data.put(context.getResources().getString(R.string.json_about), about);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }
        return isResponseOk(makeRequest(jsonRequest));

    }

    public boolean changeAboutMeRequest(String status) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_set_user_info));

            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_status), status);
            data.put(context.getResources().getString(R.string.json_sid), sid);
            data.put(context.getResources().getString(R.string.json_cid), cid);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }
        return isResponseOk(makeRequest(jsonRequest));

    }

    public boolean aboutMeRequest(String user_id) {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put(context.getResources().getString(R.string.json_action), context.getResources().getString(R.string.action_user_info));

            JSONObject data = new JSONObject();
            data.put(context.getResources().getString(R.string.json_user_id), user_id);
            data.put(context.getResources().getString(R.string.json_sid), sid);
            data.put(context.getResources().getString(R.string.json_cid), cid);
            jsonRequest.put(context.getResources().getString(R.string.json_data), data);
        } catch (JSONException e) {
            Log.e("Exception", "Requests: cannot put request:" + e.toString());
        }
        return isResponseOk(makeRequest(jsonRequest));

    }
}
