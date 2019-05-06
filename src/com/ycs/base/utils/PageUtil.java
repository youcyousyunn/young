package com.ycs.base.utils;

import com.ycs.basbo.constants.Constants;

/**
 * 分页工具类
 * @author youcyousyunn
 * @date 2018年3月10日
 */
public class PageUtil {

	/**
     * 当前页
     */
    private static Integer currentPage = Constants.DEFAULT_CURRENT_PAGE;

    /**
     * 页大小
     */
    private static Integer pageSize = Constants.DEFAULT_PAGE_SIZE;

    /**
     * 总记录数
     */
    private static Integer totalCount = Constants.DEFAULT_TOTAL_COUNT;

    /**
     * 总页数
     */
    private static Integer totalPage = Constants.DEFAULT_TOTAL_PAGE;

    /**
     * 起始行
     */
    private static Integer row = Constants.DEFAULT_ROW;

    /**
     * 构造函数
     */
    private PageUtil() {
    }

    
    /**
     * 计算分页信息(只提供总条数,使用默认的页大小、当前页计算分页信息)
     * @param totalCount 总条数
     */
    public static void calculatePageInfo(int totalCount) {
        // 初始化信息
        initialize();
        PageUtil.totalCount = totalCount > 0 ? totalCount : Constants.DEFAULT_TOTAL_COUNT;
        // 计算分页数据
        PageUtil.totalPage = (totalCount + pageSize - 1) / pageSize;
        // 计算当前起始行数
        PageUtil.row = pageSize * currentPage;
    }

    /**
     * 计算分页数据
     * @param totalCount 总记录数
     * @param pageSize 页大小
     * @param currentPage 当前页
     */
    public static Page calculatePageInfo(int totalCount, int pageSize, int currentPage) {
        initialize();
        PageUtil.totalCount = totalCount > 0 ? totalCount : Constants.DEFAULT_TOTAL_COUNT;
        PageUtil.pageSize = pageSize > 0 ? pageSize : Constants.DEFAULT_PAGE_SIZE;
        PageUtil.currentPage = currentPage > 0 ? currentPage : Constants.DEFAULT_CURRENT_PAGE;
        // 计算分页数据
        PageUtil.totalPage = (PageUtil.totalCount + PageUtil.pageSize - 1) / PageUtil.pageSize;
        // 计算起始行
        PageUtil.row = PageUtil.pageSize * currentPage;
        return new Page(totalCount, row, pageSize);
    }

    /**
     * 获取总记录数
     * @return 总记录数
     */
    public static Integer getTotalCount() {
        return null != totalCount ? totalCount : Constants.DEFAULT_TOTAL_COUNT;
    }

    /**
     * 获取起始行
     * @return 起始行
     */
    public static Integer getStartRow() {
        return null != row ? row : Constants.DEFAULT_ROW;
    }

    /**
     * 获取当前页
     * @return 当前页
     */
    public static Integer getCurrentPage() {
        return null != currentPage ? currentPage : Constants.DEFAULT_CURRENT_PAGE;
    }

    /**
     * 获取页大小
     * @return 页大小
     */
    public static Integer getPageSize() {
        return null != pageSize ? pageSize : Constants.DEFAULT_PAGE_SIZE;
    }

    /**
     * 获取总页数
     * @return 总页数
     */
    public static Integer getTotalPage() {
        return null != totalPage ? totalPage : Constants.DEFAULT_TOTAL_PAGE;
    }

    /**
     * 获取偏移量(页大小)
     * @return 偏移量
     */
    public static Integer getOffset() {
        return null != pageSize ? pageSize : Constants.DEFAULT_PAGE_SIZE;
    }

    /**
     * 初始化数据
     */
    private static void initialize() {
        PageUtil.currentPage = Constants.DEFAULT_CURRENT_PAGE;
        PageUtil.pageSize = Constants.DEFAULT_PAGE_SIZE;
        PageUtil.row = Constants.DEFAULT_ROW;
        PageUtil.totalCount = Constants.DEFAULT_TOTAL_COUNT;
        PageUtil.totalPage = Constants.DEFAULT_TOTAL_PAGE;
    }
    

}
