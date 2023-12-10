package com.jjin.jpaentity.subway.repo;

import com.jjin.jpaentity.subway.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepo extends JpaRepository<Station, Integer> {
}
