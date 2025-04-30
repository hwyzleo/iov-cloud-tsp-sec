package net.hwyz.iov.cloud.tsp.sec.service.facade.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.sec.service.application.service.SkAppService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 密钥相关服务接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/service/sk")
public class SkServiceController {

    private final SkAppService skAppService;

    /**
     * 生成车辆密钥
     *
     * @param vin 车架号
     */
    @PutMapping("/generateVehicleSk/{vin}")
    public Map<String, String> generateVehicleSk(@PathVariable String vin) {
        logger.info("生成车辆[{}]密钥", vin);
        return skAppService.generateVehicleSk(vin);
    }

}
