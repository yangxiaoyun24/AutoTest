package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesTest {

    private ResourceBundle bundle;
    private String url;
    private CookieStore store;

    @BeforeTest
    public void BeforeTest(){
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies
        this.store = client.getCookieStore();
        List<Cookie> storeList = store.getCookies();

        for(Cookie cookie : storeList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("获取到的cookies：" + name + ":" + value);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void getWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl  = this.url + uri;

        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("get请求的返回值：" + result);
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void postWithCookies() throws IOException {
        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url + uri;

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(testUrl);
        client.setCookieStore(this.store);
        post.setHeader("content-type","application/json");
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        JSONObject jsonObject = new JSONObject(result);

        String name = jsonObject.getString("huhansan");
        String status = jsonObject.getString("status");
        System.out.println(name + "," + status);

    }
}
