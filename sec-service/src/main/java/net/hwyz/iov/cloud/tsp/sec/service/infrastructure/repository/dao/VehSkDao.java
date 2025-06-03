package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po.VehSkPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 车辆密钥表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-04-23
 */
@Mapper
public interface VehSkDao extends BaseDao<VehSkPo, Long> {

    /**
     * 查询车辆指定类型最后的密钥
     *
     * @param vin  车架号
     * @param type 密钥类型
     * @return 车辆密钥
     */
    VehSkPo selectLastPo(String vin, String type);

}
