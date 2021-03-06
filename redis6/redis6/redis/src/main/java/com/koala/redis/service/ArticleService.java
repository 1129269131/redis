package com.koala.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * day02：
 *  微信文章阅读量统计
 * Create by koala on 2021-11-13
 */
@Service
@Slf4j
public class ArticleService
{
    public static final String ARTICLE = "article:";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void likeArticle(String articleId)
    {
        String key = ARTICLE+articleId;
        Long likeNumber = stringRedisTemplate.opsForValue().increment(key);
        log.info("文章编号:{},喜欢数:{}",key,likeNumber);
    }
}
