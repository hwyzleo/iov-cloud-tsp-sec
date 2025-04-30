package net.hwyz.iov.cloud.tsp.sec.api.feign.service.factory;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.tsp.sec.api.feign.service.ExSkService;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 密钥相关服务降级处理
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
public class ExSkServiceFallbackFactory implements FallbackFactory<ExSkService> {

    @Override
    public ExSkService create(Throwable throwable) {
        return new ExSkService() {
            @Override
            public Map<String, String> generateVehicleSk(String vin) {
                logger.error("安全服务生成车辆[{}]密钥调用失败", vin, throwable);
                return null;
            }
        };
    }
}
