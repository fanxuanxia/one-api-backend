package com.example.oneapibackend.common;

import lombok.Data;

@Data
public class PageResult {

        /**
         * 当前页号
         */
        private long current = 1;

        /**
         * 页面大小
         */
        private long pageSize = 10;

        /**
         * 排序字段
         */
        private String sortField;

        /**
         * 排序顺序（默认j降序）
         */
        private Boolean isAsc = false;

}
