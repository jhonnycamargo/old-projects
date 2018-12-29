package com.merkhet.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.enterprise.inject.Produces;
public class Resources {

  @PersistenceContext
  @Produces
  private EntityManager entityManager;
}
