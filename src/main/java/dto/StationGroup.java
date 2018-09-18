package dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class StationGroup {

    /**
     * parentID : 1859febe5ce70000
     * objectID : 1ce88a5815804000
     * updatedDate : 2018-09-17T03:45:15.796+0000
     * objectTypeID : 57
     * name : 苏州群策科技有限公司
     * businessType : 29
     * createdDate : 2018-09-17T03:45:04.490+0000
     */

    private String mdmID;
    private String parentID;
    private String objectID;
    private String updatedDate;
    private String objectTypeID;
    private String name;
    private String businessType;
    private String createdDate;

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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
