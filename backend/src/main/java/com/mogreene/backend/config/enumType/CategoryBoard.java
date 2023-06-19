package com.mogreene.backend.config.enumType;

/**
 * @name : CategoryBoard
 * @author : Mo-Greene
 * @date : 2023/06/14
 * 카테고리 분류 enum
 */
public enum CategoryBoard {
    FREE("자유"),
    NOTICE("공지"),
    ATTACHMENT("자료실");

    private final String name;

    CategoryBoard(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
