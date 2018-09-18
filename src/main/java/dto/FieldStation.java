package dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FieldStation {

    /**
     * timezone : +08:00
     * parentID : 1ce88a5869006000
     * objectID : 1ce88a58b2006000
     * updatedDate : 2018-09-17T03:45:15.796+0000
     * objectTypeID : 102
     * name : 苏州群策科技有限公司配电房
     * province : 江苏省
     * businessType : 29
     * isAttachedSite : true
     * createdDate : 2018-09-17T03:45:04.799+0000
     * city : 苏州市
     */

    private String timezone;
    private String parentID;
    private String objectID;
    private String updatedDate;
    private String objectTypeID;
    private String name;
    private String province;
    private String businessType;
    private String isAttachedSite;
    private String createdDate;
    private String city;
    private String mdmID;

    public String toJson(){
        return JSON.toJSONString(this,SerializerFeature.PrettyFormat);
    }

    public String getMdmID() {
        return mdmID;
    }

    public void setMdmID(String mdmID) {
        this.mdmID = mdmID;
    }



    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getObjectTypeID() {
        return objectTypeID;
    }

    public void setObjectTypeID(String objectTypeID) {
        this.objectTypeID = objectTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getIsAttachedSite() {
        return isAttachedSite;
    }

    public void setIsAttachedSite(String isAttachedSite) {
        this.isAttachedSite = isAttachedSite;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
