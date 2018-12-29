package co.merkhet.web.control;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import co.merkhet.web.business.navigation.boundary.SidebarPageConfig;
import co.merkhet.web.business.navigation.entity.SidebarPage;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class SidebarAjaxbasedController extends SelectorComposer<Component> {

	private static final long serialVersionUID = -2988354737216566155L;

	@Wire
	Listbox mnuLst;

	@WireVariable
	SidebarPageConfig sidebarPageConfig;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		for (SidebarPage page : sidebarPageConfig.getPages()) {
			Listitem listitem = constructSidebarListitem(page.getName(),
					page.getLabel(), page.getIconUri(), page.getUri());

			mnuLst.appendChild(listitem);
		}

	}

	private Listitem constructSidebarListitem(final String name, String label,
			String imageSrc, final String locationUri) {

		// construct component and hierarchy
		Listitem listitem = new Listitem();
		final Listcell listcell = new Listcell();
		listcell.setImage(imageSrc);
		listcell.setLabel(label);
		listitem.appendChild(listcell);

		EventListener<Event> onActionListener = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
				// redirect current url to new location
				if (locationUri.startsWith("http")) {
					// open a new browser tab
					Executions.getCurrent().sendRedirect(locationUri);
				} else {
					// use iterable to find the first include only
					Include include = (Include) Selectors
							.iterable(mnuLst.getPage(), "#mainInclude")
							.iterator().next();
					include.setSrc(locationUri);

					// advance bookmark control,
					// bookmark with a prefix
					if (name != null) {
						getPage().getDesktop().setBookmark("p_" + name);
					}
				}
				changeImage(event.getTarget());
			}
		};

		listitem.addEventListener(Events.ON_CLICK, onActionListener);

		return listitem;
	}

	private void changeImage(Component component) {
		if (component instanceof Listitem) {
			Listitem listitem = (Listitem) component;
			Listbox listbox = (Listbox) listitem.getParent();
			List<Component> listComps = listbox.getChildren();
			for (Component comp : listComps) {
				if (comp instanceof Listitem) {
					List<Listcell> cells = ((Listitem) comp).getChildren();
					for (Listcell listcell : cells) {
						String image = listcell.getImage();
						image = image.replace("ffffff", "63c6ae");
						listcell.setImage(image);
					}
				}
			}

			List<Listcell> children = listitem.getChildren();
			for (Listcell listcell : children) {
				String image = listcell.getImage();
				image = image.replace("63c6ae", "ffffff");
				listcell.setImage(image);
			}

		}
	}

}
