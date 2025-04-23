package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import net.hwyz.iov.cloud.tsp.sec.service.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 密钥应用服务测试类
 *
 * @author hwyz_leo
 */
public class SkAppServiceTest extends BaseTest {

    @Autowired
    private SkAppService skAppService;

    @Test
    @Order(1)
    @DisplayName("生成车辆密钥")
    public void testGenerateVehicleSk() throws Exception {
        skAppService.generateVehicleSk(vin);
    }

}
