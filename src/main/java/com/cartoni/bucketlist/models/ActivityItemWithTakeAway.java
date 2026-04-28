package com.cartoni.bucketlist.models;

import com.cartoni.bucketlist.entities.Takeaway;

public class ActivityItemWithTakeAway {
    private Takeaway takeaway;
    private Integer activityId;
    private boolean isChecked;
    private boolean isFavouritem;

    public Takeaway getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(Takeaway takeaway) {
        this.takeaway = takeaway;
    }

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

    public boolean isFavouritem() {
        return isFavouritem;
    }

    public void setFavouritem(boolean favouritem) {
        isFavouritem = favouritem;
    }
}
