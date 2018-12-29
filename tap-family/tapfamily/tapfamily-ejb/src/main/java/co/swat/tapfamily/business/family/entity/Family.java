package co.swat.tapfamily.business.family.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import co.swat.tapfamily.business.organization.entity.InformalOrganization;

@Entity
@Table(name = "FAMILY")
public class Family extends InformalOrganization {

  private static final long serialVersionUID = 1379610236165590967L;

}
