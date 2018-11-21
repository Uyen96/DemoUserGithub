package com.example.uyen.demousergithub;

public class User {
    private int mId;
    private String mName;
    private String mFullName;
    private String mDes;

    public User(int id, String name, String fullName, String des) {
        mId = id;
        mName = name;
        mFullName = fullName;
        mDes = des;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        mDes = des;
    }
}
