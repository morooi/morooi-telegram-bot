package com.morooi.telegram.bot.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.morooi.telegram.bot.db.model.BwgApiKeyDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Zhou.Shaojing
 * @date 2023/4/13 14:03
 */
public interface BwgApiKeyMapper extends BaseMapper<BwgApiKeyDO> {

    @Select("select pid, user_id, veid, api_key from bwg_api_key where user_id = #{userId}")
    BwgApiKeyDO selectByUserId(Long userId);

    @Update("update bwg_api_key set veid = #{param.veid}, api_key = #{param.apiKey} where user_id = #{param.userId}")
    int updateByUserId(@Param("param") BwgApiKeyDO bwgApiKeyDO);

}
