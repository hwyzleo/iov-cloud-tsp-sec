package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.framework.common.enums.EcuType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception.PartNotExistException;
import net.hwyz.iov.cloud.tsp.vmd.api.feign.service.ExVehiclePartService;
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

    private final ExVehiclePartService exVehiclePartService;

    /**
     * 申请证书
     *
     * @param vin        车架号
     * @param deviceId   客户端ID
     * @param clientType 客户端类型
     * @return 证书
     */
    public CertificateResponse apply(String vin, String deviceId, ClientType clientType) {
        checkPartExist(clientType, deviceId);
        // TODO 调用 PKI 生成证书
        CertificateResponse response = new CertificateResponse();
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
     * 检查零部件是否存在
     *
     * @param clientType 客户端类型
     * @param sn         零部件序列号
     */
    private void checkPartExist(ClientType clientType, String sn) {
        EcuType ecuType = null;
        switch (clientType) {
            case TBOX -> ecuType = EcuType.TBOX;
            case CCP -> ecuType = EcuType.CCP;
            case IDCM -> ecuType = EcuType.IDCM;
        }
        if (ecuType == null || exVehiclePartService.getPartBySn(ecuType, sn) == null) {
            throw new PartNotExistException(clientType, sn);
        }
    }
}
