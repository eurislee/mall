package me.euris.mall.model.vo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 02:40:00
 */

@Getter
@Setter
@ToString
public class SysUserVO {
    private Long id;

    @NotNull(message = "必须提供用户所在的部门")
    private Long deptId;

    @NotBlank(message = "用户名不可以为空")
    @Length(min = 1, max = 128, message = "用户名长度需要在128个字以内")
    private String username;

    @NotBlank(message = "密码不可以为空")
    @Length(min = 1, max = 128, message = "用户名长度需要在128个字以内")
    private String password;

    @NotBlank(message = "邮箱不允许为空")
    @Length(min = 5, max = 128, message = "邮箱长度需要在50个字符以内")
    private String mail;

    @NotBlank(message = "电话不可以为空")
    @Length(min = 1, max = 20, message = "电话长度需要在20个字以内")
    private String cellphone;

    @NotNull(message = "必须指定用户的状态")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Integer status;

    @Length(max = 500, message = "备注长度需要在500个字以内")
    private String remark = "";
}
