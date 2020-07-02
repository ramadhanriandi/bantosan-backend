package com.blibli.demo.configuration.data;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public final class Credential {

	public static final String CREDENTIAL_STORE_ID = "STORE_ID";
	public static final String CREDENTIAL_CHANNEL_ID = "CHANNEL_ID";
	public static final String CREDENTIAL_CLIENT_ID = "CLIENT_ID";
	public static final String CREDENTIAL_REQUEST_ID = "REQUEST_ID";
	public static final String CREDENTIAL_USERNAME = "USERNAME";
	public static final String CREDENTIAL_MODE = "MODE";
	public static final String CREDENTIAL_ROLES = "ROLES";
	public static final String CREDENTIAL_ACCESSIBILITIES = "ACCESSIBILITIES";

	private Credential() {
	}

	public static String getStoreId() {
		return MDC.get(Credential.CREDENTIAL_STORE_ID);
	}

	public static void setStoreId(String storeId) {
		MDC.put(Credential.CREDENTIAL_STORE_ID, storeId);
	}

	public static String getChannelId() {
		return MDC.get(Credential.CREDENTIAL_CHANNEL_ID);
	}

	public static void setChannelId(String channelId) {
		MDC.put(Credential.CREDENTIAL_CHANNEL_ID, channelId);
	}

	public static String getClientId() {
		return MDC.get(Credential.CREDENTIAL_CLIENT_ID);
	}

	public static void setClientId(String clientId) {
		MDC.put(Credential.CREDENTIAL_CLIENT_ID, clientId);
	}

	public static String getRequestId() {
		return MDC.get(Credential.CREDENTIAL_REQUEST_ID);
	}

	public static void setRequestId(String requestId) {
		MDC.put(Credential.CREDENTIAL_REQUEST_ID, requestId);
	}

	public static String getUsername() {
		return MDC.get(Credential.CREDENTIAL_USERNAME);
	}

	public static void setUsername(String username) {
		MDC.put(Credential.CREDENTIAL_USERNAME, username);
	}

	public static String getMode() {
		return MDC.get(Credential.CREDENTIAL_MODE);
	}

	public static void setMode(String mode) {
		MDC.put(Credential.CREDENTIAL_MODE, mode);
	}

	public static String[] getRoles() {
		return StringUtils.commaDelimitedListToStringArray(MDC.get(Credential.CREDENTIAL_ROLES));
	}

	public static void setRoles(String[] roles) {
		MDC.put(Credential.CREDENTIAL_ROLES, StringUtils.arrayToCommaDelimitedString(roles));
	}

	public static String[] getAccessibilities() {
		return StringUtils.commaDelimitedListToStringArray(MDC.get(Credential.CREDENTIAL_ACCESSIBILITIES));
	}

	public static void setAccessibilities(String[] accessibilities) {
		MDC.put(Credential.CREDENTIAL_ACCESSIBILITIES, StringUtils.arrayToCommaDelimitedString(accessibilities));
	}
}