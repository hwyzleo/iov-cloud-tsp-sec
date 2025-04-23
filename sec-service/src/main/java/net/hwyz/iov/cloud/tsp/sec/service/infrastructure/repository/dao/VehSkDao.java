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

}
