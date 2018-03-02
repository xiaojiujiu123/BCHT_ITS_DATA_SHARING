package com.bcht.its.dataSharing.beans;

import lombok.Getter;
import lombok.Setter;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

/**
 * Created by JXX on 2017/8/10.
 * 访问日志对象
 */
@Getter
@Setter
@Table("ds_query_log")
public class QueryLog {
    @Name
    private String id;
    private Date fwsj;
    private String fwyh;
    private String fwip;
    private String ms;
}
