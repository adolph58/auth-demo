package com.example.demo.auth;

import cn.hutool.core.util.BooleanUtil;
import io.netty.util.concurrent.FastThreadLocal;

public class CurrentAccountHolder {
    private static final FastThreadLocal<Boolean> SWITCH_FLAG = new FastThreadLocal();
    private static final FastThreadLocal<IAccount> CURRENT_USER = new FastThreadLocal();

    public CurrentAccountHolder() {
    }

    public static void init() {
        SWITCH_FLAG.set(true);
    }

    public static void set(IAccount user) {
        Boolean switchFlag = (Boolean)SWITCH_FLAG.get();
        if (BooleanUtil.isTrue(switchFlag)) {
            CURRENT_USER.set(user);
        }

    }

    public static IAccount get() {
        Boolean switchFlag = (Boolean)SWITCH_FLAG.get();
        return BooleanUtil.isTrue(switchFlag) ? (IAccount)CURRENT_USER.get() : null;
    }

    public static void remove() {
        SWITCH_FLAG.remove();
        CURRENT_USER.remove();
    }
}

