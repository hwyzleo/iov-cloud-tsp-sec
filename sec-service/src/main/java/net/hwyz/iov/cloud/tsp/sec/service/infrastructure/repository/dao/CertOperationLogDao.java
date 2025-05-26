package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.dao;

import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po.CertOperationLogPo;
import net.hwyz.iov.cloud.framework.mysql.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 证书操作日志表 DAO
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-26
 */
@Mapper
public interface CertOperationLogDao extends BaseDao<CertOperationLogPo, Long> {

}
