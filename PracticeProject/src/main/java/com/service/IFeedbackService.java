package com.service;

import com.entity.Feedback;
import com.entity.IssueType;

public interface IFeedbackService {
    Feedback add(long createdByUserID, String description, IssueType issue);
    Feedback findById(long feedbackId);
}
