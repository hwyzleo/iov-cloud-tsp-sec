package net.hwyz.iov.cloud.tsp.sec.service.domain.contract.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 证书操作枚举类
 *
 * @author hwyz_leo
 */
@Getter
@AllArgsConstructor
public enum CertOperation {

    /** 申请并下载 **/
    APPLY_AND_DOWNLOAD(1);

    private final Integer value;

    public static CertOperation valOf(Integer val) {
        return Arrays.stream(CertOperation.values())
                .filter(certOperation -> Objects.equals(certOperation.value, val))
                .findFirst()
                .orElse(null);
    }

}
