package cn.mango.community.model;

public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;


    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * 设置
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取
     * @return gmtCreate
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置
     * @param gmtCreate
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取
     * @return gmtModified
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置
     * @param gmtModified
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String toString() {
        return "User{id = " + id + ", name = " + name + ", accountId = " + accountId + ", token = " + token + ", gmtCreate = " + gmtCreate + ", gmtModified = " + gmtModified + "}";
    }
}