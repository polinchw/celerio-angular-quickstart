/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package com.bpe.monitor.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.*;

import com.bpe.monitor.domain.TempReading;
import com.bpe.monitor.domain.TempReading_;

public interface TempReadingRepository extends JpaRepository<TempReading, Long> {

    default List<TempReading> complete(String query, int maxResults) {
        TempReading probe = new TempReading();
        probe.setTempType(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(TempReading_.tempType.getName(), match -> match.ignoreCase().startsWith());

        Page<TempReading> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}