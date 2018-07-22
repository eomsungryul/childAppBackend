package kr.co.dwebss.child.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "center_duty_manage")
public class CenterDutyManage {
    /**
     * 어린이집담당관리ID
     */
    @Id
    @Column(name = "CENTER_DUTY_ID")
    private Integer centerDutyId;

    /**
     * 이벤트코드
     */
    @Column(name = "EVENT_CD")
    private Integer eventCd;

    /**
     * 담당수행날짜
     */
    @Column(name = "DUTY_DATE")
    private Date dutyDate;

    /**
     * 어린이집ID
     */
    @Column(name = "CENTER_ID")
    private Integer centerId;

    /**
     * 어린이집담당코드
     */
    @Column(name = "CENTER_DUTY_CD")
    private Integer centerDutyCd;

    /**
     * getter어린이집담당관리ID
     *
     * @return CENTER_DUTY_ID - 어린이집담당관리ID
     */
    public Integer getCenterDutyId() {
        return centerDutyId;
    }

    /**
     * setter어린이집담당관리ID
     *
     * @param centerDutyId 어린이집담당관리ID
     */
    public void setCenterDutyId(Integer centerDutyId) {
        this.centerDutyId = centerDutyId;
    }

    /**
     * getter이벤트코드
     *
     * @return EVENT_CD - 이벤트코드
     */
    public Integer getEventCd() {
        return eventCd;
    }

    /**
     * setter이벤트코드
     *
     * @param eventCd 이벤트코드
     */
    public void setEventCd(Integer eventCd) {
        this.eventCd = eventCd;
    }

    /**
     * getter담당수행날짜
     *
     * @return DUTY_DATE - 담당수행날짜
     */
    public Date getDutyDate() {
        return dutyDate;
    }

    /**
     * setter담당수행날짜
     *
     * @param dutyDate 담당수행날짜
     */
    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    /**
     * getter어린이집ID
     *
     * @return CENTER_ID - 어린이집ID
     */
    public Integer getCenterId() {
        return centerId;
    }

    /**
     * setter어린이집ID
     *
     * @param centerId 어린이집ID
     */
    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    /**
     * getter어린이집담당코드
     *
     * @return CENTER_DUTY_CD - 어린이집담당코드
     */
    public Integer getCenterDutyCd() {
        return centerDutyCd;
    }

    /**
     * setter어린이집담당코드
     *
     * @param centerDutyCd 어린이집담당코드
     */
    public void setCenterDutyCd(Integer centerDutyCd) {
        this.centerDutyCd = centerDutyCd;
    }
}