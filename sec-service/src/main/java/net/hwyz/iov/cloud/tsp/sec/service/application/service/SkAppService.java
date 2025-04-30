package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.sec.service.domain.contract.enums.VehicleSkType;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.dao.VehSkDao;
import net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po.VehSkPo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 生成车辆相关密钥
     *
     * @param vin 车架号
     * @return 密钥Map
     */
    public Map<String, String> generateVehicleSk(String vin) {
        Map<String, String> skMap = new HashMap<>();
        if (getVehicleSk(vin).size() == 0) {
            String tboxSk = HexUtil.encodeHexStr(RandomUtil.randomBytes(16));
            vehSkDao.insertPo(VehSkPo.builder()
                    .vin(vin)
                    .type(VehicleSkType.TBOX_SK.name())
                    .value(tboxSk)
                    .build());
            skMap.put(VehicleSkType.TBOX_SK.name(), tboxSk);
        }
        return skMap;
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

}