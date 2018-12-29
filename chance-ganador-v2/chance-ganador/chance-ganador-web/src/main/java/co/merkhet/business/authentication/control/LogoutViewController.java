package co.merkhet.business.authentication.control;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class LogoutViewController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 6132236443241490393L;

	@WireVariable
	private LogoutController logoutController;

	@Listen("onClick=#logoutBtn")
	public void doLogout() {
		logoutController.logout();
		Executions.sendRedirect("/");
	}

	@Listen("onClick=#perfilBtn")
	public void doToPerfil() {
		// use iterable to find the first include only
		Include include = (Include) Selectors
				.iterable(this.getPage(), "#mainInclude").iterator().next();
		include.setSrc("/perfil/perfil.zul");
	}
}
