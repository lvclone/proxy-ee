package com.github.monkeywie.proxyee.mongo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.json.JSONUtil;
import com.github.monkeywie.proxyee.mongodb.model.HttpCache;
import com.github.monkeywie.proxyee.mongodb.repository.HttpCacheRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 测试操作 MongoDb
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-28 16:35
 */
@Slf4j
public class HttpCacheRepositoryTest extends SpringBootDemoMongodbApplicationTests {
    @Autowired
    private HttpCacheRepository httpCacheRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Snowflake snowflake;

    /**
     * 测试新增
     */
    @Test
    public void testSave() {
        HttpCache httpCache = new HttpCache(1L, "http", null, null, null, DateUtil.date(), DateUtil.date());
        httpCacheRepo.save(httpCache);
        log.info("【httpCache】= {}", JSONUtil.toJsonStr(httpCache));
    }

    /**
     * 测试新增列表
     */
    @Test
    public void testSaveList() {
        List<HttpCache> httpCaches = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            httpCaches.add(new HttpCache(snowflake.nextId(), "http", null, null, null, DateUtil.date(), DateUtil.date()));
        }
        httpCacheRepo.saveAll(httpCaches);

        log.info("【httpCaches】= {}", JSONUtil.toJsonStr(httpCaches.stream().map(HttpCache::getId).collect(Collectors.toList())));
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        httpCacheRepo.findById(1L).ifPresent(httpCache -> {
            httpCache.setUrl(httpCache.getUrl() + "更新之后的标题");
            httpCache.setUpdateTime(DateUtil.date());
            httpCacheRepo.save(httpCache);
            log.info("【httpCache】= {}", JSONUtil.toJsonStr(httpCache));
        });
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        // 根据主键删除
        httpCacheRepo.deleteById(1L);

        // 全部删除
//        httpCacheRepo.deleteAll();
    }

    /**
     * 测试点赞数、访客数，使用save方式更新点赞、访客
     */
//    @Test
//    public void testThumbUp() {
//        httpCacheRepo.findById(1L).ifPresent(httpCache -> {
//            httpCache.setThumbUp(httpCache.getThumbUp() + 1);
//            httpCache.setVisits(httpCache.getVisits() + 1);
//            httpCacheRepo.save(httpCache);
//            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", httpCache.getTitle(), httpCache.getThumbUp(), httpCache.getVisits());
//        });
//    }

    /**
     * 测试点赞数、访客数，使用更优雅/高效的方式更新点赞、访客
     */
//    @Test
//    public void testThumbUp2() {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("_id").is(1L));
//        Update update = new Update();
//        update.inc("thumbUp", 1L);
//        update.inc("visits", 1L);
//        mongoTemplate.updateFirst(query, update, "article");
//
//        httpCacheRepo.findById(1L).ifPresent(article -> log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits()));
//    }

    /**
     * 测试分页排序查询
     */
    @Test
    public void testQuery() {
        Sort sort = Sort.by("thumbUp", "updateTime").descending();
        PageRequest pageRequest = PageRequest.of(0, 40, sort);
        Page<HttpCache> all = httpCacheRepo.findAll(pageRequest);
        log.info("【总页数】= {}", all.getTotalPages());
        log.info("【总条数】= {}", all.getTotalElements());
        log.info("{}\t\t{}", "Content-Type", "url");

        for (HttpCache httpCache : all.getContent()) {
            log.info("{}\t\t\t\t{}", httpCache.getResponseHeader().get("Content-Type"), httpCache.getUrl() );
        }
//        log.info("【当前页数据】= {}", JSONUtil.toJsonStr(all.getContent().stream().map(article -> "文章标题：" + article.getUrl() + "resp：" + article.getResponseHeader() + "更新时间：" + article.getUpdateTime()).collect(Collectors.toList())));
    }

    /**
     * 测试根据标题模糊查询
     */
    @Test
    public void testFindByTitleLike() {
        List<HttpCache> httpCaches = httpCacheRepo.findByUrl("vuejs-core.cn/shop-vite/static/js/main-CTOsFuLe.js");
        HttpCache httpCache = httpCaches.get(0);
        String responseContent = httpCache.getResponseContent().replace("2024 kazuya kawaguchi", "hahah");
        responseContent = responseContent.replace("setTimeout((()=>{location.hostname.includes(\"127\")||location.hostname.includes(\"localhost\")||!location.hostname.includes(\"vuejs-core\")&&!disableDebugger||!o.query||\"auto\"===o.query.debugger||DisableDevtool({url:\"https://vuejs-core.cn/debugger\",timeOutUrl:\"https://vuejs-core.cn/debugger\"})}),1e3)", "console.log('已经跳过！！！');");
        httpCache.setResponseContent(responseContent);

        log.info("{}", httpCache.getId());
        log.info("{}", httpCache.getResponseContent());
        httpCacheRepo.save(httpCache);

//        log.info("【httpCaches】= {}", JSONUtil.toJsonStr(httpCaches));
    }

    @Test
    public void testFindById() {
        httpCacheRepo.deleteById(1857304450348224512L);
    }

}
