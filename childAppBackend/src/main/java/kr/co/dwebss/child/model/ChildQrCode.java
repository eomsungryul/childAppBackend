package kr.co.dwebss.child.model;

import javax.persistence.*;

@Table(name = "child_qr_code")
public class ChildQrCode {
    /**
     * 아이ID
     */
    @Id
    @Column(name = "CHILD_ID")
    private String childId;

    /**
     * QR코드이미지(BINARY)
     */
    @Column(name = "CHILD_QR_CODE_IMG")
    private String childQrCodeImg;

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
     * getterQR코드이미지(BINARY)
     *
     * @return CHILD_QR_CODE_IMG - QR코드이미지(BINARY)
     */
    public String getChildQrCodeImg() {
        return childQrCodeImg;
    }

    /**
     * setterQR코드이미지(BINARY)
     *
     * @param childQrCodeImg QR코드이미지(BINARY)
     */
    public void setChildQrCodeImg(String childQrCodeImg) {
        this.childQrCodeImg = childQrCodeImg;
    }
}