package com.tu.ziik.lms.repository;

import com.tu.ziik.lms.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ahmadjawid on 1/29/17.
 */

@Repository
public interface EventRepository extends JpaRepository <Event, Long> {
}
