package com.morooi.telegram.bot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 11:50
 */
@Data
public class BwgApiDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6116935834172210760L;

    @JSONField(alternateNames = "hostname")
    private String hostName;

    @JSONField(alternateNames = "node_datacenter")
    private String nodeDataCenter;

    @JSONField(alternateNames = "ip_addresses")
    private List<String> ipAddresses;

    @JSONField(alternateNames = "plan_monthly_data")
    private Long planMonthlyData;

    @JSONField(alternateNames = "data_counter")
    private Long dataCounter;

    @JSONField(alternateNames = "data_next_reset")
    private Long dataNextReset;

    private Integer error;
}
