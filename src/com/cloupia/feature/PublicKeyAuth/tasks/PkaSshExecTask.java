/*******************************************************************************
 * Copyright (c) 2016 Russ Whitear, Cisco and others
 *
 * Unless explicitly stated otherwise all files in this repository are licensed
 * under the Apache Software License 2.0
 *******************************************************************************/
package com.cloupia.feature.PublicKeyAuth.tasks;

import org.apache.log4j.Logger;
import com.cloupia.service.cIM.inframgr.AbstractTask;
import com.cloupia.service.cIM.inframgr.TaskConfigIf;
import com.cloupia.service.cIM.inframgr.TaskOutputDefinition;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionLogger;
import com.cloupia.service.cIM.inframgr.customactions.CustomActionTriggerContext;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;



public class PkaSshExecTask extends AbstractTask {

	private static Logger logger = Logger.getLogger( PkaSshExecTask.class );
			
	@Override
	public void executeCustomAction(CustomActionTriggerContext context,
			CustomActionLogger actionLogger) throws Exception {
		PkaSshExecConfig config = (PkaSshExecConfig) context.loadConfigObject();

		
		String	host 	= config.getHost();
		int 	port 	= Integer.parseInt(config.getPort());
		String 	login 	= config.getLogin();
		String 	pkaFile = config.getPkaFile();
		String 	command	= config.getCommand();
		
		
		actionLogger.addInfo("Host: " + host );
		actionLogger.addInfo("Port: " + port );
		actionLogger.addInfo("Login: " + login );
		actionLogger.addInfo("PKA File: " + pkaFile );
		actionLogger.addInfo("Command: " + command );
		
		String privateKey = pkaFile;
		String passphrase = null;
		
		
		logger.info("");
		
		JSch jsch = new JSch();
		
		jsch.addIdentity(privateKey, passphrase);
		
		Session session = jsch.getSession(login, host, port);
		
		session.setConfig("StrictHostKeyChecking", "no");
		
		actionLogger.addInfo("Connecting to host: " + host );

		session.connect();
		
		Channel channel=session.openChannel("exec");
		
		((ChannelExec)channel).setCommand(command);
		
		channel.setInputStream(null);
		
		//channel.setOutputStream(System.out);
		
		actionLogger.addInfo("Running command: \"" + command + "\"" );
		
		channel.connect();
		
	    channel.disconnect();
	    
	    session.disconnect();
	    
	}

	@Override
	public TaskConfigIf getTaskConfigImplementation() {
		return new PkaSshExecConfig();
	}

	@Override
	public String getTaskName() {
		return PkaSshExecConfig.displayLabel;
	}

	@Override
	public TaskOutputDefinition[] getTaskOutputDefinitions() {
		return null;
	}
}
