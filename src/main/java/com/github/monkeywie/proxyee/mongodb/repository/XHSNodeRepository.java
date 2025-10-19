//package com.github.monkeywie.proxyee.mongodb.repository;
//
//import com.github.monkeywie.proxyee.mongodb.model.xhs.Node;
//import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.List;
//
///**
// * <p>
// * 文章 Dao
// * </p>
// *
// * @author yangkai.shen
// * @date Created in 2018-12-28 16:30
// */
//public interface XHSNodeRepository extends MongoRepository<Node, String> {
//    /**
//     * 根据标题模糊查询
//     *
//     * @param title 标题
//     * @return 满足条件的文章列表
//     */
//    List<Node> findByDisplay_titleLike(String title);
//
//
//    List<Node> findByNote_id(String nodeId);
//}
