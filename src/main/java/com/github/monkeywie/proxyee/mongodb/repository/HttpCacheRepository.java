package com.github.monkeywie.proxyee.mongodb.repository;

import com.github.monkeywie.proxyee.mongodb.model.HttpCache;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * <p>
 * 文章 Dao
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-28 16:30
 */
public interface HttpCacheRepository extends MongoRepository<HttpCache, Long> {
    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @return 满足条件的文章列表
     */
    List<HttpCache> findByUrl(String title);


    List<HttpCache> findByUrlLike(String title);

}
