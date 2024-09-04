package com.iworkcloud.pojo.entity;

public class Administrator {
    private int administratorId;
    private String administratorName;
    private String administratorAccount;
    private String administratorPassword;
    private String administratorAuthority;

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getAdministratorAccount() {
        return administratorAccount;
    }

    public void setAdministratorAccount(String administratorAccount) {
        this.administratorAccount = administratorAccount;
    }

    public String getAdministratorPassword() {
        return administratorPassword;
    }

    public void setAdministratorPassword(String administratorPassword) {
        this.administratorPassword = administratorPassword;
    }

    public String getAdministratorAuthority() {
        return administratorAuthority;
    }

    public void setAdministratorAuthority(String administratorAuthority) {
        this.administratorAuthority = administratorAuthority;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "administratorId=" + administratorId +
                ", administratorName='" + administratorName + '\'' +
                ", administratorAccount='" + administratorAccount + '\'' +
                ", administratorPassword='" + administratorPassword + '\'' +
                ", administratorAuthority='" + administratorAuthority + '\'' +
                '}';
    }
}
