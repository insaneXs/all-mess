package com.insanexs.mess.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: xieshang
 * @Description:
 * @Date: Create at 2019-12-03
 */
public class FastJsonTest {

    @Test
    public void deserializeTest(){
        testJSONParse();
        testJSONArrayParse();
    }

    protected static void testJSONParse(){
        System.out.println("**************Start Test JSON Parse");
        Article originalArticle = new Article("Article 1", "This is content", AuditStatus.AUDITING);
        String jsonStr = JSON.toJSONString(originalArticle);

        System.out.println("Serialize to json string: " + jsonStr);

        Article deserializeArticle = JSON.parseObject(jsonStr, Article.class);

        System.out.println("Deserialize to Java Object: " + deserializeArticle + "; and the status is " + deserializeArticle.getStatus());
        //Equals
        Assert.assertTrue(deserializeArticle.getStatus().equals(AuditStatus.AUDITING));
        Assert.assertEquals(originalArticle, deserializeArticle);
    }

    protected static void testJSONArrayParse(){
        System.out.println("**************Start Test JSONArray Parse");
        JSONArray arr = new JSONArray();
        Article originArticle = new Article("Article 1", "This is content", AuditStatus.AUDITING);
        arr.add(originArticle);

        String jsonArrStr = JSON.toJSONString(arr);
        System.out.println("Serialize to json array string: " + jsonArrStr);

        arr = JSON.parseArray(jsonArrStr);
        Article deserializeArticle = arr.getObject(0, Article.class);

        System.out.println("Deserialize to json arr, then to java object: " + deserializeArticle + "; and the status is " + deserializeArticle.getStatus());
        //Not Equals
        Assert.assertFalse(deserializeArticle.getStatus().equals(AuditStatus.AUDITING));
        Assert.assertNotEquals(originArticle, deserializeArticle);
    }
}
