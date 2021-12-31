package org.techtown.se_project05;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ManagerDelClassR extends StringRequest {
    final static private String URL = "http://hyse.dothome.co.kr/DelClass.php";
    private Map<String, String> map;
    public ManagerDelClassR(String userID, int lectureID, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("userID",userID);
        map.put("lectureID", Integer.toString(lectureID));
    }
    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
