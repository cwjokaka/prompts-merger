package com.okaka.pm.domain.prompt.entity.aggregate;

import com.okaka.pm.domain.prompt.entity.vo.Prompt;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author okaka
 * @date 2023-09-06
 */
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class PromptAggregate {

    String id;

    List<Prompt> prompts;


}
