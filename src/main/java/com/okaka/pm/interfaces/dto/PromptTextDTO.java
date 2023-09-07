package com.okaka.pm.interfaces.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author okaka
 * @date 2023-09-07
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromptTextDTO {

    String text;

}
