package kr.co.dwebss.child.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "class_behalf_teacher")
public class ClassBehalfTeacher {
    /**
     * 학급선생님대리관리ID
     */
    @Id
    @Column(name = "CLASS_BEHALF_TEACHER_ID")
    private Integer classBehalfTeacherId;

    /**
     * 클래스ID
     */
    @Column(name = "CLASS_ID")
    private Integer classId;

    /**
     * 대리시작일자
     */
    @Column(name = "BEHALF_START_D")
    private Date behalfStartD;

    /**
     * 대리종료일자
     */
    @Column(name = "BEHALF_END_D")
    private Date behalfEndD;

    /**
     * 사용자ID
     */
    @Column(name = "BEHALF_TEACHER_USER_ID")
    private Integer behalfTeacherUserId;

    /**
     * getter학급선생님대리관리ID
     *
     * @return CLASS_BEHALF_TEACHER_ID - 학급선생님대리관리ID
     */
    public Integer getClassBehalfTeacherId() {
        return classBehalfTeacherId;
    }

    /**
     * setter학급선생님대리관리ID
     *
     * @param classBehalfTeacherId 학급선생님대리관리ID
     */
    public void setClassBehalfTeacherId(Integer classBehalfTeacherId) {
        this.classBehalfTeacherId = classBehalfTeacherId;
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

    /**
     * getter대리시작일자
     *
     * @return BEHALF_START_D - 대리시작일자
     */
    public Date getBehalfStartD() {
        return behalfStartD;
    }

    /**
     * setter대리시작일자
     *
     * @param behalfStartD 대리시작일자
     */
    public void setBehalfStartD(Date behalfStartD) {
        this.behalfStartD = behalfStartD;
    }

    /**
     * getter대리종료일자
     *
     * @return BEHALF_END_D - 대리종료일자
     */
    public Date getBehalfEndD() {
        return behalfEndD;
    }

    /**
     * setter대리종료일자
     *
     * @param behalfEndD 대리종료일자
     */
    public void setBehalfEndD(Date behalfEndD) {
        this.behalfEndD = behalfEndD;
    }

    /**
     * getter사용자ID
     *
     * @return BEHALF_TEACHER_USER_ID - 사용자ID
     */
    public Integer getBehalfTeacherUserId() {
        return behalfTeacherUserId;
    }

    /**
     * setter사용자ID
     *
     * @param behalfTeacherUserId 사용자ID
     */
    public void setBehalfTeacherUserId(Integer behalfTeacherUserId) {
        this.behalfTeacherUserId = behalfTeacherUserId;
    }
}