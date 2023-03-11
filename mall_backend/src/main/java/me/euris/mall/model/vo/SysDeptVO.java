package me.euris.mall.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 18:52:00
 */
@Getter
@Setter
@ToString
public class SysDeptVO {

    private Long id;

    private Long parentId = 0L;

    @NotBlank(message = "部门名称不能为空")
    @Length(min = 2, max = 128, message = "部门名称长度要在2-128个字符以内")
    private String name;

    @NotNull(message = "展示顺序不能为空")
    private Integer rank;

    @Length(max = 500, message = "备注的长度需要在500个字符以内")
    private String remark = "";

}
