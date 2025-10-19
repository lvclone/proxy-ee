//package com.github.monkeywie.proxyee.mongodb.service;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Snowflake;
//import com.github.monkeywie.proxyee.mongodb.model.HttpCacheInterface;
////import com.github.monkeywie.proxyee.mongodb.model.xhs.Node;
////import com.github.monkeywie.proxyee.mongodb.repository.XHSNodeRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Slf4j
//@Component
//public class XHSNodeService {
//
//    @Autowired
//    private XHSNodeRepository xhsNodeRepository;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Autowired
//    private Snowflake snowflake;
//
//
//    public Node check(Node node) {
//        Node nodeR = xhsNodeRepository.findById(node.getNote_id()).get();
//
//
//
////        if (null == byUrl || byUrl.isEmpty()) {
////            httpCache.setId(snowflake.nextId());
////            httpCache.setUpdateTime(DateUtil.date());
////            httpCache.setCreateTime(DateUtil.date());
////            xhsNodeRepository.save(httpCache);
////            log.info("Add HttpCacheInterface:{}", httpCache.getUrl());
////            return null;
////        } else {
////            log.info("use HttpCacheInterface:{}", httpCache.getUrl());
////            httpCache = byUrl.get(0);
////        }
//
//        return nodeR;
//    }
//
//
////    public boolean isHttpCacheEmpty(String title) {
////
////        List<HttpCacheInterface> byUrl = xhsNodeRepository.findByUrl(title);
////        return null != byUrl && byUrl.isEmpty();
////    }
//
//
//}
