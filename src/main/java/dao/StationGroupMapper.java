package dao;

import dto.StationGroup;

import java.util.List;

public interface StationGroupMapper {
    void putStationGroupsToMysql(List<StationGroup> stationGroups);
    void putStationGroupToMysql(StationGroup stationGroup);
    List<String> getStationGroupsMdmIdFromMysql();
}
