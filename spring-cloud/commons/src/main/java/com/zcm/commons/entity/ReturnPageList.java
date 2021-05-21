package com.zcm.commons.entity;

import com.baomidou.mybatisplus.core.metadata.*;
import lombok.*;

import java.util.*;

/**
 * 返回数据类型数据统一封装
 *
 * @ClassName:
 * @author: SFH
 * @date: 2019/8/22  16:31
 */
@Data
@AllArgsConstructor
public class ReturnPageList {

    /**
     * 页码
     */
    private int pageIndex;
    /**
     * 单页数据量
     */
    private int pageSize;
    /**
     * 统计总数据量
     */
    private long totalCount;
    /**
     * 统计总行数
     */
    private int totalPages;
    /**
     * 数据实体
     */
    private List list;

    public ReturnPageList() {
    }

    /**
     * mysql分页
     *
     * @param iPage
     */
    public ReturnPageList(IPage iPage) {
        this.pageIndex = (int) iPage.getCurrent();
        this.pageSize = (int) iPage.getSize();
        this.totalCount = iPage.getTotal();
        this.totalPages = (int) iPage.getPages();
        this.list = iPage.getRecords();
    }

//    /**
//     * ES 分页
//     *
//     * @param pageIndex
//     * @param pageSize
//     * @param page
//     */
//    public ReturnPageList(int pageIndex, int pageSize, Page page) {
//        this.pageIndex = pageIndex;
//        this.pageSize = pageSize;
//        this.totalCount = page.getTotalElements();
//        //计算总页数
//        pageSize = pageSize == 0 ? 1 : pageSize;
//        this.totalPages = (int) (totalCount / pageSize);
//        if (totalCount % pageSize != 0) {
//            this.totalPages += 1;
//        }
//        this.list = page.getContent();
//    }

    /**
     * 默认  无数组分页
     *
     * @param pageIndex
     * @param pageSize
     * @param totalCount
     * @param totalPages
     */
    public ReturnPageList(int pageIndex, int pageSize, long totalCount, int totalPages) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = totalPages + 1;
        this.list = new ArrayList();
    }

    /**
     * @Author： zcm
     * @Date： 2020/8/19 19:40
     * @Version : V2.0.0
     * @Description : 分页计算
     */
    public ReturnPageList(int pageIndex, int pageSize, long totalCount) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        //计算总页数
        this.totalCount = totalCount;
        pageSize = pageSize == 0 ? 1 : pageSize;
        this.totalPages = (int) (totalCount / pageSize);
        if (totalCount % pageSize != 0) {
            this.totalPages += 1;
        }
        this.list = new ArrayList();
    }

    /**
     * 自动计算页数
     *
     * @param pageIndex
     * @param pageSize
     * @param totalCount
     * @param list
     * @Author sfh
     * @Date 2020/7/16 18:03
     * @version:2.0
     */
    public ReturnPageList(int pageIndex, int pageSize, Long totalCount, List list) {

        //计算总页数
        if (totalCount != null) {
            this.totalCount = totalCount;
            pageSize = pageSize == 0 ? 1 : pageSize;
            this.totalPages = (int) (totalCount / pageSize);
            if (totalCount % pageSize != 0) {
                this.totalPages += 1;
            }
        } else {
            this.totalPages = 0;
            this.totalCount = 0L;
        }

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.list = list;
    }

    public static ReturnPageList defaultReturnPageList() {
        ReturnPageList returnPageList = new ReturnPageList();
        returnPageList.setList(new ArrayList());
        returnPageList.setPageIndex(1);
        returnPageList.setPageSize(10);
        returnPageList.setTotalPages(1);
        returnPageList.setTotalCount(0);
        return returnPageList;
    }

}
