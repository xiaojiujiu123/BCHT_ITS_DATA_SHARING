package com.bcht.its.dataSharing.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by zyq on 2017/4/14.
 */
@XStreamAlias("root")
public class Root1 {
    @XStreamAlias("head")
    private Head1 head1;
    @XStreamAlias("body")
    private Body body;

    public Head1 getHead1() {
        return head1;
    }

    public void setHead1(Head1 head1) {
        this.head1 = head1;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
