package com.jjin.dbscan.station;

import com.jjin.jooqtest.tables.TbStation;
import com.jjin.jooqtest.tables.records.TbStationRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StationJooqRepository {
    @Autowired
    private DSLContext dslContext;

    public List<Station> getList(Integer size) {
        return dslContext.select(TbStation.TB_STATION)
                .from(TbStation.TB_STATION)
                .orderBy(TbStation.TB_STATION.ID.desc())
                .limit(size)
                .fetchInto(Station.class);
    }

    public void bulkInsert(List<Station> list) {
        List<TbStationRecord> collect = list.stream()
                .map(x -> dslContext.newRecord(TbStation.TB_STATION, x))
                .collect(Collectors.toList());
        dslContext.batchInsert(collect).execute();
    }
}
