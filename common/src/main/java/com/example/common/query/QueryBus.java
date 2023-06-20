package com.example.common.query;

public interface QueryBus {

  <Q extends Query<R>, R> R execute(Q query);

}
