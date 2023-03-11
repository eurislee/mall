package me.euris.mall.model.dto;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.euris.mall.model.domain.SysDept;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 00:16:00
 */

@Getter
@Setter
@ToString
public class SysDeptDTO extends SysDept {

    private List<SysDeptDTO> childrenDept;

    /**
     * @Name copyProperties
     * @Description 数据拷贝
     * @Author Euris Lee
     * @param: dept 拷贝对象
     * @CreateTime 3/11/23 1:30 AM
     * @return: me.euris.mall.model.dto.SysDeptDTO
     * @Throws
     */
    public static SysDeptDTO copyProperties(SysDept dept) {
        SysDeptDTO dto = new SysDeptDTO();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
