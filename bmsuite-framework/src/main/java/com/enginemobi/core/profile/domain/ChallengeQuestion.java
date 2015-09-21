package com.enginemobi.core.profile.domain;

import com.enginemobi.core.generic.domain.BmSuiteEntity;

public interface ChallengeQuestion  extends BmSuiteEntity<Long, ChallengeQuestion> {

    String getQuestion();

    void setQuestion(String question);
}
