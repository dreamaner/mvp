package com.android.mvp;

/**
 * Created by Dreamaner on 2017/5/15.
 */

public class XDroidConfig {

    public boolean DEV;

    public boolean LOG;

    public String LOG_TAG;

    public String CACHE_SP_NAME;

    public String CACHE_DISK_DIR;

    public boolean isLOG() {
        return LOG;
    }

    public void setLOG(boolean LOG) {
        this.LOG = LOG;
    }

    public String getLOG_TAG() {
        return LOG_TAG;
    }

    public void setLOG_TAG(String LOG_TAG) {
        this.LOG_TAG = LOG_TAG;
    }

    public String getCACHE_SP_NAME() {
        return CACHE_SP_NAME;
    }

    public boolean isDEV() {
        return DEV;
    }

    public void setDEV(boolean DEV) {
        this.DEV = DEV;
    }

    public void setCACHE_SP_NAME(String CACHE_SP_NAME) {
        this.CACHE_SP_NAME = CACHE_SP_NAME;
    }

    public String getCACHE_DISK_DIR() {
        return CACHE_DISK_DIR;
    }

    public void setCACHE_DISK_DIR(String CACHE_DISK_DIR) {
        this.CACHE_DISK_DIR = CACHE_DISK_DIR;
    }
}
