package com.xiangtai.framework.core.dao.domain;

import java.io.Serializable;

public class StatusData implements Serializable {
    private static final long serialVersionUID = -3205094719765712634L;
    private boolean resultStatus = true;
    private String resultMessage;

    public StatusData() {
    }

    public boolean isResultStatus() {
        return this.resultStatus;
    }

    public void setResultStatus(boolean resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("resultStatus:");
        ret.append(this.resultStatus);
        ret.append(",");
        ret.append("resultMessage:");
        ret.append(this.resultMessage);
        return ret.toString();
    }
}
