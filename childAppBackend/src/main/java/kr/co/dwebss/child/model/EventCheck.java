package kr.co.dwebss.child.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "event_check")
public class EventCheck {
    /**
     * 이벤트체크ID
     */
    @Id
    @Column(name = "EVENT_CHECK_ID")
    private Integer eventCheckId;

    /**
     * 이벤트체크일시
     */
    @Column(name = "EVENT_CHECK_DT")
    private Date eventCheckDt;

    /**
     * 클래스일일이벤트ID
     */
    @Column(name = "CLASS_DAILY_EVENT_ID")
    private Integer classDailyEventId;

    /**
     * 아이ID
     */
    @Column(name = "CHILD_ID")
    private String childId;

    /**
     * 사용자(체크자)ID
     */
    @Column(name = "CHECKER_USER_ID")
    private Integer checkerUserId;

    /**
     * 이벤트체크코드
     */
    @Column(name = "EVENT_CHECK_CD")
    private Integer eventCheckCd;

    /**
     * getter이벤트체크ID
     *
     * @return EVENT_CHECK_ID - 이벤트체크ID
     */
    public Integer getEventCheckId() {
        return eventCheckId;
    }

    /**
     * setter이벤트체크ID
     *
     * @param eventCheckId 이벤트체크ID
     */
    public void setEventCheckId(Integer eventCheckId) {
        this.eventCheckId = eventCheckId;
    }

    /**
     * getter이벤트체크일시
     *
     * @return EVENT_CHECK_DT - 이벤트체크일시
     */
    public Date getEventCheckDt() {
        return eventCheckDt;
    }

    /**
     * setter이벤트체크일시
     *
     * @param eventCheckDt 이벤트체크일시
     */
    public void setEventCheckDt(Date eventCheckDt) {
        this.eventCheckDt = eventCheckDt;
    }

    /**
     * getter클래스일일이벤트ID
     *
     * @return CLASS_DAILY_EVENT_ID - 클래스일일이벤트ID
     */
    public Integer getClassDailyEventId() {
        return classDailyEventId;
    }

    /**
     * setter클래스일일이벤트ID
     *
     * @param classDailyEventId 클래스일일이벤트ID
     */
    public void setClassDailyEventId(Integer classDailyEventId) {
        this.classDailyEventId = classDailyEventId;
    }

    /**
     * getter아이ID
     *
     * @return CHILD_ID - 아이ID
     */
    public String getChildId() {
        return childId;
    }

    /**
     * setter아이ID
     *
     * @param childId 아이ID
     */
    public void setChildId(String childId) {
        this.childId = childId;
    }

    /**
     * getter사용자(체크자)ID
     *
     * @return CHECKER_USER_ID - 사용자(체크자)ID
     */
    public Integer getCheckerUserId() {
        return checkerUserId;
    }

    /**
     * setter사용자(체크자)ID
     *
     * @param checkerUserId 사용자(체크자)ID
     */
    public void setCheckerUserId(Integer checkerUserId) {
        this.checkerUserId = checkerUserId;
    }

    /**
     * getter이벤트체크코드
     *
     * @return EVENT_CHECK_CD - 이벤트체크코드
     */
    public Integer getEventCheckCd() {
        return eventCheckCd;
    }

    /**
     * setter이벤트체크코드
     *
     * @param eventCheckCd 이벤트체크코드
     */
    public void setEventCheckCd(Integer eventCheckCd) {
        this.eventCheckCd = eventCheckCd;
    }
}