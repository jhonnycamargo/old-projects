package co.merkhet.cganador.business.perfil.control;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import co.merkhet.business.authentication.control.LoginController;

public class PerfilViewViewModel {

	@WireVariable
	LoginController loginController;

	private String newPassword;
	private String confirmNewPassword;
	private String errorMessages;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Command
	@NotifyChange({ "errorMessages", "newPassword", "confirmNewPassword" })
	public void cambiarPassword() {
		if (newPassword.equals(confirmNewPassword)) {
			loginController.updatePassword(newPassword);
			errorMessages = "";
			newPassword = "";
			confirmNewPassword = "";
			Clients.showNotification("Contraseña actualizada correctamente.");
		} else {
			errorMessages = "Las contraseñas no coinciden.";
		}
	}
}
