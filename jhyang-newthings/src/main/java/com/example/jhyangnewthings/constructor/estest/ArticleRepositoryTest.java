package com.example.jhyangnewthings.constructor.estest;//package com.fiberhome.uqbing.constructor.estest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
///**
// * @Author jhYang
// * @Date 2020/4/28 0028 17:53
// * @Discription todo
// */
//@Component
//public class ArticleRepositoryTest {
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    public void initRepositoryData() {
//        //清除所有数据
//        articleRepository.deleteAll();
//        Article article = new Article();
//        article.setId((long) 1);
//        article.setTitle("《蝶恋花》");
//        article.setContent("槛菊愁烟兰泣露，罗幕轻寒，燕子双飞去。明月不谙离恨苦，斜光到晓穿朱户。昨夜西风凋碧树，独上高楼，望尽天涯路。欲寄彩笺兼尺素，山长水阔知何处？");
//        article.setCreateTime(new Date());
//        article.setUpdateTime(new Date());
//        article.setViewCount(678);
//        articleRepository.save(article);
//        Article article2 = new Article();
//        article2.setId((long) 2);
//        article2.setTitle("《蝶恋花》");
//        article2.setContent("伫倚危楼风细细，望极春愁，黯黯生天际。草色烟光残照里，无言谁会凭阑意。拟把疏狂图一醉，对酒当歌，强乐还无味。衣带渐宽终不悔，为伊消得人憔悴。");
//        article2.setCreateTime(new Date());
//        article2.setUpdateTime(new Date());
//        article.setViewCount(367);
//        articleRepository.save(article2);
//        Article article3 = new Article();
//        article3.setId((long) 3);
//        article3.setTitle("《青玉案·元夕》");
//        article3.setContent("东风夜放花千树，更吹落，星如雨。宝马雕车香满路。凤箫声动，玉壶光转，一夜鱼龙舞。蛾儿雪柳黄金缕，笑语盈盈暗香去。众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。");
//        article3.setCreateTime(new Date());
//        article3.setUpdateTime(new Date());
//        article3.setViewCount(786);
//        articleRepository.save(article3);
//    }
//
//
//    public void findDistinctByTitleContainingOrContentContainingTest() throws Exception {
//        Pageable pageable = new PageRequest(0,20);
//        String title = "我爱罗琪";
//        String content = "花千树";
//        Page<Article> page = articleRepository.findDistinctByTitleContainingOrContentContaining(title, content, pageable);
//        System.out.println(page);
//        System.out.println("---start---");
//        for(Article article : page.getContent()) {
//            System.out.println(article.toString());
//        }
//        System.out.println("---end---");
//    }
//
//
//}