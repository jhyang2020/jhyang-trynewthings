package com.example.jhyangnewthings.constructor.estest;//package com.fiberhome.uqbing.constructor.estest;
//
//import org.springframework.data.elasticsearch.annotations.Document;
//import javax.persistence.Id;
//import java.util.Date;
///**
// * @author 言曌
// * @date 2018/1/22 下午4:45
// */
//@Document(indexName="chuyun",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
//public class Article {
//    //文章ID，这里必须为 id
//    @Id
//    private Long id;
//    //标题
//    private String title;
//    //内容
//    private String content;
//    //浏览量
//    private Integer viewCount;
//    //发布时间
//    private Date createTime;
//    //更新时间
//    private Date updateTime;
//    public Article() {
//    }
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//    public String getTitle() {
//        return title;
//    }
//    public void setTitle(String title) {
//        this.title = title;
//    }
//    public String getContent() {
//        return content;
//    }
//    public void setContent(String content) {
//        this.content = content;
//    }
//    public Integer getViewCount() {
//        return viewCount;
//    }
//    public void setViewCount(Integer viewCount) {
//        this.viewCount = viewCount;
//    }
//    public Date getCreateTime() {
//        return createTime;
//    }
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                ", viewCount=" + viewCount +
//                ", createTime=" + createTime +
//                ", updateTime=" + updateTime +
//                '}';
//    }
//}