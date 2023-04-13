package com.morooi.telegram.bot.db.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 14:06
 */
@Data
@TableName("bwg_api_key")
public class BwgApiKeyDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6752865134514588806L;

    @TableId(type = IdType.AUTO)
    private Long pid;

    private Long userId;

    private String veid;

    private String apiKey;
}
