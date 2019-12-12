package com.codehub.andorid_course;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlayNowString {

    @NonNull
    private String var1;
    @Nullable
    private String var2;
    @Nullable
    private Integer var3;
    @Nullable
    private Integer var4;

    public PlayNowString(@NonNull String var1, @Nullable String var2, @Nullable Integer var3, @Nullable Integer var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(@Nullable String var2) {
        this.var2 = var2;
    }

    @Nullable
    public Integer getVar3() {
        return var3;
    }

    public void setVar3(@Nullable Integer var3) {
        this.var3 = var3;
    }

    @Nullable
    public Integer getVar4() {
        return var4;
    }

    public void setVar4(@Nullable Integer var4) {
        this.var4 = var4;
    }

    @Override
    public String toString() {
        return "PlayNowString{" +
                "var1='" + var1 + '\'' +
                ", var2='" + var2 + '\'' +
                ", var3='" + var3 + '\'' +
                ", var4='" + var4 + '\'' +
                '}';
    }
}
