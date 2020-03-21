package utils;


import com.alibaba.fastjson.JSONObject;
import config.APIs;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @ClassNameRequestUtil
 * @Description
 * @Author 村头老杨头
 * @Date 2020/3/1 0001 10:53
 * @Version V1.0
 **/
public class RequestUtil {

    public static Map getData(String uri) throws UnsupportedEncodingException {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("apicode", APIs.API_CODE);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody=restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();

        Map weatherResponse= JSONObject.parseObject(strbody, Map.class);
        return weatherResponse;
    }
}
