package com.xing.utils;

/**
 * Created by xing on 2017/7/11.
 */
public class PageUtil {
    //通过 每页显示数,总记录数,当前页构造Page
    public static Page createPage(int everyPage, int totalCount, int currentPage) {
        everyPage = everyPage == 0 ? 5 : everyPage;
        currentPage = currentPage < 1 ? 1 : currentPage;
        int totalPage = getTotalPage(everyPage, totalCount);
        int startIndex = (currentPage - 1) * everyPage;
        boolean hasPrePage = currentPage <= 1 ? false : true;
        boolean hasNextPage = ((currentPage == totalPage) || (totalPage == 0)) ? false : true;
        return new Page(everyPage,totalCount,totalPage,currentPage,startIndex,hasPrePage,hasNextPage);
    }


    private static int getTotalPage(int everyPage, int totalCount) {
        int totalPage = 0;
        if (totalCount != 0 && totalCount % everyPage == 0) {
            totalPage = totalCount / everyPage;
        } else {
            totalPage = (totalCount / everyPage) + 1;
        }
        return totalPage;
    }

}
