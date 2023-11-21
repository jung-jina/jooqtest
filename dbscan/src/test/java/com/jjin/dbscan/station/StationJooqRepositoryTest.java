package com.jjin.dbscan.station;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@JooqTest
@Import(StationJooqRepositoryTest.TestConfig.class)
class StationJooqRepositoryTest {
    @TestConfiguration
    public static class TestConfig {
        @Bean
        public StationJooqRepository stationJooqRepository() {
            return new StationJooqRepository();
        }
    }

    @Autowired
    private StationJooqRepository stationJooqRepository;

    @Test
    void getList() {
        List<Station> list = stationJooqRepository.getList(10);
        list.forEach(System.out::println);
        Assertions.assertEquals(4, list.size());
    }

    @Test
    void bulkInsertTest() {
        List<Station> stations = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Station station = new Station();
            station.setCode("code" + i);
            station.setName("name" + i);
            stations.add(station);
        }
        stationJooqRepository.bulkInsert(stations);
        List<Station> list = stationJooqRepository.getList(10);
        Assertions.assertEquals(10, list.size());
    }
}