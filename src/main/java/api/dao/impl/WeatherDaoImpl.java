package api.dao.impl;

import api.connections.DBConnection;
import api.dao.WeatherDao;
import api.weather.Weather;
import api.weather.WeathersEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class WeatherDaoImpl implements WeatherDao {

    @Autowired
    DBConnection DBConnection;

    public void insert(final Weather weather) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String query = "" +
                "INSERT INTO WEATHERS(temp_c, temp_f, wind_mph, api_id, insetr_date) VALUES(?, ?, ?, ?, ?)";
        DBConnection.jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query, new String[]{"weather_id"});
                int i = 0;
                ps.setString(++i, weather.getTempC());
                ps.setString(++i, weather.getTempF());
                ps.setString(++i, weather.getWindMph());
                ps.setString(++i, weather.getApiId());
                ps.setDate(++i, weather.getInsertingDate());
                return ps;
            }
        }, keyHolder);
    }

    public Weather getWeather(String apiId) {
            String query = "" +
                    "SELECT * " +
                    "FROM WEATHERS " +
                    "WHERE api_id = '" + apiId + "' " +
                    "AND weather_id = " +
                    "(" +
                    "SELECT MAX(weather_id) " +
                    "FROM WEATHERS " +
                    "WHERE api_id = '" + apiId + "'" +
                    ")";
            Weather weather = DBConnection.jdbcTemplate.queryForObject(query, rowMapper);
        return weather;
    }

    private RowMapper<Weather> rowMapper = new RowMapper<Weather>() {
        public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {
            Weather weather = new Weather();
            weather.setWeatherId(rs.getInt("weather_id"));
            weather.setTempC(rs.getString("temp_c"));
            weather.setTempF(rs.getString("temp_f"));
            weather.setWindMph(rs.getString("wind_mph"));
            weather.setApiId(rs.getString("api_id"));
            weather.setInsertingDate(rs.getDate("insetr_date"));
            return weather;
        }
    };

}
