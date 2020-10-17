package com.example.jhyangnewthings.constructor.estest;//package com.fiberhome.uqbing.constructor.estest;
//
///**
// * @Author jhYang
// * @Date 2020/4/29 0029 16:40
// * @Discription todo
// */
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Component;
//
///**
// * 测试用例
// */
//@Component
//public class EsBlogRepositorytest {
//    @Autowired
//    private EsBlogRepository esBlogRepository;
//
//    public void initRRepositoryData() {
//        //清楚所有数据
//        esBlogRepository.deleteAll();
//        esBlogRepository.save(new EsBlog("登鹳雀楼", "王之涣", "白日依山尽，黄河入海流，欲穷千里目，更上一层楼"));
//        esBlogRepository.save(new EsBlog("相思", "王维", "红豆生南国，春来发几枝。愿君多采撷，此物最相思"));
//        esBlogRepository.save(new EsBlog("静夜思", "李白", "床前明月光，疑是地上霜。举头望明月，低头思故乡"));
//    }
//
//    public void testFindDistinctByTitleContainingOrSummaryContainingOrContentContaining() {
//        Pageable pageable = new PageRequest(0, 20);//分页对象
//        String title = "思";
//        String summary = "相思";
//        String content = "相思";
//        Page<EsBlog> page = esBlogRepository.findDistinctByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
//
//        System.out.println("--------------start");
//        for (EsBlog blog : page.getContent())
//            System.out.println(blog.toString());
//    }
//}
//
//
