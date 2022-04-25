package by.sam_solutions.grigorieva.olga.backend.domain.table;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class TablePage<Item> {

    private List<Item> items;

    private Integer totalCount;

    private Integer currentShift;


    public static <TItem> TablePage<TItem> slice(List<TItem> items, int shift, int itemsPerPage) {
        if (shift > items.size()) {
            shift = (int) ((Math.ceil((double) items.size() / itemsPerPage) - 1) * itemsPerPage);
        }

        return new TablePage<>(items.subList(shift, Math.min(items.size(), shift + itemsPerPage)), items.size(), shift);
    }

    public static <TItem> TablePage<TItem> blank() {
        return new TablePage<>(new ArrayList<>(), 0, 0);
    }

}
