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
    @Column(name = "EVENT_START_DT")
    private Date eventStartDt;

    /**
     * 이벤트체크일시
     */
    @Column(name = "EVENT_END_DT")
    private Date eventEndDt;

    @Transient
    private Integer eventCd;

    @Transient
    private Integer classId;

    @Transient
    private String classNm;

    @Transient
    private Integer centerId;
    
    @Transient
    private Integer teacherUserId;

    @Transient
    private Integer eventOrder;

    @Transient
    private Date eventDate;
    
    @Transient
    private String destinyNm;

    @Transient
    private Integer eventAlarmEndT;

    @Transient
    private Integer eventAlarmStartT;

    @Transient
    private String eventCarNeedYn;

    @Transient
    private String parentPhone;
    
    @Transient
    private String teacherNm;

    @Transient
    private Date eventCheckDt;

    @Transient
    private String childNm;

    @Transient
    private Integer diffMinite;

	public Integer getDiffMinite() {
		return diffMinite;
	}


	public void setDiffMinite(Integer diffMinite) {
		this.diffMinite = diffMinite;
	}


	public String getChildNm() {
		return childNm;
	}


	public void setChildNm(String childNm) {
		this.childNm = childNm;
	}


	public Date getEventCheckDt() {
		return eventCheckDt;
	}


	public void setEventCheckDt(Date eventCheckDt) {
		this.eventCheckDt = eventCheckDt;
	}


	public String getTeacherNm() {
		return teacherNm;
	}


	public void setTeacherNm(String teacherNm) {
		this.teacherNm = teacherNm;
	}


	public Integer getEventCd() {
		return eventCd;
	}


	public void setEventCd(Integer eventCd) {
		this.eventCd = eventCd;
	}


	public String getParentPhone() {
		return parentPhone;
	}


	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}


	public Integer getClassId() {
		return classId;
	}


	public void setClassId(Integer classId) {
		this.classId = classId;
	}


	public String getClassNm() {
		return classNm;
	}


	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}


	public Integer getCenterId() {
		return centerId;
	}


	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}


	public Integer getTeacherUserId() {
		return teacherUserId;
	}


	public void setTeacherUserId(Integer teacherUserId) {
		this.teacherUserId = teacherUserId;
	}


	public Integer getEventOrder() {
		return eventOrder;
	}


	public void setEventOrder(Integer eventOrder) {
		this.eventOrder = eventOrder;
	}


	public Date getEventDate() {
		return eventDate;
	}


	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}


	public String getDestinyNm() {
		return destinyNm;
	}


	public void setDestinyNm(String destinyNm) {
		this.destinyNm = destinyNm;
	}


	public Integer getEventAlarmEndT() {
		return eventAlarmEndT;
	}


	public void setEventAlarmEndT(Integer eventAlarmEndT) {
		this.eventAlarmEndT = eventAlarmEndT;
	}


	public Integer getEventAlarmStartT() {
		return eventAlarmStartT;
	}


	public void setEventAlarmStartT(Integer eventAlarmStartT) {
		this.eventAlarmStartT = eventAlarmStartT;
	}


	public String getEventCarNeedYn() {
		return eventCarNeedYn;
	}


	public void setEventCarNeedYn(String eventCarNeedYn) {
		this.eventCarNeedYn = eventCarNeedYn;
	}


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

	public Date getEventStartDt() {
		return eventStartDt;
	}

	public void setEventStartDt(Date eventStartDt) {
		this.eventStartDt = eventStartDt;
	}

	public Date getEventEndDt() {
		return eventEndDt;
	}

	public void setEventEndDt(Date eventEndDt) {
		this.eventEndDt = eventEndDt;
	}
    
    

}