package com.aypark.sericefeign.model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author john mei
 * @since 2019-06-17
 */

@Data
public class Activity  {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String actName;

    /**
     * 创建人编号
     */
    private Integer creatorId;

    /**
     * 活动时间
     */
    private Date actDate;

    /**
     * 报名时间
     */
    private Date signingDate;

    /**
     * 活动介绍
     */
    private String actDes;

    /**
     * 活动发起人姓名
     */
    private String creatorName;

    /**
     * 所属部门
     */
    private Integer deptId;

    /**
     * 活动地点
     */
    private String actAddr;

    /**
     * 联系方式
     */
    private String telPhone;

    /**
     * 活动内容
     */
    private String actDetail;

    /**
     * 活动图片
     */
    private String actImg;

    private Date createDate;

    private Date updateDate;

    /**
     * 活动状态  1- 未发布  2- 已发布   3-过期
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }


    public String getActDes() {
        return actDes;
    }

    public void setActDes(String actDes) {
        this.actDes = actDes;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getActAddr() {
        return actAddr;
    }

    public void setActAddr(String actAddr) {
        this.actAddr = actAddr;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getActDetail() {
        return actDetail;
    }

    public void setActDetail(String actDetail) {
        this.actDetail = actDetail;
    }

    public String getActImg() {
        return actImg;
    }

    public void setActImg(String actImg) {
        this.actImg = actImg;
    }



    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Activity{" +
        "id=" + id +
        ", actName=" + actName +
        ", creatorId=" + creatorId +
        ", actDate=" + actDate +
        ", signingDate=" + signingDate +
        ", actDes=" + actDes +
        ", creatorName=" + creatorName +
        ", deptId=" + deptId +
        ", actAddr=" + actAddr +
        ", telPhone=" + telPhone +
        ", actDetail=" + actDetail +
        ", actImg=" + actImg +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", state=" + state +
        "}";
    }
}
