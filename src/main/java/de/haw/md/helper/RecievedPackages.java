package de.haw.md.helper;

import java.time.LocalDateTime;

import de.haw.md.akka.msg.MsgModel;


/**
 * Managing received messages including sending attempts
 * 
 * @author Sascha Waltz
 *
 */
public class RecievedPackages {
	
	private MsgModel msgModel;
	
	private boolean recieved;
	
	private int sendingAttempts;
	
	private LocalDateTime timestamp;
	
	public RecievedPackages() {
		this(null, false, LocalDateTime.now());
	}
	
	public RecievedPackages(MsgModel msgModel, boolean recieved, LocalDateTime timestamp) {
		this.msgModel = msgModel;
		this.recieved = recieved;
		this.timestamp = timestamp;
		this.sendingAttempts = 0;
	}

	public MsgModel getMsgModel() {
		return msgModel;
	}

	public void setMsgModel(MsgModel msgModel) {
		this.msgModel = msgModel;
	}

	public boolean isRecieved() {
		return recieved;
	}

	public void setRecieved(boolean recieved) {
		this.recieved = recieved;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getSendingAttempts() {
		return sendingAttempts;
	}

	public void setSendingAttempts(int sendingAttempts) {
		this.sendingAttempts = sendingAttempts;
	}
}
