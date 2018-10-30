package org.fogbowcloud.cli.order.fednet;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.beust.jcommander.Parameter;

public class FederatedNetwork {

	public static final String PROVIDER_COMMAND_KEY =  "--provider";
	@Parameter(names = { PROVIDER_COMMAND_KEY }, description = "Provider")
	private String provider = null;

	public static final String CIDR_NOTATION_COMMAND_KEY =  "--cidr-notation";
	@Parameter(names = { CIDR_NOTATION_COMMAND_KEY }, description = "CIDR Notation")
	private String cidrNotation = null;
	
	public static final String NAME_COMMAND_KEY =  "--name";
	@Parameter(names = { NAME_COMMAND_KEY }, description = "Name")
	private String name = null;
	
	public static final String ALLOWED_MEMBERS_COMMAND_KEY =  "--allowed-members";
	@Parameter(names = { ALLOWED_MEMBERS_COMMAND_KEY }, description = "Allowed members list", variableArity = true)
	private Set<String> allowedMembers = null;

	public static final String FREE_IPS_COMMAND_KEY =  "--free-ips";
	@Parameter(names = { FREE_IPS_COMMAND_KEY }, description = "Queue of free ips.")
	private Queue<String> freedIps = null;

	public static final String COMPUTE_IPS_COMMAND_KEY =  "--compute-ips";
	@Parameter(names = { COMPUTE_IPS_COMMAND_KEY }, description = "List of compute ips.")
	private List<String> computesIp = null;

	public FederatedNetwork() {
		
	}
	
	public FederatedNetwork(String cidrNotation, String name, Set<String> allowedMembers) {
		this.cidrNotation = cidrNotation;
		this.name = name;
		this.allowedMembers = allowedMembers;
	}	
	
	public String getCidrNotation() {
		return cidrNotation;
	}

	public String getName() {
		return name;
	}

	public Set<String> getAllowedMembers() {
		return allowedMembers;
	}

	public Queue<String> getFreedIps() {
		return freedIps;
	}

	public void setFreedIps(Queue<String> freedIps) {
		this.freedIps = freedIps;
	}

	public List<String> getComputesIp() {
		return computesIp;
	}

	public void setComputesIp(List<String> computesIp) {
		this.computesIp = computesIp;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
}
