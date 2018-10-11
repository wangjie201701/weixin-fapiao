package com.wangjie.bill.base.http.callback;



import com.wangjie.bill.base.http.HttpClientResponse;

import java.io.IOException;

public abstract class CallbackAsResponse extends HttpClientCallback {

    public CallbackType getType() {
        return CallbackType.RESPONSE;
    }

    public abstract void exec(final HttpClientResponse response) throws IOException;
}
