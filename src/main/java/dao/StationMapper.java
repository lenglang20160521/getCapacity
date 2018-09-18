package dao;

import dto.Station;

import java.util.List;

public interface StationMapper {
    void putStationsToMysql(List<Station> stations);
    void  putStationToMysql(Station station);
    List<String> getStationsMdmIdFromMysql();
}
