package com.example.jhyangnewthings.constructor.estest;//package com.fiberhome.uqbing.constructor.estest;
//
//import org.springframework.data.elasticsearch.annotations.Document;
//
//import javax.persistence.Id;
//import java.io.Serializable;//java提供的常用的通用数据保存和读取接口
//@Document(indexName = "blog",type = "blog" )//表示文档的注解，索引是blog
//public class EsBlog implements Serializable {
//    private static final long serialVersionUID = 1l;
//    @Id//表示主键
//    private String id;
//    private String title;
//    private String summary;
//    private String content;
//
//    protected EsBlog() {
//        //jpa的要求哦
//    }
//
//    public EsBlog(String title, String summary, String content)
//    {
//        this.title = title;
//        this.summary = summary;
//        this.content = content;
//    }
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSummary() {
//        return summary;
//    }
//
//    public void setSummary(String summary) {
//        this.summary = summary;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "EsBlog{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", summary='" + summary + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
//}
//
