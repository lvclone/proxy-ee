package com.github.monkeywie.proxyee.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 文章实体类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-28 16:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpCache {

    @Id
    private Long id;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头部信息
     */
    private Map<String, String> requestHeader;

    /**
     * 文章内容
     */
    private Map<String, String> responseHeader;


    private String responseContent;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
