package ca.sheridancollege.czuberad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements Serializable {
    private Long teamID;
    private String teamName;
    private String continent;
    private int played;
    private int won;
    private int drawn;
    private int lost;
}
