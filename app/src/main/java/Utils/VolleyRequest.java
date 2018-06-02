package Utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import API.KEYS;

public class VolleyRequest {
    KEYS API_KEY = new  KEYS();
    public void getData(String URL, final VolleyRequest.VolleyCallBack callBack) {
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> params = new HashMap();
                Log.v("getHeaders", "here" + params);
                params.put("X-Auth-Token", API_KEY.getFootballDataOrgKEY());
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(request);
    }

    public interface VolleyCallBack {
        void onSuccess(String data);
    }
}
