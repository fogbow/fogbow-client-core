package cloud.fogbow.cli.ras.securityrule;

import cloud.fogbow.cli.FogbowCliHttpUtil;
import cloud.fogbow.cli.HttpCliConstants;
import cloud.fogbow.cli.constants.CliCommonParameters;
import cloud.fogbow.cli.constants.Documentation;
import cloud.fogbow.cli.constants.Messages;
import cloud.fogbow.cli.exceptions.FogbowCLIException;
import cloud.fogbow.cli.utils.CommandUtil;
import cloud.fogbow.common.constants.HttpMethod;
import cloud.fogbow.common.exceptions.FogbowException;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.ParametersDelegate;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import cloud.fogbow.cli.ras.order.network.NetworkCommand;
import cloud.fogbow.cli.ras.order.publicip.PublicIpCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SecurityRuleCommand {
    public static final String NAME = "security-rule";
    public static final String ENDPOINT = "security-rules";
    public static final String CREATE_COMMAND_KEY = "--create";
    public static final String DELETE_COMMAND_KEY = "--delete";
    public static final String NETWORK_ID_COMMAND_KEY = "--network-id";
    public static final String PUBLIC_IP_COMMAND_KEY = "--public-ip-id";

    @Parameter(names = CREATE_COMMAND_KEY)
    private Boolean isCreateCommand = false;

    @Parameter(names = DELETE_COMMAND_KEY)
    private Boolean isDeleteCommand = false;

    @Parameter(names = NETWORK_ID_COMMAND_KEY)
    private String networkId;

    @Parameter(names = PUBLIC_IP_COMMAND_KEY)
    private String publicIpId;

    @Parameter(names = CliCommonParameters.URL_COMMAND_KEY, description = Documentation.CommonParameters.URL)
    private String url;

    @Parameter(names = CliCommonParameters.SYSTEM_USER_TOKEN_COMMAND_KEY, description = Documentation.CommonParameters.SYSTEM_USER_TOKEN)
    private String systemUserToken = null;

    @Parameter(names = CliCommonParameters.SYSTEM_USER_TOKEN_PATH_COMMAND_KEY, description = Documentation.CommonParameters.SYSTEM_USER_TOKEN_PATH)
    private String systemUserTokenPath = null;

    @ParametersDelegate
    private SecurityRule securityRule = new SecurityRule();

    @ParametersDelegate
    private FogbowCliHttpUtil authenticatedRequest = new FogbowCliHttpUtil();

    public String run() throws IOException, FogbowException {
        if ((isCreateCommand() ^ isDeleteCommand())) {
            if (isCreateCommand()) {
                return doCreate(securityRule);
            } else {
                return doDelete(securityRule);
            }
        } else {
            throw new ParameterException(String.format(Messages.Exception.INCONSISTENT_PARAMS, CREATE_COMMAND_KEY, DELETE_COMMAND_KEY));
        }
    }

    private String doCreate(SecurityRule securityRule) throws IOException, FogbowException {
        if (networkId != null ^ publicIpId != null) {
            String completeUrl;
            if (networkId != null) {
                completeUrl = StringUtils.join(Arrays.asList(url, NetworkCommand.ENDPOINT, networkId, ENDPOINT), "/");
            } else {
                completeUrl = StringUtils.join(Arrays.asList(url, PublicIpCommand.ENDPOINT, publicIpId, ENDPOINT), "/");
            }

            HashMap body = securityRule.getHTTPHashMap();
            String httpResponse = authenticatedRequest.doGenericAuthenticatedRequest(HttpMethod.POST, completeUrl, body);
            return httpResponse;
        } else {
            String message = String.format(Messages.Exception.INCONSISTENT_PARAMS, NETWORK_ID_COMMAND_KEY, PUBLIC_IP_COMMAND_KEY);
            throw new ParameterException(message);
        }
    }

    private String doDelete(SecurityRule securityRule) throws ClientProtocolException, FogbowException {
        if (securityRule == null || securityRule.getId() == null) {
            throw new ParameterException(Messages.Exception.NO_RULE_ID_INFORMED);
        }

        List<String> urlParams;
        if (networkId != null ^ publicIpId != null) {
            if (networkId != null) {
                urlParams = Arrays.asList(url, NetworkCommand.ENDPOINT, networkId,
                        ENDPOINT, securityRule.getId());
            } else {
                urlParams = Arrays.asList(url, PublicIpCommand.ENDPOINT, publicIpId,
                        ENDPOINT, securityRule.getId());
            }
        } else {
            String message = String.format(Messages.Exception.INCONSISTENT_PARAMS, NETWORK_ID_COMMAND_KEY, PUBLIC_IP_COMMAND_KEY);
            throw new ParameterException(message);
        }

        String completeUrl = StringUtils.join(urlParams, "/");
        String httpResponse = authenticatedRequest.doGenericAuthenticatedRequest(HttpMethod.DELETE, completeUrl);
        return httpResponse;
    }

    public Boolean isCreateCommand() {
        return this.isCreateCommand;
    }

    public Boolean isDeleteCommand() {
        return this.isDeleteCommand;
    }
}
