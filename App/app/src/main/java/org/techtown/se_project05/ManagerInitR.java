package org.techtown.se_project05;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ManagerInitR extends StringRequest {
    final static private String URL = "http://hyse.dothome.co.kr/GetClass.php";
    private Map<String, String> map;
    public ManagerInitR(String userID,Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
