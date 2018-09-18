package dao;

import dto.FieldStation;

import java.util.List;

public interface FieldStationMapper {
    void putFieldStationsToMysql(List<FieldStation> fieldStations);
    void  putFieldStationToMysql(FieldStation fieldStation);
    List<String> getFieldStationsMdmIdFromMysql();
}
