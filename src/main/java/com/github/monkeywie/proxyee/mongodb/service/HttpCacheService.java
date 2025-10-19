package com.github.monkeywie.proxyee.mongodb.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import com.github.monkeywie.proxyee.mongodb.model.HttpCache;
import com.github.monkeywie.proxyee.mongodb.repository.HttpCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class HttpCacheService {

    @Autowired
    private HttpCacheRepository httpCacheRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Snowflake snowflake;

    public HttpCache check(HttpCache httpCache) {
        log.info("check http cache {}", httpCache);
        List<HttpCache> byUrl = httpCacheRepo.findByUrl(httpCache.getUrl());

        if (null == byUrl || byUrl.isEmpty()) {
            httpCache.setId(snowflake.nextId());
            httpCache.setUpdateTime(DateUtil.date());
            httpCache.setCreateTime(DateUtil.date());
            httpCacheRepo.save(httpCache);
            log.info("Add Cache:{}", httpCache.getUrl());
            return null;
        } else {
            log.info("use Cache:{}", httpCache.getUrl());
            httpCache = byUrl.get(0);
        }

        return httpCache;
    }


    public boolean isHttpCacheEmpty(String title) {

        List<HttpCache> byUrl = httpCacheRepo.findByUrl(title);
        return null != byUrl && byUrl.isEmpty();
    }


}
