package co.merkhet.engchat.business.chat.entity;

public class Conversation {

	/**
	 *
	 */
	private String id;

	/** The msg. */
	private String msg;

	/** Msg to maintain context. */
	private String lastMsg;

	/** The is sent. */
	private boolean isSent;

	/** The is success. */
	private boolean isSuccess;

	/** The date. */
	private String date;

	/**
	 * Instantiates a new conversation.
	 *
	 * @param msg
	 *            the msg
	 * @param date
	 *            the date
	 * @param isSent
	 *            the is sent
	 * @param isSuccess
	 *            the is success
	 */
	public Conversation(String msg, String date, boolean isSent, boolean isSuccess) {
		this.msg = msg;
		this.isSent = isSent;
		this.date = date;
		if (isSent)
			this.isSuccess = isSuccess;
		else
			this.isSuccess = false;
	}

	public Conversation() {

	}

	/**
	 * Gets the msg.
	 *
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 *
	 * @param msg
	 *            the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the last msg.
	 *
	 * @return the lastMsg
	 */
	public String getLastMsg() {
		return lastMsg;
	}

	/**
	 * Msg to mantain the context.
	 *
	 * @param lastMsg
	 *            the last sent msg
	 */
	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	/**
	 * Checks if is sent.
	 *
	 * @return true, if is sent
	 */
	public boolean getIsSent() {
		return isSent;
	}

	/**
	 * Sets the sent.
	 *
	 * @param isSent
	 *            the new sent
	 */
	public void setIsSent(boolean isSent) {
		this.isSent = isSent;
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * Sets the success.
	 *
	 * @param isSuccess
	 *            the new success
	 */
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
