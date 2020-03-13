package com.hondee.service_plan.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class PageUtils {
    public static Pageable getPageInfo() {
        String strPageIndex = RequestUtils.getParameter("pageIndex");
        String strPageSize = RequestUtils.getParameter("pageSize");
        int pageIndex = 1;
        int pageSize = Integer.MAX_VALUE;
        try {
            pageIndex = StringUtils.isEmpty(strPageIndex) ? 1 : Integer.parseInt(strPageIndex);
            pageSize = StringUtils.isEmpty(strPageSize) ? Integer.MAX_VALUE : Integer.parseInt(strPageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return PageRequest.of(pageIndex - 1, pageSize);
    }

    public static Pageable getPageInfo(Sort sort) {
        String strPageIndex = RequestUtils.getParameter("pageIndex");
        String strPageSize = RequestUtils.getParameter("pageSize");
        int pageIndex = 1;
        int pageSize = Integer.MAX_VALUE;
        try {
            pageIndex = StringUtils.isEmpty(strPageIndex) ? 1 : Integer.parseInt(strPageIndex);
            pageSize = StringUtils.isEmpty(strPageSize) ? Integer.MAX_VALUE : Integer.parseInt(strPageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return PageRequest.of(pageIndex - 1, pageSize, sort);
    }
}
