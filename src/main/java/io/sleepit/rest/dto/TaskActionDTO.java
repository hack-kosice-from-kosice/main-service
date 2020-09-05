package io.sleepit.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class TaskActionDTO {

    private final Action action;

    public TaskActionDTO(@JsonProperty("action") final Action action) {
        this.action = Objects.requireNonNull(action, "action can not be null");
    }

    public Action getAction() {
        return action;
    }

    public enum Action {
        MARK_AS_ACHIEVED, MARK_AS_REJECTED
    }
}
