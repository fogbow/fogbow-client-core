package cloud.fogbow.cli.constants;

public class Documentation {
    public class Allocation {
        public static final String GET = "Gets the user's current resource allocation on the specified member and cloud.";
    }

    public class Clouds {
    }

    public class CommonParameters {
        public static final String CLOUD_NAME = "Name of the cloud to which the request is targeted.";
        public static final String FEDERATION_TOKEN = "User's federation token.";
        public static final String FEDERATION_TOKEN_PATH = "Path to file containing the user's federation token.";
        public static final String MEMBER_ID = "Id of the member to which the request is to be sent.";
        public static final String URL = "URL of the Fogbow service.";
    }

    public class FederatedNetwork {
        public static final String ID = "Federated network identification.";
    }

    public class GenericRequest {
        public static final String BODY = "Body field.";
        public static final String HEADERS = "Headers field.";
        public static final String METHOD = "Method field.";
        public static final String URL = "URL field.";
    }

    public class Image {
        public static final String GET = "Gets details for a specific image.";
        public static final String GET_ALL = "Gets the list of all images available.";
        public static final String ID = "Image identifier.";
    }

    public class Member {
        public static final String GET_ALL = "Gets the list of members that belong to the federation.";
    }

    public class Order {
        public static final String CREATE = "Creates the specified resource.";
        public static final String DELETE = "Deletes the resource.";
        public static final String GET = "Gets the details of the resource.";
        public static final String GET_ALL = "Gets the list of all resources created by the user.";
        public static final String ID = "Resource identifier.";
    }

    public class Quota {
        public static final String GET = "Gets the user's quota on the specified member and cloud.";
    }

    public class SecurityGroupRule {

    }

    public class Token {
        public static final String COMMAND_DESCRIPTION = "Creates tokens.";
        public static final String CREATE_COMMAND = "Creates a new token.";
        public static final String DYNAMIC_PARAMS = "Dynamic parameters for token command.";
    }
}
