package com.example.common.utils;

import com.google.common.collect.Sets;
import java.util.Set;

public class CastUtils {

  public static <T> Set<T> convertIterableToSet(Iterable<T> data) {
    return Sets.newHashSet(data);
  }

}
