package org.techtown.se_project05;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddStudR extends StringRequest {
    final static private String URL = "http://hyse.dothome.co.kr/StudentAdd.php";
    private Map<String, String> map;

    public AddStudR(String studentID, int lectureID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("studentID", studentID);
        map.put("lectureID", Integer.toString(lectureID));
    }
    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}

