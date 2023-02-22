//package com.example.common.hytrix;
//
//import com.example.common.utils.UserContextHolder;
//import com.example.common.utils.UserContextHolder.UserContext;
//import java.util.concurrent.Callable;
//
//public class DelegatingUserContextCallable<V> implements Callable<V> {
//
//  private final Callable<V> delegate;
//  private UserContext originalUserContext;
//
//  public DelegatingUserContextCallable(Callable<V> delegate,
//      UserContext originalUserContext) {
//    this.delegate = delegate;
//    this.originalUserContext = originalUserContext;
//  }
//
//  @Override
//  public V call() throws Exception {
//    UserContextHolder.setContext(originalUserContext);
//    try {
//      return delegate.call();
//    } finally {
//      this.originalUserContext = null;
//    }
//  }
//}
