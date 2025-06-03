package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import cn.hutool.core.util.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.ccp.api.contract.VehicleCcpExService;
import net.hwyz.iov.cloud.tsp.ccp.api.feign.service.ExCcpInfoService;
import net.hwyz.iov.cloud.tsp.ccp.api.feign.service.ExVehicleCcpService;
import net.hwyz.iov.cloud.tsp.idcm.api.contract.VehicleIdcmExService;
import net.hwyz.iov.cloud.tsp.idcm.api.feign.service.ExIdcmInfoService;
import net.hwyz.iov.cloud.tsp.idcm.api.feign.service.ExVehicleIdcmService;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception.ClientTypeInvalidException;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception.PartNotExistException;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception.VehiclePartNotMatchException;
import net.hwyz.iov.cloud.tsp.tbox.api.contract.VehicleTboxExService;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExTboxInfoService;
import net.hwyz.iov.cloud.tsp.tbox.api.feign.service.ExVehicleTboxService;
import org.springframework.stereotype.Service;

/**
 * 零部件应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PartAppService {

    private final ExCcpInfoService exCcpInfoService;
    private final ExTboxInfoService exTboxInfoService;
    private final ExIdcmInfoService exIdcmInfoService;
    private final ExVehicleCcpService exVehicleCcpService;
    private final ExVehicleTboxService exVehicleTboxService;
    private final ExVehicleIdcmService exVehicleIdcmService;

    /**
     * 检查零部件是否存在
     *
     * @param clientType 客户端类型
     * @param sn         零部件序列号
     */
    public void checkPartExist(ClientType clientType, String sn) {
        switch (clientType) {
            case TBOX -> {
                if (exTboxInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
            }
            case CCP -> {
                if (exCcpInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
            }
            case IDCM -> {
                if (exIdcmInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
            }
            case ADCM -> {
            }
            default -> throw new ClientTypeInvalidException(clientType);
        }
    }

    /**
     * 检查车辆零部件状态
     *
     * @param vin        车架号
     * @param sn         序列号
     * @param clientType 客户端类型
     */
    public void checkVehiclePartState(String vin, String sn, ClientType clientType) {
        switch (clientType) {
            case TBOX -> {
                if (exTboxInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
                VehicleTboxExService vehicleTbox = exVehicleTboxService.get(null, sn);
                if (ObjUtil.isNotNull(vehicleTbox) && !vehicleTbox.getVin().equalsIgnoreCase(vin)) {
                    throw new VehiclePartNotMatchException(clientType, sn, vehicleTbox.getVin(), vin);
                }
            }
            case CCP -> {
                if (exCcpInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
                VehicleCcpExService vehicleCcp = exVehicleCcpService.get(null, sn);
                if (ObjUtil.isNotNull(vehicleCcp) && !vehicleCcp.getVin().equalsIgnoreCase(vin)) {
                    throw new VehiclePartNotMatchException(clientType, sn, vehicleCcp.getVin(), vin);
                }
            }
            case IDCM -> {
                if (exIdcmInfoService.getBySn(sn) == null) {
                    throw new PartNotExistException(clientType, sn);
                }
                VehicleIdcmExService vehicleIdcm = exVehicleIdcmService.get(null, sn);
                if (ObjUtil.isNotNull(vehicleIdcm) && !vehicleIdcm.getVin().equalsIgnoreCase(vin)) {
                    throw new VehiclePartNotMatchException(clientType, sn, vehicleIdcm.getVin(), vin);
                }
            }
            case ADCM -> {
            }
        }
        throw new ClientTypeInvalidException(clientType);
    }

}
