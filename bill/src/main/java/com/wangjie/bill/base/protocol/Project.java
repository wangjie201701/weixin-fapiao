package com.wangjie.bill.base.protocol;

public enum Project {

    bill(1, "bill");

    private String no;
    private String name;

    private Project(int no, String name) {
        this.no = String.format("%03d", no);
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }
}
