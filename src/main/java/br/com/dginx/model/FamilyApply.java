package br.com.dginx.model;

import br.com.dginx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("familyApply")
public class FamilyApply {
    @Id
    private String id;
    private String applicantId;
    private String spouseId;
    private List<String> dependentsId;
    private Integer points;
    private Double tiebreaker;

    public void addPoints(Integer val) {
        if (points == null) {
            points = val;
        } else {
            points += val;
        }
    }

    public void resetPoints() {
        this.points = Utils.INT_0;
    }

    public List<String> getDependentsId() {
        if (dependentsId == null) {
            dependentsId = new ArrayList<>();
        }
        return dependentsId;
    }
}
