package br.com.dginx.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.dginx.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("famillyApply")
public class FamillyApply {
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
