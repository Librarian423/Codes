package org.techtown.se_project05;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ScheduleR extends StringRequest {
    final static private String URL = "http://hyse.dothome.co.kr/StudentAtAdd";
    private Map<String, String> map;

    public ScheduleR(String userID, String date, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("date", date);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
