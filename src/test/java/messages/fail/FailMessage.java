package messages.fail;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FailMessage {
    public final String MESSAGE_NOT_MATCH_EXPECTED = "Сообщение не соответствует ожидаемому";
    public final String CARD_COUNT_NOT_MATCH_EXPECTED = "Количество карточек не соответствует ожидаемому";
    public final String MENU_NOT_SORTED_BY_PRICE_ASC = "Меню не отсортировано по увеличению цены";
    public final String MENU_NOT_FILTERED_BY_MIN_PRICE = "Меню не отфильтровано по минимальной цене";
    public final String MENU_NOT_FILTERED_BY_MAX_PRICE = "Меню не отфильтровано по максимальной цене";

}
