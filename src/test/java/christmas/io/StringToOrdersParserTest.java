package christmas.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.exceptions.IllegalOrderFormatException;
import christmas.order.Order;
import christmas.utils.StringToOrdersParser;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringToOrdersParserTest {
    private final static Order oneSteak = new Order(MainMenu.T_BONE_STEAK, 1);
    private final static Order twoIceCream = new Order(DessertMenu.ICE_CREAM, 2);

    @DisplayName("메뉴-수량,메뉴-수량 형식의 문자열을 입력하면 Set<Order> 를 반환한다.")
    @Test
    void twoMenusToOrders() {
        //when
        Set<Order> orders = StringToOrdersParser.parseInputToOrderSet("티본스테이크-1,아이스크림-2");

        //then
        assertThat(orders).contains(oneSteak, twoIceCream);
    }

    @DisplayName("같은 메뉴를 입력하면 예외가 발생한다.")
    @Test
    void name() {
        assertThatThrownBy(() -> StringToOrdersParser.parseInputToOrderSet("티본스테이크-1,티본스테이크-2")).isInstanceOf(
                IllegalOrderFormatException.class);
    }
}