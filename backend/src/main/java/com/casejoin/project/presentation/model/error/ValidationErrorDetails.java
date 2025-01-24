package com.casejoin.project.presentation.model.error;

import java.util.List;

public record ValidationErrorDetails(String title, int status, String detail, List<FieldMessage> validationErrors){}
