package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.SecretKeyRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.SecretKeyResponse;
import net.hwyz.iov.cloud.tsp.sec.service.domain.contract.enums.VehicleSkType;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception.ClientTypeInvalidException;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.dao.VehSkDao;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po.VehSkPo;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.util.EncryptUtil;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.util.SignUtil;
import net.hwyz.iov.cloud.tsp.vmd.api.feign.service.ExVehicleLifecycleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static net.hwyz.iov.cloud.tsp.sec.service.domain.contract.constant.Constants.COMM_SK_EXPIRE_MILLISECONDS;

/**
 * 密钥应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SkAppService {

    private final VehSkDao vehSkDao;
    private final SignUtil signUtil;
    private final EncryptUtil encryptUtil;
    private final PartAppService partAppService;
    private final ExVehicleLifecycleService exVehicleLifecycleService;

    /**
     * 内部车辆集合
     * 逗号分割
     */
    @Value("${biz.internal-vehicles:}")
    private String internalVehicles;

    /**
     * 生成车辆相关密钥
     *
     * @param vin 车架号
     * @return 密钥Map
     */
    public Map<String, String> generateVehicleSk(String vin) {
        Map<String, String> skMap = new HashMap<>();
        if (getVehicleSk(vin).size() == 0) {
            skMap.put(VehicleSkType.TBOX_SK.name(), generateSk(vin, VehicleSkType.TBOX_SK, 16));
            skMap.put(VehicleSkType.TBOX_IMMO.name(), generateSk(vin, VehicleSkType.TBOX_IMMO, 16));
            skMap.put(VehicleSkType.BTM_IMMO.name(), generateSk(vin, VehicleSkType.BTM_IMMO, 16));
            skMap.put(VehicleSkType.CCP_IMMO.name(), generateSk(vin, VehicleSkType.CCP_IMMO, 16));
            skMap.put(VehicleSkType.IBCM_IMMO.name(), generateSk(vin, VehicleSkType.IBCM_IMMO, 16));
            skMap.put(VehicleSkType.MDCU_IMMO.name(), generateSk(vin, VehicleSkType.MDCU_IMMO, 16));
        }
        return skMap;
    }

    /**
     * 生成车辆终端通讯密钥
     *
     * @param vin        车架号
     * @param clientId   客户端ID
     * @param clientType 客户端类型
     * @param request    密钥请求
     * @return 密钥响应
     */
    public SecretKeyResponse generateVehicleClientCommSk(String vin, String clientId, ClientType clientType, SecretKeyRequest request) {
        partAppService.checkVehiclePartState(vin, clientId, clientType);
        String certInfo;
        if (ObjUtil.contains(internalVehicles.split(","), vin)) {
            // 内部车辆则使用默认证书默认密钥
            certInfo = "default";
        } else {
            certInfo = signUtil.p7AttachedVerify(request.getSignature());
        }
        String sk;
        switch (clientType) {
            case TBOX -> sk = generateSk(vin, VehicleSkType.TBOX_COMM_SK, 16);
            case CCP -> sk = generateSk(vin, VehicleSkType.CCP_COMM_SK, 16);
            case IDCM -> sk = generateSk(vin, VehicleSkType.IDCM_COMM_SK, 16);
            case ADCM -> sk = generateSk(vin, VehicleSkType.ADCM_COMM_SK, 16);
            default -> throw new ClientTypeInvalidException(clientType);
        }
        SecretKeyResponse response = SecretKeyResponse.builder()
                .encryptedSk(encryptUtil.asymmetricEncryptByCert(certInfo, sk))
                .expireTime(System.currentTimeMillis() + COMM_SK_EXPIRE_MILLISECONDS)
                .build();
        switch (clientType) {
            case TBOX -> exVehicleLifecycleService.recordFirstApplyTboxCommSkNode(vin);
            case CCP -> exVehicleLifecycleService.recordFirstApplyCcpCommSkNode(vin);
            case IDCM -> exVehicleLifecycleService.recordFirstApplyIdcmCommSkNode(vin);
            case ADCM -> exVehicleLifecycleService.recordFirstApplyAdcmCommSkNode(vin);
        }
        return response;
    }

    /**
     * 获取车辆相关密钥
     *
     * @param vin 车架号
     * @return 车辆相关密钥Map
     */
    public Map<String, String> getVehicleSk(String vin) {
        return vehSkDao.selectPoByExample(VehSkPo.builder().vin(vin).build()).stream()
                .collect(Collectors.toMap(VehSkPo::getType, VehSkPo::getValue));
    }

    /**
     * 生成密钥
     *
     * @param vin           车架号
     * @param vehicleSkType 车辆密钥类型
     * @param skLength      密钥长度
     * @return 密钥
     */
    private String generateSk(String vin, VehicleSkType vehicleSkType, int skLength) {
        VehSkPo vehSkPo = vehSkDao.selectLastPo(vin, vehicleSkType.name());
        // 如果之前密钥是在10分钟内创建的，直接返回该密钥
        if (ObjectUtil.isNotNull(vehSkPo) && vehSkPo.getCreateTime().getTime() + 10 * 60 * 1000 > System.currentTimeMillis()) {
            return vehSkPo.getValue();
        }
        String sk = HexUtil.encodeHexStr(RandomUtil.randomBytes(skLength));
        vehSkDao.insertPo(VehSkPo.builder()
                .vin(vin)
                .type(vehicleSkType.name())
                .value(sk)
                .build());
        return sk;
    }

}