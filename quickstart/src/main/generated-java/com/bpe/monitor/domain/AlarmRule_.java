/*
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package com.bpe.monitor.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AlarmRule.class)
public abstract class AlarmRule_ {

    // Raw attributes
    public static volatile SingularAttribute<AlarmRule, Long> id;
    public static volatile SingularAttribute<AlarmRule, Integer> alarmType;
    public static volatile SingularAttribute<AlarmRule, String> emailToAlert;
    public static volatile SingularAttribute<AlarmRule, Float> high;
    public static volatile SingularAttribute<AlarmRule, Float> low;

    // Many to one
    public static volatile SingularAttribute<AlarmRule, Account> accountFk;
}