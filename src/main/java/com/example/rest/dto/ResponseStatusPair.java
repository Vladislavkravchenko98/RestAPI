package com.example.rest.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ResponseStatusPair {
    private long id;
    private boolean newStatus;
    private boolean oldStatus;

    public ResponseStatusPair(long id, boolean newStatus, boolean oldStatus) {
        this.id = id;
        this.newStatus = newStatus;
        this.oldStatus = oldStatus;
    }

    public ResponseStatusPair() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNewStatus() {
        return newStatus;
    }

    public void setNewStatus(boolean newStatus) {
        this.newStatus = newStatus;
    }

    public boolean isOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(boolean oldStatus) {
        this.oldStatus = oldStatus;
    }
}
