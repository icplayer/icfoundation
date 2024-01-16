package com.lorepo.icf.utils;

public interface IRequestListener{
    public void onFinished(String responseText);
    public void onError(int reason_code);
}
