package kr.co.dwebss.child.model;

import javax.persistence.*;

@Table(name = "common_code")
public class CommonCode {
    /**
     * 코드
     */
    @Id
    @Column(name = "CODE")
    private Integer code;

    /**
     * 코드유형ID
     */
    @Column(name = "CODE_CATEGORY_ID")
    private Integer codeCategoryId;

    /**
     * 코드명
     */
    @Column(name = "CODE_NM")
    private String codeNm;

    /**
     * 코드영문명
     */
    @Column(name = "CODE_ENG_NM")
    private String codeEngNm;

    /**
     * 코드사용여부
     */
    @Column(name = "CODE_USE_YN")
    private String codeUseYn;

    /**
     * getter코드
     *
     * @return CODE - 코드
     */
    public Integer getCode() {
        return code;
    }

    /**
     * setter코드
     *
     * @param code 코드
     */
    public void setCode(Integer code) {
        this.code = code;
    }

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
     * getter코드명
     *
     * @return CODE_NM - 코드명
     */
    public String getCodeNm() {
        return codeNm;
    }

    /**
     * setter코드명
     *
     * @param codeNm 코드명
     */
    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    /**
     * getter코드영문명
     *
     * @return CODE_ENG_NM - 코드영문명
     */
    public String getCodeEngNm() {
        return codeEngNm;
    }

    /**
     * setter코드영문명
     *
     * @param codeEngNm 코드영문명
     */
    public void setCodeEngNm(String codeEngNm) {
        this.codeEngNm = codeEngNm;
    }

    /**
     * getter코드사용여부
     *
     * @return CODE_USE_YN - 코드사용여부
     */
    public String getCodeUseYn() {
        return codeUseYn;
    }

    /**
     * setter코드사용여부
     *
     * @param codeUseYn 코드사용여부
     */
    public void setCodeUseYn(String codeUseYn) {
        this.codeUseYn = codeUseYn;
    }
}