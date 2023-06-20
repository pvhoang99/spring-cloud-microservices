package com.example.common.vm.filters;

import static org.jooq.impl.DSL.field;

import org.jooq.Condition;

public enum Op {

  eq {
    @Override
    public Condition build(String field, Object value) {
      return field(field).equal(value);
    }
  };

  public abstract Condition build(String field, Object value);

}
