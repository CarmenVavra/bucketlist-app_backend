package com.cartoni.bucketlist.model;

import com.cartoni.bucketlist.entities.Takeaway;

public class TakeawayWithChecked extends Takeaway {
    private Integer activityId;
    private boolean isChecked;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
