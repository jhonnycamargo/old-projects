package co.merkhet.web.business.navigation.boundary;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import co.merkhet.web.business.navigation.entity.SidebarPage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class SidebarPageConfig implements Serializable {

  private static final long serialVersionUID = 4765631268139204467L;

  Map<String, SidebarPage> pageMap = new LinkedHashMap<String, SidebarPage>();

  public SidebarPageConfig() {
    pageMap.put("fn1", new SidebarPage("homeMn", "Home",
        "/resources/img/home_63c6ae_16.png", "/home/dashboard.zul"));
    pageMap.put("fn2", new SidebarPage("sorteosMn",
        "Resultados Loter\u00edas",
        "/resources/img/table_63c6ae_16.png", "/sorteo/sorteos.zul"));
    pageMap.put("fn3", new SidebarPage("estadisticasMn",
        "Estad\u00edsticas",
        "/resources/img/bar-chart-o_63c6ae_16.png", "/stats/stats.zul"));
    pageMap.put("fn4", new SidebarPage("jugarMn", "A Jugar!",
        "/resources/img/gamepad_63c6ae_16.png", "/play/lets_play.zul"));
    pageMap.put("fn5", new SidebarPage("flaskMn", "Laboratorios",
            "/resources/img/flask_63c6ae_16.png", "/flask/flask.zul"));
  }

  public List<SidebarPage> getPages() {
    return new ArrayList<SidebarPage>(pageMap.values());
  }

  public SidebarPage getPage(String name) {
    return pageMap.get(name);
  }
}
