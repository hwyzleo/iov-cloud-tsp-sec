package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;
import net.hwyz.iov.cloud.tsp.sec.service.domain.contract.enums.CertOperation;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.dao.CertOperationLogDao;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po.CertOperationLogPo;
import net.hwyz.iov.cloud.tsp.vmd.api.feign.service.ExVehicleLifecycleService;
import org.springframework.stereotype.Service;

/**
 * 证书应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CertAppService {

    private final PartAppService partAppService;
    private final CertOperationLogDao certOperationLogDao;
    private final ExVehicleLifecycleService exVehicleLifecycleService;

    /**
     * 申请证书
     *
     * @param vin        车架号
     * @param deviceId   客户端ID
     * @param clientType 客户端类型
     * @param csr        证书签名请求
     * @return 证书
     */
    public CertificateResponse apply(String vin, String deviceId, ClientType clientType, CertificateSigningRequest csr) {
        partAppService.checkPartExist(clientType, deviceId);
        String subject = "CN=" + deviceId + "-" + vin + ",O=HWYZ,OU=" + clientType.name() + ",C=CN";
        // TODO 调用 PKI 生成证书并返回相关证书信息
        String sn = "sn" + deviceId;
        String template = "template";
        String issuer = "issuer";
        CertificateResponse response = new CertificateResponse();
        recordLog(clientType, deviceId, vin, CertOperation.APPLY_AND_DOWNLOAD, sn, template, subject, issuer, csr.getCsr());
        switch (clientType) {
            case TBOX -> exVehicleLifecycleService.recordFirstApplyTboxCertNode(vin);
            case CCP -> exVehicleLifecycleService.recordFirstApplyCcpCertNode(vin);
            case IDCM -> exVehicleLifecycleService.recordFirstApplyIdcmCertNode(vin);
            case ADCM -> exVehicleLifecycleService.recordFirstApplyAdcmCertNode(vin);
        }
        return response;
    }

    /**
     * 更新证书
     *
     * @param vin        车架号
     * @param deviceId   客户端ID
     * @param clientType 客户端类型
     * @return 证书
     */
    public CertificateResponse renew(String vin, String deviceId, ClientType clientType) {
        // TODO 调用 PKI 更新证书
        return null;
    }

    /**
     * 记录操作日志
     *
     * @param clientType 客户端类型
     * @param deviceSn   设备SN
     * @param deviceKey  设备KEY
     * @param operation  证书操作
     * @param sn         证书序列号
     * @param template   证书模板
     * @param subject    证书主题
     * @param issuer     证书颁布者
     * @param csr        证书签名请求
     */
    private void recordLog(ClientType clientType, String deviceSn, String deviceKey, CertOperation operation, String sn,
                           String template, String subject, String issuer, String csr) {
        certOperationLogDao.insertPo(CertOperationLogPo.builder()
                .deviceType(clientType.name())
                .deviceSn(deviceSn)
                .deviceKey(deviceKey)
                .deviceOperation(operation.getValue())
                .sn(sn)
                .template(template)
                .subject(subject)
                .issuer(issuer)
                .csr(csr)
                .build());
    }


}
