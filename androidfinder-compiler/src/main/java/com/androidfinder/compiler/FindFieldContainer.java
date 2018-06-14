package com.androidfinder.compiler;

import java.util.List;

public class FindFieldContainer {

    private List<FindView> findViews;

    private List<FindColor> findColors;

    private List<FindString> findStrings;

    private List<FindDrawable> findDrawables;

    public List<FindView> getFindViews() {
        return findViews;
    }

    public void setFindViews(List<FindView> findViews) {
        this.findViews = findViews;
    }

    public List<FindColor> getFindColors() {
        return findColors;
    }

    public void setFindColors(List<FindColor> findColors) {
        this.findColors = findColors;
    }

    public List<FindString> getFindStrings() {
        return findStrings;
    }

    public void setFindStrings(List<FindString> findStrings) {
        this.findStrings = findStrings;
    }

    public List<FindDrawable> getFindDrawables() {
        return findDrawables;
    }

    public void setFindDrawables(List<FindDrawable> findDrawables) {
        this.findDrawables = findDrawables;
    }
}
