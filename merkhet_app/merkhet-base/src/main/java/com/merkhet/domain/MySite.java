package com.merkhet.domain;

import java.util.Date;

public class MySite {
  private String siteId;
  private String descripcion;
  private Date startDate;
  private Date endDate;

  public MySite() {
  }

  public MySite(String siteId, String descripcion, Date startDate,
      Date endDate) {
    this.siteId = siteId;
    this.descripcion = descripcion;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public String getSiteId() {
    return siteId;
  }

  public void setSiteId(String siteId) {
    this.siteId = siteId;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MySite other = (MySite) obj;
    if (siteId == null) {
      if (other.siteId != null)
        return false;
    } else if (!siteId.equals(other.siteId))
      return false;
    return true;
  }

}
