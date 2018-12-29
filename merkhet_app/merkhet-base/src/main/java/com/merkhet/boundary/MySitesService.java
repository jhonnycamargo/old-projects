package com.merkhet.boundary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.merkhet.domain.MySite;

@Stateless
public class MySitesService {

  private List<MySite> mySites = new ArrayList<MySite>();

  @PostConstruct
  public void init() {
    for (int i = 0; i < 1000; i++) {
      mySites.add(new MySite("" + i, "Site " + i, Calendar.getInstance()
          .getTime(), Calendar.getInstance().getTime()));
    }
  }

  public int size() {
    return mySites.size();
  }

  public List<MySite> list(int startIndex, int endIndex) {
    return mySites.subList(startIndex, endIndex);
  }

  public MySite findById(String id) {
    return mySites.get(Integer.parseInt(id));
  }

}
