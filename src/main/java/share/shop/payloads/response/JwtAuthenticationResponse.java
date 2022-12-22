package share.shop.payloads.response;

import share.shop.payloads.request.UserProfile;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    private String tokenType = "Bearer";

    private UserProfile user;

    public JwtAuthenticationResponse(String accessToken,String refreshToken, UserProfile user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
