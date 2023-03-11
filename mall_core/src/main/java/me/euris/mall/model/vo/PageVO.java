package me.euris.mall.model.vo;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-11-2023 04:58:00
 */

@Getter
@Setter
@ToString
@Builder
public class PageVO<T> {
    private List<T> data = Lists.newArrayList();

    private int total = 0;
}
