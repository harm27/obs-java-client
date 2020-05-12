package nl.harm27.obswebsocket.api.complex;

import com.google.gson.annotations.SerializedName;

public class StreamSettings {
    @SerializedName("server")
    private String server;
    @SerializedName("key")
    private String key;
    @SerializedName("use_auth")
    private boolean useAuth;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isUseAuth() {
        return useAuth;
    }

    public void setUseAuth(boolean useAuth) {
        this.useAuth = useAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
