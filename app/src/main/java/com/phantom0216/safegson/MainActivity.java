package com.phantom0216.safegson;

import com.phantom0216.safegson.demo.R;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testGson();
    }

    private void testGson() {
        String str = "{\"hello1\":{\"sex\":\"100\"},\"hello2\":\"\"}";
        Gson gson = SafeGson.getSingletonGson();
        SafeGson.setNotifyCallback(new INotifyInterface() {

            @Override
            public void onSkipParseError(String info) {
                Log.e("test", "类型解析异常：" + info);
            }
        });
        // Gson gson = new Gson();
        Bean bean = gson.fromJson(str, Bean.class);
        Log.d("test", "bean: " + bean);
    }

    private static class Bean {
        public String name;
        public int age;
        public Hello hello1;
        public Hello hello2;

        @NonNull
        @Override
        public String toString() {
            return "Bean[name: " + name + ", age: " + age + ", hello1: " + hello1 + ", hello2: " + hello2;
        }
    }

    private static class Hello {
        public String sex;

        @NonNull
        @Override
        public String toString() {
            return "Hello[sex:" + sex + "]";
        }
    }
}