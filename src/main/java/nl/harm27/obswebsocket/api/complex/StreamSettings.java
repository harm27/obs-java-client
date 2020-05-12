package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

/**
 * Settings for the stream.
 */
public class StreamSettings {
    @SerializedName("server")
    private String server;
    @SerializedName("service")
    private String service;
    @SerializedName("bwtest")
    private boolean bandwidthTest;
    @SerializedName("key")
    private String key;
    @SerializedName("use_auth")
    private boolean useAuth;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    /**
     * The publish URL.
     */
    public String getServer() {
        return server;
    }

    /**
     * The publish URL.
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * The publish key of the stream.
     */
    public String getKey() {
        return key;
    }

    /**
     * The publish key of the stream.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Indicates whether authentication should be used when connecting to the streaming server.
     */
    public boolean isUseAuth() {
        return useAuth;
    }

    /**
     * Indicates whether authentication should be used when connecting to the streaming server.
     */
    public void setUseAuth(boolean useAuth) {
        this.useAuth = useAuth;
    }

    /**
     * If authentication is enabled, the username for the streaming server.
     * Ignored if use_auth is not set to true.
     */
    public String getUsername() {
        return username;
    }

    /**
     * If authentication is enabled, the username for the streaming server.
     * Ignored if use_auth is not set to true.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * If authentication is enabled, the password for the streaming server.
     * Ignored if use_auth is not set to true.
     */
    public String getPassword() {
        return password;
    }

    /**
     * If authentication is enabled, the password for the streaming server.
     * Ignored if use_auth is not set to true.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * The used service
     */
    public String getService() {
        return service;
    }

    /**
     * The used service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Bandwidth Test enabled
     */
    public boolean isBandwidthTest() {
        return bandwidthTest;
    }

    /**
     * Bandwidth Test enabled
     */
    public void setBandwidthTest(boolean bandwidthTest) {
        this.bandwidthTest = bandwidthTest;
    }
}
