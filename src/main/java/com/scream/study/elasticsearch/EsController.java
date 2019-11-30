package com.scream.study.elasticsearch;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/es")
@Api(tags = "es功能验证")
public class EsController {
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "测试 - addUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "entity", required = true)
    })
    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity entity) {
        return userDao.save(entity);
    }

//    @ApiOperation(value = "测试 - findById")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
//    })
//    @PostMapping("/findById/{id}")
//    public Optional<UserEntity> findById(@PathVariable String id) {
//        return userDao.findById(id);
//    }

    @ApiOperation(value = "测试 - deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    })
//    @PostMapping("/deleteById/{id}")
//    public void deleteById(@PathVariable String id) {
//        userDao.deleteById(id);
//    }

    @RequestMapping("createIndex")
    public void createIndex() {
        esTemplate.createIndex(UserEntity.class);
    }

    @RequestMapping("deleteIndex")
    public void deleteIndex() {
        esTemplate.deleteIndex(UserEntity.class);
    }

    @RequestMapping("saveData")
    public void saveData() {
        UserEntity entity = new UserEntity(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        userDao.save(entity);
    }
    @RequestMapping("saveAllData")
    public void saveAllData() {
        List<UserEntity> list = new ArrayList<>();
        list.add(new UserEntity(1L, "小米手机", " 手机", "小米", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new UserEntity(2L, "苹果手机", " 手机", "苹果", 3699.00, "http://image.baidu.com/13123.jpg"));
        list.add(new UserEntity(3L, "华为手机", " 手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        userDao.saveAll(list);
    }

    /**
     * elasticsearch中本没有修改，它的修改原理是该是先删除在新增
     * 修改和新增是同一个接口，区分的依据就是id。
     */
    @RequestMapping("updateData")
    public void updateData() {
        UserEntity UserEntity = new UserEntity(1L, "苹果XSMax", " 手机",
                "小米", 3499.00, "http://image.baidu.com/13123.jpg");
        userDao.save(UserEntity);
    }
    @RequestMapping("findAll")
    public Iterable<UserEntity> findAll(){
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<UserEntity> list = userDao.findAll(Sort.by("price").ascending());
        return list;
    }

    /**
     * MatchQuery
     * @return
     */
    @RequestMapping("matchQuery/{title}")
    public Page<UserEntity> matchQuery(@PathVariable String title){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", title));
        queryBuilder .withHighlightFields(new HighlightBuilder.Field("title").preTags("<strong>").postTags("</strong>").fragmentSize(15));
        // 搜索，获取结果
        Page<UserEntity> UserEntitys = userDao.search(queryBuilder.build());
        // 总条数
        long total = UserEntitys.getTotalElements();
        System.out.println("total = " + total);
        for (UserEntity UserEntity : UserEntitys) {
            System.out.println(UserEntity);
        }
        return UserEntitys;
    }

    /**
     * TermQuery
     * @return
     */
    @RequestMapping("termQuery/{title}")
    public Page<UserEntity> termQuery(@PathVariable String title){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("title", title));
        // 搜索，获取结果
        Page<UserEntity> UserEntitys = userDao.search(queryBuilder.build());
        // 总条数
        long total = UserEntitys.getTotalElements();
        System.out.println("total = " + total);
        for (UserEntity UserEntity : UserEntitys) {
            System.out.println(UserEntity);
        }
        return UserEntitys;
    }
    @RequestMapping("pageQuery/{title}")
    public Page<UserEntity> pageQuery(@PathVariable String title){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category", title));
        // 分页：
        int page = 0;
        int size = 2;
        queryBuilder.withPageable(PageRequest.of(page,size));

        // 搜索，获取结果
        Page<UserEntity> UserEntitys = userDao.search(queryBuilder.build());
        // 总条数
        long total = UserEntitys.getTotalElements();
        System.out.println("总条数 = " + total);
        // 总页数
        System.out.println("总页数 = " + UserEntitys.getTotalPages());
        // 当前页
        System.out.println("当前页：" + UserEntitys.getNumber());
        // 每页大小
        System.out.println("每页大小：" + UserEntitys.getSize());

        for (UserEntity UserEntity : UserEntitys) {
            System.out.println(UserEntity);
        }
        return UserEntitys;
    }
}
