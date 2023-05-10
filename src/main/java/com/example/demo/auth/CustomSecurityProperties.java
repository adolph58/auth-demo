package com.example.demo.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(
        prefix = "auth.demo.security.defs"
)
public class CustomSecurityProperties {
    private static Long adminId = -1L;
    private static String adminRoleName = "admin";
    private Boolean ipWhileListCheck = false;
    private String mode = "resource";

    public static Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        adminId = adminId;
    }

    public static String getAdminRoleName() {
        return adminRoleName;
    }

    public void setAdminRoleName(String adminRoleName) {
        adminRoleName = adminRoleName;
    }

    public CustomSecurityProperties() {
    }

    public Boolean getIpWhileListCheck() {
        return this.ipWhileListCheck;
    }

    public String getMode() {
        return this.mode;
    }

    public void setIpWhileListCheck(final Boolean ipWhileListCheck) {
        this.ipWhileListCheck = ipWhileListCheck;
    }

    public void setMode(final String mode) {
        this.mode = mode;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CustomSecurityProperties)) {
            return false;
        } else {
            CustomSecurityProperties other = (CustomSecurityProperties)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$ipWhileListCheck = this.getIpWhileListCheck();
                Object other$ipWhileListCheck = other.getIpWhileListCheck();
                if (this$ipWhileListCheck == null) {
                    if (other$ipWhileListCheck != null) {
                        return false;
                    }
                } else if (!this$ipWhileListCheck.equals(other$ipWhileListCheck)) {
                    return false;
                }

                Object this$mode = this.getMode();
                Object other$mode = other.getMode();
                if (this$mode == null) {
                    if (other$mode != null) {
                        return false;
                    }
                } else if (!this$mode.equals(other$mode)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CustomSecurityProperties;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $ipWhileListCheck = this.getIpWhileListCheck();
        result = result * 59 + ($ipWhileListCheck == null ? 43 : $ipWhileListCheck.hashCode());
        Object $mode = this.getMode();
        result = result * 59 + ($mode == null ? 43 : $mode.hashCode());
        return result;
    }

    public String toString() {
        return "CustomSecurityProperties(ipWhileListCheck=" + this.getIpWhileListCheck() + ", mode=" + this.getMode() + ")";
    }

    public interface SecurityMode {
        String RESOURCE_MODE = "resource";
        String MENU_MODE = "menu";
    }
}

