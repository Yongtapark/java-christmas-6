package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.*;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;

import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDayDiscountBenefitEventTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate specialDate = LocalDate.of(2023, Month.DECEMBER, 25);
    private final static LocalDate nonSpecialDate = LocalDate.of(2023, Month.DECEMBER, 26);
    private final static SpecialDayDiscountEvent SPECIAL_DAY_DISCOUNT_EVENT = new SpecialDayDiscountEvent(
            SPECIAL_DISCOUNT, eventPeriod,1000);

    @DisplayName("특별 할인이 적용되는 날은 1000원을 추가할인한다.")
    @Test
    void reservationSpecialDate() {
        EventResult eventResult = SPECIAL_DAY_DISCOUNT_EVENT.execute(specialDate);
        Assertions.assertThat(eventResult.discountBenefit()).isEqualTo(BASIC_BENEFIT.getAmount());
    }

    @DisplayName("특별 할인이 적용되지 않는 날은 1000원을 추가할인하지 않는다.")
    @Test
    void reservationNoNSpecialDate() {
        EventResult eventResult = SPECIAL_DAY_DISCOUNT_EVENT.execute(nonSpecialDate);
        Assertions.assertThat(eventResult.discountBenefit()).isEqualTo(NO_BENEFIT.getAmount());
    }
}