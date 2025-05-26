package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import net.hwyz.iov.cloud.framework.mysql.po.BasePo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * <p>
 * 证书操作日志表 数据对象
 * </p>
 *
 * @author hwyz_leo
 * @since 2025-05-26
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_cert_operation_log")
public class CertOperationLogPo extends BasePo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备类型
     */
    @TableField("device_type")
    private String deviceType;

    /**
     * 设备序列号
     */
    @TableField("device_sn")
    private String deviceSn;

    /**
     * 设备KEY
     */
    @TableField("device_key")
    private String deviceKey;

    /**
     * 设备请求类型：1-证书申请并下载
     */
    @TableField("device_operation")
    private Integer deviceOperation;

    /**
     * 证书序列号
     */
    @TableField("sn")
    private String sn;

    /**
     * 证书模板
     */
    @TableField("template")
    private String template;

    /**
     * 证书主题
     */
    @TableField("subject")
    private String subject;

    /**
     * 证书颁发者
     */
    @TableField("issuer")
    private String issuer;

    /**
     * 证书请求
     */
    @TableField("csr")
    private String csr;
}
