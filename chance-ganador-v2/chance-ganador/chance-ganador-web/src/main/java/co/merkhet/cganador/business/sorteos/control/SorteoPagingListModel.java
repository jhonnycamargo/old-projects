package co.merkhet.cganador.business.sorteos.control;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import co.merkhet.cganador.business.chance.boundary.Sorteos;
import co.merkhet.cganador.business.chance.entity.Sorteo;
import co.merkhet.web.business.data.control.AbstractPagingListModel;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class SorteoPagingListModel extends AbstractPagingListModel<Sorteo> {

	private static final long serialVersionUID = -4551708620510885315L;

	@WireVariable
	private Sorteos sorteos;

	public SorteoPagingListModel(int startPageNumber, int pageSize) {
		super(startPageNumber, pageSize);
	}

	@Override
	public int getTotalSize() {
		return (int) sorteos.getSorteosCount();
	}

	@Override
	protected List<Sorteo> getPageData(int itemStartNumber, int pageSize) {
		return sorteos.selectSorteosWithLimit(itemStartNumber, pageSize);
	}

}
