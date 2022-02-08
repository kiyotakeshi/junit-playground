package com.kiyotakeshi.junitPlayground.oreilly.recruitment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    @DisplayName("必須条件にマッチしない場合、 matches は false を返す")
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // set up(arrange)

        // 質問と望ましい回答を回答の重要度とともに Criterion(条件) にセットする
        Question question = new BooleanQuestion(1, "has Bonus?");
        Answer criteriaAnswer = new Answer(Bool.TRUE, question);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);

        // 複数の Criterion を保持
        Criteria criteria = new Criteria();
        criteria.add(criterion);

        Profile profile = new Profile("Mike popcorn, Inc");
        Answer profileAnswer = new Answer(Bool.FALSE, question);
        profile.add(profileAnswer);

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertFalse(matches);
    }

    @Test
    @DisplayName("不問の条件がある場合、 matches は true を返す")
    void matchAnswersTrueForAnyDontCareCriteria() {
        // set up(arrange)

        // 質問と望ましい回答を回答の重要度とともに Criterion(条件) にセットする
        Question question = new BooleanQuestion(1, "has Water Server?");
        Answer criteriaAnswer = new Answer(Bool.TRUE, question);
        Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);

        // 複数の Criterion を保持
        Criteria criteria = new Criteria();
        criteria.add(criterion);

        Profile profile = new Profile("Mike popcorn, Inc");
        Answer profileAnswer = new Answer(Bool.FALSE, question);
        profile.add(profileAnswer);

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertTrue(matches);
    }
}
