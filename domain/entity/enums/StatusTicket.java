package com.cit.backend.domain.entity.enums;

import jakarta.persistence.Entity;

public enum StatusTicket {
    OPEN,
    IN_ANALYSIS,
    ANALYZED,
    IN_PROGRESS,
    NOT_RESOLVED,
    RESOLVED
}
