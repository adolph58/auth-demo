package com.example.demo.auth;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Map;
import java.util.Set;

/**
 * @author Arte
 * @create 2023/5/10.
 */
public class SecurityClientBo implements IAccount {
    private String clientId;
    private Long id;
    private String password;
    private Long userId;
    private Long deptId;
    private String deptCode;
    private String deptName;
    private Long canalId;
    private String canalCode;
    private String canalName;
    private Set<Long> canalIds;
    private Set<Long> childCanalIds;
    private Set<String> roles;
    private Set<Long> roleIds;
    private String jti;
    private Long exp;
    private Set<String> scope;
    private Set<String> authorities;
    private Set<String> aud;
    private Set<String> authorizedGrantTypes;
    private Set<String> redirectUris;
    private Set<String> ipWhitelist;
    private String sourceIp;
    private Long expiryTime;
    private Boolean invalid;
    private String token;
    private Map<String, Object> extension;

    @JSONField(
            serialize = false
    )
    public Boolean isUser() {
        return 0L != this.userId && -1L != this.userId;
    }

    @JSONField(
            serialize = false
    )
    public Boolean isAdmin() {
        if (CollectionUtil.isNotEmpty(this.getRoles()) && this.getRoles().contains(CustomSecurityProperties.getAdminRoleName())) {
            return true;
        } else {
            Long adminId = CustomSecurityProperties.getAdminId();
            return null != adminId && adminId.equals(this.userId);
        }
    }

    @JSONField(
            serialize = false
    )
    public Long getUnionId() {
        return this.id;
    }

    @JSONField(
            serialize = false
    )
    public String getName() {
        return this.clientId;
    }

    public String getClientId() {
        return this.clientId;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public String getDeptCode() {
        return this.deptCode;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public Long getCanalId() {
        return this.canalId;
    }

    public String getCanalCode() {
        return this.canalCode;
    }

    public String getCanalName() {
        return this.canalName;
    }

    public Set<Long> getCanalIds() {
        return this.canalIds;
    }

    public Set<Long> getChildCanalIds() {
        return this.childCanalIds;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public Set<Long> getRoleIds() {
        return this.roleIds;
    }

    public String getJti() {
        return this.jti;
    }

    public Long getExp() {
        return this.exp;
    }

    public Set<String> getScope() {
        return this.scope;
    }

    public Set<String> getAuthorities() {
        return this.authorities;
    }

    public Set<String> getAud() {
        return this.aud;
    }

    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    public Set<String> getRedirectUris() {
        return this.redirectUris;
    }

    public Set<String> getIpWhitelist() {
        return this.ipWhitelist;
    }

    public String getSourceIp() {
        return this.sourceIp;
    }

    public Long getExpiryTime() {
        return this.expiryTime;
    }

    public Boolean getInvalid() {
        return this.invalid;
    }

    public String getToken() {
        return this.token;
    }

    public Map<String, Object> getExtension() {
        return this.extension;
    }

    public SecurityClientBo setClientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    public SecurityClientBo setId(final Long id) {
        this.id = id;
        return this;
    }

    public SecurityClientBo setPassword(final String password) {
        this.password = password;
        return this;
    }

    public SecurityClientBo setUserId(final Long userId) {
        this.userId = userId;
        return this;
    }

    public SecurityClientBo setDeptId(final Long deptId) {
        this.deptId = deptId;
        return this;
    }

    public SecurityClientBo setDeptCode(final String deptCode) {
        this.deptCode = deptCode;
        return this;
    }

    public SecurityClientBo setDeptName(final String deptName) {
        this.deptName = deptName;
        return this;
    }

    public SecurityClientBo setCanalId(final Long canalId) {
        this.canalId = canalId;
        return this;
    }

    public SecurityClientBo setCanalCode(final String canalCode) {
        this.canalCode = canalCode;
        return this;
    }

    public SecurityClientBo setCanalName(final String canalName) {
        this.canalName = canalName;
        return this;
    }

    public SecurityClientBo setCanalIds(final Set<Long> canalIds) {
        this.canalIds = canalIds;
        return this;
    }

    public SecurityClientBo setChildCanalIds(final Set<Long> childCanalIds) {
        this.childCanalIds = childCanalIds;
        return this;
    }

    public SecurityClientBo setRoles(final Set<String> roles) {
        this.roles = roles;
        return this;
    }

    public SecurityClientBo setRoleIds(final Set<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public SecurityClientBo setJti(final String jti) {
        this.jti = jti;
        return this;
    }

    public SecurityClientBo setExp(final Long exp) {
        this.exp = exp;
        return this;
    }

    public SecurityClientBo setScope(final Set<String> scope) {
        this.scope = scope;
        return this;
    }

    public SecurityClientBo setAuthorities(final Set<String> authorities) {
        this.authorities = authorities;
        return this;
    }

    public SecurityClientBo setAud(final Set<String> aud) {
        this.aud = aud;
        return this;
    }

    public SecurityClientBo setAuthorizedGrantTypes(final Set<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public SecurityClientBo setRedirectUris(final Set<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

    public SecurityClientBo setIpWhitelist(final Set<String> ipWhitelist) {
        this.ipWhitelist = ipWhitelist;
        return this;
    }

    public SecurityClientBo setSourceIp(final String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public SecurityClientBo setExpiryTime(final Long expiryTime) {
        this.expiryTime = expiryTime;
        return this;
    }

    public SecurityClientBo setInvalid(final Boolean invalid) {
        this.invalid = invalid;
        return this;
    }

    public SecurityClientBo setToken(final String token) {
        this.token = token;
        return this;
    }

    public SecurityClientBo setExtension(final Map<String, Object> extension) {
        this.extension = extension;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SecurityClientBo)) {
            return false;
        } else {
            SecurityClientBo other = (SecurityClientBo)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label335: {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id == null) {
                            break label335;
                        }
                    } else if (this$id.equals(other$id)) {
                        break label335;
                    }

                    return false;
                }

                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                Object this$deptId = this.getDeptId();
                Object other$deptId = other.getDeptId();
                if (this$deptId == null) {
                    if (other$deptId != null) {
                        return false;
                    }
                } else if (!this$deptId.equals(other$deptId)) {
                    return false;
                }

                label314: {
                    Object this$canalId = this.getCanalId();
                    Object other$canalId = other.getCanalId();
                    if (this$canalId == null) {
                        if (other$canalId == null) {
                            break label314;
                        }
                    } else if (this$canalId.equals(other$canalId)) {
                        break label314;
                    }

                    return false;
                }

                label307: {
                    Object this$exp = this.getExp();
                    Object other$exp = other.getExp();
                    if (this$exp == null) {
                        if (other$exp == null) {
                            break label307;
                        }
                    } else if (this$exp.equals(other$exp)) {
                        break label307;
                    }

                    return false;
                }

                Object this$expiryTime = this.getExpiryTime();
                Object other$expiryTime = other.getExpiryTime();
                if (this$expiryTime == null) {
                    if (other$expiryTime != null) {
                        return false;
                    }
                } else if (!this$expiryTime.equals(other$expiryTime)) {
                    return false;
                }

                Object this$invalid = this.getInvalid();
                Object other$invalid = other.getInvalid();
                if (this$invalid == null) {
                    if (other$invalid != null) {
                        return false;
                    }
                } else if (!this$invalid.equals(other$invalid)) {
                    return false;
                }

                label286: {
                    Object this$clientId = this.getClientId();
                    Object other$clientId = other.getClientId();
                    if (this$clientId == null) {
                        if (other$clientId == null) {
                            break label286;
                        }
                    } else if (this$clientId.equals(other$clientId)) {
                        break label286;
                    }

                    return false;
                }

                label279: {
                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password == null) {
                            break label279;
                        }
                    } else if (this$password.equals(other$password)) {
                        break label279;
                    }

                    return false;
                }

                Object this$deptCode = this.getDeptCode();
                Object other$deptCode = other.getDeptCode();
                if (this$deptCode == null) {
                    if (other$deptCode != null) {
                        return false;
                    }
                } else if (!this$deptCode.equals(other$deptCode)) {
                    return false;
                }

                label265: {
                    Object this$deptName = this.getDeptName();
                    Object other$deptName = other.getDeptName();
                    if (this$deptName == null) {
                        if (other$deptName == null) {
                            break label265;
                        }
                    } else if (this$deptName.equals(other$deptName)) {
                        break label265;
                    }

                    return false;
                }

                Object this$canalCode = this.getCanalCode();
                Object other$canalCode = other.getCanalCode();
                if (this$canalCode == null) {
                    if (other$canalCode != null) {
                        return false;
                    }
                } else if (!this$canalCode.equals(other$canalCode)) {
                    return false;
                }

                label251: {
                    Object this$canalName = this.getCanalName();
                    Object other$canalName = other.getCanalName();
                    if (this$canalName == null) {
                        if (other$canalName == null) {
                            break label251;
                        }
                    } else if (this$canalName.equals(other$canalName)) {
                        break label251;
                    }

                    return false;
                }

                Object this$canalIds = this.getCanalIds();
                Object other$canalIds = other.getCanalIds();
                if (this$canalIds == null) {
                    if (other$canalIds != null) {
                        return false;
                    }
                } else if (!this$canalIds.equals(other$canalIds)) {
                    return false;
                }

                Object this$childCanalIds = this.getChildCanalIds();
                Object other$childCanalIds = other.getChildCanalIds();
                if (this$childCanalIds == null) {
                    if (other$childCanalIds != null) {
                        return false;
                    }
                } else if (!this$childCanalIds.equals(other$childCanalIds)) {
                    return false;
                }

                label230: {
                    Object this$roles = this.getRoles();
                    Object other$roles = other.getRoles();
                    if (this$roles == null) {
                        if (other$roles == null) {
                            break label230;
                        }
                    } else if (this$roles.equals(other$roles)) {
                        break label230;
                    }

                    return false;
                }

                label223: {
                    Object this$roleIds = this.getRoleIds();
                    Object other$roleIds = other.getRoleIds();
                    if (this$roleIds == null) {
                        if (other$roleIds == null) {
                            break label223;
                        }
                    } else if (this$roleIds.equals(other$roleIds)) {
                        break label223;
                    }

                    return false;
                }

                Object this$jti = this.getJti();
                Object other$jti = other.getJti();
                if (this$jti == null) {
                    if (other$jti != null) {
                        return false;
                    }
                } else if (!this$jti.equals(other$jti)) {
                    return false;
                }

                Object this$scope = this.getScope();
                Object other$scope = other.getScope();
                if (this$scope == null) {
                    if (other$scope != null) {
                        return false;
                    }
                } else if (!this$scope.equals(other$scope)) {
                    return false;
                }

                label202: {
                    Object this$authorities = this.getAuthorities();
                    Object other$authorities = other.getAuthorities();
                    if (this$authorities == null) {
                        if (other$authorities == null) {
                            break label202;
                        }
                    } else if (this$authorities.equals(other$authorities)) {
                        break label202;
                    }

                    return false;
                }

                label195: {
                    Object this$aud = this.getAud();
                    Object other$aud = other.getAud();
                    if (this$aud == null) {
                        if (other$aud == null) {
                            break label195;
                        }
                    } else if (this$aud.equals(other$aud)) {
                        break label195;
                    }

                    return false;
                }

                Object this$authorizedGrantTypes = this.getAuthorizedGrantTypes();
                Object other$authorizedGrantTypes = other.getAuthorizedGrantTypes();
                if (this$authorizedGrantTypes == null) {
                    if (other$authorizedGrantTypes != null) {
                        return false;
                    }
                } else if (!this$authorizedGrantTypes.equals(other$authorizedGrantTypes)) {
                    return false;
                }

                Object this$redirectUris = this.getRedirectUris();
                Object other$redirectUris = other.getRedirectUris();
                if (this$redirectUris == null) {
                    if (other$redirectUris != null) {
                        return false;
                    }
                } else if (!this$redirectUris.equals(other$redirectUris)) {
                    return false;
                }

                label174: {
                    Object this$ipWhitelist = this.getIpWhitelist();
                    Object other$ipWhitelist = other.getIpWhitelist();
                    if (this$ipWhitelist == null) {
                        if (other$ipWhitelist == null) {
                            break label174;
                        }
                    } else if (this$ipWhitelist.equals(other$ipWhitelist)) {
                        break label174;
                    }

                    return false;
                }

                label167: {
                    Object this$sourceIp = this.getSourceIp();
                    Object other$sourceIp = other.getSourceIp();
                    if (this$sourceIp == null) {
                        if (other$sourceIp == null) {
                            break label167;
                        }
                    } else if (this$sourceIp.equals(other$sourceIp)) {
                        break label167;
                    }

                    return false;
                }

                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token != null) {
                        return false;
                    }
                } else if (!this$token.equals(other$token)) {
                    return false;
                }

                Object this$extension = this.getExtension();
                Object other$extension = other.getExtension();
                if (this$extension == null) {
                    if (other$extension != null) {
                        return false;
                    }
                } else if (!this$extension.equals(other$extension)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SecurityClientBo;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $deptId = this.getDeptId();
        result = result * 59 + ($deptId == null ? 43 : $deptId.hashCode());
        Object $canalId = this.getCanalId();
        result = result * 59 + ($canalId == null ? 43 : $canalId.hashCode());
        Object $exp = this.getExp();
        result = result * 59 + ($exp == null ? 43 : $exp.hashCode());
        Object $expiryTime = this.getExpiryTime();
        result = result * 59 + ($expiryTime == null ? 43 : $expiryTime.hashCode());
        Object $invalid = this.getInvalid();
        result = result * 59 + ($invalid == null ? 43 : $invalid.hashCode());
        Object $clientId = this.getClientId();
        result = result * 59 + ($clientId == null ? 43 : $clientId.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $deptCode = this.getDeptCode();
        result = result * 59 + ($deptCode == null ? 43 : $deptCode.hashCode());
        Object $deptName = this.getDeptName();
        result = result * 59 + ($deptName == null ? 43 : $deptName.hashCode());
        Object $canalCode = this.getCanalCode();
        result = result * 59 + ($canalCode == null ? 43 : $canalCode.hashCode());
        Object $canalName = this.getCanalName();
        result = result * 59 + ($canalName == null ? 43 : $canalName.hashCode());
        Object $canalIds = this.getCanalIds();
        result = result * 59 + ($canalIds == null ? 43 : $canalIds.hashCode());
        Object $childCanalIds = this.getChildCanalIds();
        result = result * 59 + ($childCanalIds == null ? 43 : $childCanalIds.hashCode());
        Object $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
        Object $roleIds = this.getRoleIds();
        result = result * 59 + ($roleIds == null ? 43 : $roleIds.hashCode());
        Object $jti = this.getJti();
        result = result * 59 + ($jti == null ? 43 : $jti.hashCode());
        Object $scope = this.getScope();
        result = result * 59 + ($scope == null ? 43 : $scope.hashCode());
        Object $authorities = this.getAuthorities();
        result = result * 59 + ($authorities == null ? 43 : $authorities.hashCode());
        Object $aud = this.getAud();
        result = result * 59 + ($aud == null ? 43 : $aud.hashCode());
        Object $authorizedGrantTypes = this.getAuthorizedGrantTypes();
        result = result * 59 + ($authorizedGrantTypes == null ? 43 : $authorizedGrantTypes.hashCode());
        Object $redirectUris = this.getRedirectUris();
        result = result * 59 + ($redirectUris == null ? 43 : $redirectUris.hashCode());
        Object $ipWhitelist = this.getIpWhitelist();
        result = result * 59 + ($ipWhitelist == null ? 43 : $ipWhitelist.hashCode());
        Object $sourceIp = this.getSourceIp();
        result = result * 59 + ($sourceIp == null ? 43 : $sourceIp.hashCode());
        Object $token = this.getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        Object $extension = this.getExtension();
        result = result * 59 + ($extension == null ? 43 : $extension.hashCode());
        return result;
    }

    public String toString() {
        return "SecurityClientBo(clientId=" + this.getClientId() + ", id=" + this.getId() + ", password=" + this.getPassword() + ", userId=" + this.getUserId() + ", deptId=" + this.getDeptId() + ", deptCode=" + this.getDeptCode() + ", deptName=" + this.getDeptName() + ", canalId=" + this.getCanalId() + ", canalCode=" + this.getCanalCode() + ", canalName=" + this.getCanalName() + ", canalIds=" + this.getCanalIds() + ", childCanalIds=" + this.getChildCanalIds() + ", roles=" + this.getRoles() + ", roleIds=" + this.getRoleIds() + ", jti=" + this.getJti() + ", exp=" + this.getExp() + ", scope=" + this.getScope() + ", authorities=" + this.getAuthorities() + ", aud=" + this.getAud() + ", authorizedGrantTypes=" + this.getAuthorizedGrantTypes() + ", redirectUris=" + this.getRedirectUris() + ", ipWhitelist=" + this.getIpWhitelist() + ", sourceIp=" + this.getSourceIp() + ", expiryTime=" + this.getExpiryTime() + ", invalid=" + this.getInvalid() + ", token=" + this.getToken() + ", extension=" + this.getExtension() + ")";
    }

    public SecurityClientBo() {
    }

    public SecurityClientBo(final String clientId, final Long id, final String password, final Long userId, final Long deptId, final String deptCode, final String deptName, final Long canalId, final String canalCode, final String canalName, final Set<Long> canalIds, final Set<Long> childCanalIds, final Set<String> roles, final Set<Long> roleIds, final String jti, final Long exp, final Set<String> scope, final Set<String> authorities, final Set<String> aud, final Set<String> authorizedGrantTypes, final Set<String> redirectUris, final Set<String> ipWhitelist, final String sourceIp, final Long expiryTime, final Boolean invalid, final String token, final Map<String, Object> extension) {
        this.clientId = clientId;
        this.id = id;
        this.password = password;
        this.userId = userId;
        this.deptId = deptId;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.canalId = canalId;
        this.canalCode = canalCode;
        this.canalName = canalName;
        this.canalIds = canalIds;
        this.childCanalIds = childCanalIds;
        this.roles = roles;
        this.roleIds = roleIds;
        this.jti = jti;
        this.exp = exp;
        this.scope = scope;
        this.authorities = authorities;
        this.aud = aud;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.redirectUris = redirectUris;
        this.ipWhitelist = ipWhitelist;
        this.sourceIp = sourceIp;
        this.expiryTime = expiryTime;
        this.invalid = invalid;
        this.token = token;
        this.extension = extension;
    }
}
