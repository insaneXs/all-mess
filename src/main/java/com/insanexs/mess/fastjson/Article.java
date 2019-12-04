package com.insanexs.mess.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.insanexs.mess.fastjson.codec.AuditStatusCodec;

import java.util.Objects;

/**
 * @Author: insaneXs
 * @Description:
 * @Date: Create at 2019-12-03
 */
public class Article {

    private String title;

    private String content;

    @JSONField(serializeUsing = AuditStatusCodec.class, deserializeUsing = AuditStatusCodec.class)
    private AuditStatus status;

    public Article(){

    }

    public Article(String title, String content, AuditStatus status){
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AuditStatus getStatus() {
        return status;
    }

    public void setStatus(AuditStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(title, article.title) &&
                Objects.equals(content, article.content) &&
                status == article.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, content, status);
    }
}
