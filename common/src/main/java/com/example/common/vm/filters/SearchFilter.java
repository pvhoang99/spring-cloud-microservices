package com.example.common.vm.filters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.Condition;

@Setter
@Getter
@NoArgsConstructor
public abstract class SearchFilter {

    @JsonProperty("filters")
    private Map<String, Map<Op, Object>> filters;

    public abstract String prefix();

    @JsonIgnore
    public Set<Condition> buildConditions() {
        Set<Condition> conditions = new HashSet<>();
        for (String field : this.filters.keySet()) {
            Map<Op, Object> con = this.filters.get(field);
            for (Op op : con.keySet()) {
                Condition condition = op.build(field, con.get(op));
                conditions.add(condition);
            }
        }

        return conditions;
    }

}
