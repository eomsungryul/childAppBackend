package kr.co.dwebss.child.model;

import javax.persistence.*;

@Table(name = "common_code_category")
public class CommonCodeCategory {
    /**
     * 코드유형ID
     */
    @Id
    @Column(name = "CODE_CATEGORY_ID")
    private Integer codeCategoryId;

    /**
     * 코드유형명
     */
    @Column(name = "CODE_CATEGORY_NM")
    private String codeCategoryNm;

    /**
     * 코드유형영문명
     */
    @Column(name = "CODE_CATEGORY_ENG_NM")
    private String codeCategoryEngNm;

    /**
     * getter코드유형ID
     *
     * @return CODE_CATEGORY_ID - 코드유형ID
     */
    public Integer getCodeCategoryId() {
        return codeCategoryId;
    }

    /**
     * setter코드유형ID
     *
     * @param codeCategoryId 코드유형ID
     */
    public void setCodeCategoryId(Integer codeCategoryId) {
        this.codeCategoryId = codeCategoryId;
    }

    /**
     * getter코드유형명
     *
     * @return CODE_CATEGORY_NM - 코드유형명
     */
    public String getCodeCategoryNm() {
        return codeCategoryNm;
    }

    /**
     * setter코드유형명
     *
     * @param codeCategoryNm 코드유형명
     */
    public void setCodeCategoryNm(String codeCategoryNm) {
        this.codeCategoryNm = codeCategoryNm;
    }

    /**
     * getter코드유형영문명
     *
     * @return CODE_CATEGORY_ENG_NM - 코드유형영문명
     */
    public String getCodeCategoryEngNm() {
        return codeCategoryEngNm;
    }

    /**
     * setter코드유형영문명
     *
     * @param codeCategoryEngNm 코드유형영문명
     */
    public void setCodeCategoryEngNm(String codeCategoryEngNm) {
        this.codeCategoryEngNm = codeCategoryEngNm;
    }
}