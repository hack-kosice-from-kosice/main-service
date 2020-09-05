package io.sleepit.model.task.amount;

import java.math.BigDecimal;

public interface Amount {

    BigDecimal value();

    String unit();

    String asHumanReadableString();

}
