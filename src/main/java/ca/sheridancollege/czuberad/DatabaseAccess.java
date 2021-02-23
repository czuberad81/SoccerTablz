package ca.sheridancollege.czuberad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    NamedParameterJdbcTemplate jdbc;

    public void insertTeam(String teamName,String continent, int played,int won,int drawn,int lost){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO teams(TeamName,Continent,Played,Won,Drawn,Lost) VALUES(:teamName,:continent,:played,:won,:drawn,:lost)";

        namedParameters.addValue("teamName",teamName);
        namedParameters.addValue("continent",continent);
        namedParameters.addValue("played",played);
        namedParameters.addValue("won",won);
        namedParameters.addValue("drawn",drawn);
        namedParameters.addValue("lost",lost);
        jdbc.update(query,namedParameters);
    }
    public List<Team> getTeams(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }
}
