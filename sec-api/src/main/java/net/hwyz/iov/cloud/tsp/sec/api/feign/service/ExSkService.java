package net.hwyz.iov.cloud.tsp.sec.api.feign.service;

import net.hwyz.iov.cloud.framework.common.constant.ServiceNameConstants;
import net.hwyz.iov.cloud.tsp.sec.api.feign.service.factory.ExSkServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * 密钥相关服务接口
 */
@FeignClient(contextId = "exSkService", value = ServiceNameConstants.TSP_SEC, path = "/service/sk", fallbackFactory = ExSkServiceFallbackFactory.class)
public interface ExSkService {

    /**
     * 生成车辆密钥
     *
     * @param vin 车架号
     */
    @PutMapping("/generateVehicleSk/{vin}")
    void generateVehicleSk(@PathVariable String vin);

}
