package com.demon.Fitnes.repository;

import com.demon.Fitnes.model.Service;
import com.demon.Fitnes.repository.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Service> findById(Long id) {
        final String sql = """
                    SELECT service_id, name, service_price, place, description
                    FROM service
                    WHERE service_id = ?;
                    """;
        return jdbcTemplate.getJdbcTemplate().query(sql, ServiceMapper.MAPPER, id)
                .stream().findAny();
    }

    public List<Service> findAllServices() {
        final String sql = """
                    SELECT service_id, name, service_price, place, description
                    FROM service;
                    """;
        return jdbcTemplate.query(sql, ServiceMapper.MAPPER);
    }

    public long insert(Service service) {
        final String sql = "INSERT INTO service (name, service_price, place, description)" +
                " VALUES(:name, :price, :place, :description)";

        var params = new MapSqlParameterSource()
                .addValue("name", service.getName())
                .addValue("price", service.getPrice())
                .addValue("place", service.getPlace())
                .addValue("description", service.getDescription());

        var keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        return (long) keyHolder.getKeys().get("id");
    }

    public void update(Service service) {
        final String sql = "UPDATE service SET name = :name," +
                " service_price = :price, place = :place, description = :description" +
                " WHERE service_id = :id";

        var params = new MapSqlParameterSource()
                .addValue("id", service.getId())
                .addValue("name", service.getName())
                .addValue("price", service.getPrice())
                .addValue("place", service.getPlace())
                .addValue("description", service.getDescription());

        jdbcTemplate.update(sql, params);
    }
}
