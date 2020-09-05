package io.sleepit.tasks.model.amount;

import java.math.BigDecimal;

public interface Amount {

    BigDecimal value();

    String unit();

    String asHumanReadableString();

}
