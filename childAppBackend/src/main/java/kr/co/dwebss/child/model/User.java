package kr.co.dwebss.child.model;

import java.util.List;

import javax.persistence.*;

public class User {
    /**
     * 사용자ID
     */
    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 사용자권한코드
     */
    @Column(name = "USER_ROLE_CD")
    private Integer userRoleCd;

    /**
     * 사용자이름
     */
    @Column(name = "USER_NM")
    private String userNm;

    /**
     * 사용자연락처
     */
    @Column(name = "USER_PHONE")
    private String userPhone;

    /**
     * 사용자로그인(email)ID
     */
    @Column(name = "USER_LOGIN_ID")
    private String userLoginId;

    /**
     * 사용자패스워드(웹용)
     */
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    /**
     * 어린이집ID
     */
    @Column(name = "CENTER_ID")
    private Integer centerId;

    @Column(name = "CONFIRM_YN")
    private String confirmYn;

    @Column(name = "USER_EMAIL_GMAIL")
    private String userEmailGmail;
    
    @Column(name = "USER_EMAIL_FACEBOOK")
    private String userEmailFacebook;
    
    @Column(name = "PUSH_TOKEN")
    private String pushToken;

    @Transient
    private String userEmail;

    /**
     * 어린이집이름
     */
    @Transient
    private String centerNm;

    /**
     * 어린이집전화번호
     */
    @Transient
    private String centerPhone;

    @Transient
    private List<Child> myChildList;

    @Transient
    private List<ClassVO> myClassList;
    
    @Transient
    private List<ClassVO> centerClassList;
    
    
    public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Child> getMyChildList() {
		return myChildList;
	}

	public void setMyChildList(List<Child> myChildList) {
		this.myChildList = myChildList;
	}

	public List<ClassVO> getMyClassList() {
		return myClassList;
	}

	public void setMyClassList(List<ClassVO> myClassList) {
		this.myClassList = myClassList;
	}

	public List<ClassVO> getCenterClassList() {
		return centerClassList;
	}

	public void setCenterClassList(List<ClassVO> centerClassList) {
		this.centerClassList = centerClassList;
	}

	/**
     * 어린이집 주소
     */
    @Transient
    private String centerAddr;
    

    public String getCenterNm() {
		return centerNm;
	}

	public void setCenterNm(String centerNm) {
		this.centerNm = centerNm;
	}

	public String getCenterPhone() {
		return centerPhone;
	}

	public void setCenterPhone(String centerPhone) {
		this.centerPhone = centerPhone;
	}

	public String getCenterAddr() {
		return centerAddr;
	}

	public void setCenterAddr(String centerAddr) {
		this.centerAddr = centerAddr;
	}


	/**
     * getter사용자ID
     *
     * @return USER_ID - 사용자ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * setter사용자ID
     *
     * @param userId 사용자ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * getter사용자권한코드
     *
     * @return USER_ROLE_CD - 사용자권한코드
     */
    public Integer getUserRoleCd() {
        return userRoleCd;
    }

    /**
     * setter사용자권한코드
     *
     * @param userRoleCd 사용자권한코드
     */
    public void setUserRoleCd(Integer userRoleCd) {
        this.userRoleCd = userRoleCd;
    }

    /**
     * getter사용자이름
     *
     * @return USER_NM - 사용자이름
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * setter사용자이름
     *
     * @param userNm 사용자이름
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * getter사용자연락처
     *
     * @return USER_PHONE - 사용자연락처
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * setter사용자연락처
     *
     * @param userPhone 사용자연락처
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * getter사용자로그인(email)ID
     *
     * @return USER_LOGIN_ID - 사용자로그인(email)ID
     */
    public String getUserLoginId() {
        return userLoginId;
    }

    /**
     * setter사용자로그인(email)ID
     *
     * @param userLoginId 사용자로그인(email)ID
     */
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    /**
     * getter사용자패스워드(웹용)
     *
     * @return USER_PASSWORD - 사용자패스워드(웹용)
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * setter사용자패스워드(웹용)
     *
     * @param userPassword 사용자패스워드(웹용)
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
     * @return CONFIRM_YN
     */
    public String getConfirmYn() {
        return confirmYn;
    }

    /**
     * @param confirmYn
     */
    public void setConfirmYn(String confirmYn) {
        this.confirmYn = confirmYn;
    }

	public String getUserEmailGmail() {
		return userEmailGmail;
	}

	public void setUserEmailGmail(String userEmailGmail) {
		this.userEmailGmail = userEmailGmail;
	}

	public String getUserEmailFacebook() {
		return userEmailFacebook;
	}

	public void setUserEmailFacebook(String userEmailFacebook) {
		this.userEmailFacebook = userEmailFacebook;
	}
    
    

}