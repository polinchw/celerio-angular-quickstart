/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.bpe.monitor.domain;

import java.time.Instant;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TempReading.class)
public abstract class TempReading_ {

    // Raw attributes
    public static volatile SingularAttribute<TempReading, Long> id;
    public static volatile SingularAttribute<TempReading, Instant> dateRecorded;
    public static volatile SingularAttribute<TempReading, Float> reading;
    public static volatile SingularAttribute<TempReading, String> tempType;

    // Many to one
    public static volatile SingularAttribute<TempReading, Device> deviceFk;
}