package com.sch.igor;

public interface CheckNull {
    public int NOT_NULL=5;

    public static int checkNull (Object a, Object b) {
        if(a==null&&b!=null) {
        return 1;
        }
        if(a!=null&&b==null) {
        return -1;
        }
        if(a==null&&b==null) {
        return 0;
        }
        return NOT_NULL;
    }

}
