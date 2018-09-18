package dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Station {



    /**
     * parentID : 1ce88a5815804000
     * objectID : 1ce88a5869006000
     * updatedDate : 2018-09-17T03:45:15.796+0000
     * objectTypeID : 58
     * name : 苏州群策科技有限公司配电房
     * state : 江苏省
     * businessType : 29
     * longitude : 120.779693
     * latitude : 31.312547
     * isAttachedSite : true
     * createdDate : 2018-09-17T03:45:04.652+0000
     * city : 苏州市
     */

    private String mdmID;
    private String parentID;
    private String objectID;
    private String updatedDate;
    private String objectTypeID;
    private String name;
    private String state;
    private String businessType;
    private String longitude;
    private String latitude;
    private String isAttachedSite;
    private String createdDate;
    private String city;

    public String toJson(){
        return JSON.toJSONString(this,SerializerFeature.PrettyFormat);
    }

    public String getMdmID() {
        return mdmID;
    }

    public void setMdmID(String mdmID) {
        this.mdmID = mdmID;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
