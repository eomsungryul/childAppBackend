package kr.co.dwebss.child.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Table(name = "class_daily_event")
public class ClassDailyEvent {
    /**
     * 클래스일일이벤트ID
     */
    @Id
    @Column(name = "CLASS_DAILY_EVENT_ID")
    private Integer classDailyEventId;

    /**
     * 이벤트순서
     */
    @Column(name = "EVENT_ORDER")
    private Integer eventOrder;

    /**
     * 이벤트발생일
     */
    @Column(name = "EVENT_DATE")
    private Date eventDate;

    /**
     * 클래스ID
     */
    @Column(name = "CLASS_ID")
    private Integer classId;

    @Column(name = "DESTINY_NM")
    private String destinyNm;
    

    @Column(name = "EVENT_ALARM_END_T")
    private Integer eventAlarmEndT;
    
    @Column(name = "EVENT_ALARM_START_T")
    private Integer eventAlarmStartT;
    

    @Column(name = "EVENT_CAR_NEED_YN")
    private String eventCarNeedYn;
    
    @Transient
    private List<EventCheck> eventCheckList;
    

    public List<EventCheck> getEventCheckList() {
		return eventCheckList;
	}

	public void setEventCheckList(List<EventCheck> eventCheckList) {
		this.eventCheckList = eventCheckList;
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
     * getter이벤트순서
     *
     * @return EVENT_ORDER - 이벤트순서
     */
    public Integer getEventOrder() {
        return eventOrder;
    }

    /**
     * setter이벤트순서
     *
     * @param eventOrder 이벤트순서
     */
    public void setEventOrder(Integer eventOrder) {
        this.eventOrder = eventOrder;
    }

    /**
     * getter이벤트발생일
     *
     * @return EVENT_DATE - 이벤트발생일
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * setter이벤트발생일
     *
     * @param eventDate 이벤트발생일
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * getter클래스ID
     *
     * @return CLASS_ID - 클래스ID
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * setter클래스ID
     *
     * @param classId 클래스ID
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
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
    
    

}