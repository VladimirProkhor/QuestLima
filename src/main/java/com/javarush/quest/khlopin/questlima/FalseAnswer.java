package com.javarush.quest.khlopin.questlima;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FalseAnswer extends Answer {
    private Long answerId;
    private String text;
    private String value;
    private Long questionId;
}
