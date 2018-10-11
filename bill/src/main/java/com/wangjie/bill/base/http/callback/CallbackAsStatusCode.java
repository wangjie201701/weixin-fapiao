package com.wangjie.bill.base.http.callback;

import java.io.IOException;

public abstract class CallbackAsStatusCode extends HttpClientCallback {

    public CallbackType getType() {
        return CallbackType.STATUS_CODE;
    }

    public abstract void exec(final int code) throws IOException;
}
