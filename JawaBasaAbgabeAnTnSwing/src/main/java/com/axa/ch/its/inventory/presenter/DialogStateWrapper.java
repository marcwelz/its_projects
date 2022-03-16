package com.axa.ch.its.inventory.presenter;

public class DialogStateWrapper {
	public DialogState state;

	public void setState(DialogState lend) {
		this.state = lend;
	}

	public DialogState getState() {
		return state;
	}
}