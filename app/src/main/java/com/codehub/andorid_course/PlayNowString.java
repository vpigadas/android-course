package com.codehub.andorid_course;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlayNowString {

    @NonNull
    private String var1;
    @Nullable
    private String var2;
    @Nullable
    private String var3;
    @Nullable
    private String var4;

    public PlayNowString(@NonNull String var1, @Nullable String var2, @Nullable String var3, @Nullable String var4) {
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

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
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
