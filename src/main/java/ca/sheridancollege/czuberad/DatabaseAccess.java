package ca.sheridancollege.czuberad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public List<Team> getTeamsByPoints(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams ORDER BY(Won * 3 + Drawn)DESC";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }
    public List<Team> getTeamsByName(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams ORDER BY TeamName ASC";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }
    public List<Team> getTeamsByCon(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams ORDER BY Continent ASC";
        return  jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }

    public List<Team> getTeamBySearch(String searchedString){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams WHERE TeamName like :searchedString OR Continent LIKE :searchedString";
        namedParameters.addValue("searchedString","%"+searchedString+"%");
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void deleteTeamById(Long teamID){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM teams WHERE TeamID = :teamID";
        namedParameters.addValue("teamID", teamID);
        jdbc.update(query,namedParameters);
    }

    public void updateTeamById(Team team){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "UPDATE teams SET TeamName =:teamName,Continent =:continent,Played =:played,Won =:won,Drawn =:drawn,Lost =:lost WHERE TeamID =:teamID";
        namedParameters.addValue("teamID",team.getTeamID());
        namedParameters.addValue("teamName",team.getTeamName());
        namedParameters.addValue("continent",team.getContinent());
        namedParameters.addValue("played",team.getPlayed());
        namedParameters.addValue("won",team.getWon());
        namedParameters.addValue("drawn",team.getDrawn());
        namedParameters.addValue("lost",team.getLost());
        jdbc.update(query,namedParameters);
    }

    public List<Team> getTeamById(Long teamID){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM teams WHERE TeamID =:teamID";
        namedParameters.addValue("teamID",teamID);
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<Team>(Team.class));
    }


}
