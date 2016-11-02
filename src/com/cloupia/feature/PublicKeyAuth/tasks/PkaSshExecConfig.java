/*******************************************************************************
 * Copyright (c) 2016 Russ Whitear, Cisco and others
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cloupia.feature.PublicKeyAuth.tasks;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.cloupia.feature.PublicKeyAuth.constants.PKAConstants;
import com.cloupia.model.cIM.FormFieldDefinition;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.customactions.UserInputField;
import com.cloupia.service.cIM.inframgr.forms.wizard.FormField;

@PersistenceCapable(detachable = "true", table = "PublicKeyAuth_pkasshexectask")
public class PkaSshExecConfig implements TaskConfigIf {

	public static final String displayLabel = "SSH Command Exec with PKA";
	@Persistent
	private long configEntryId;
	@Persistent
	private long actionId;
	
	@FormField(label = "Host", help = "Hostname or IP Address", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TEXT)
	@UserInputField(type = PKAConstants.GENERIC_TEXT_INPUT)
	@Persistent
	private String             host;

	@FormField(label = "Port", help = "", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TEXT)
	@UserInputField(type = PKAConstants.GENERIC_TEXT_INPUT)
	@Persistent
	private String             port;

	@FormField(label = "Login", help = "", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TEXT)
	@UserInputField(type = PKAConstants.GENERIC_TEXT_INPUT)
	@Persistent
	private String             login;

	@FormField(label = "Private Key File Location", help = "Path to private PKA file.", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TEXT)
	@UserInputField(type = PKAConstants.GENERIC_TEXT_INPUT)
	@Persistent
	private String             pkaFile;
	
	@FormField(label = "Command", help = "SSH command to send.", mandatory = true, type = FormFieldDefinition.FIELD_TYPE_TEXT)
	@UserInputField(type = PKAConstants.GENERIC_TEXT_INPUT)
	@Persistent
	private String             command;
	
	
	@Override
	public long getActionId() {
		return actionId;
	}

	@Override
	public long getConfigEntryId() {
		return configEntryId;
	}

	@Override
	public String getDisplayLabel() {
		return displayLabel;
	}

	@Override
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}

	@Override
	public void setConfigEntryId(long configEntryId) {
		this.configEntryId = configEntryId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPkaFile() {
		return pkaFile;
	}

	public void setPkaFile(String pkaFile) {
		this.pkaFile = pkaFile;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
