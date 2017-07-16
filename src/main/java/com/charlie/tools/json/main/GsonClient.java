package com.charlie.tools.json.main;

import com.charlie.tools.vo.UserVO;
import com.google.gson.Gson;

/**
 * Created by charlie on 04/04/2017.
 */
public class GsonClient {

    private Gson gson = new Gson();

    public void object2Json() {
        UserVO user = new UserVO();
        user.setAge(30);
        user.setName("Charlie");
        System.out.println(gson.toJson(user));
    }

    public void json2Object(String jsonStr) {
        UserVO user = gson.fromJson(jsonStr, UserVO.class);
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    public static void main(String[] args) {
        GsonClient ju = new GsonClient();
        ju.object2Json();

        String str = "{\"name\":\"Charlie\",\"age\":30}";
        ju.json2Object(str);
    }
}
